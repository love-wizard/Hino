package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Context;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.extras.control.EmailField;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.model.Customer;
import com.hino.model.Staff;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

/**
 * List all customers. 
 * Modify credit.
 */
public class GeneralSalesCreditAdjust extends BasePage {
	private static final long serialVersionUID = -5120720346501405631L;
	
    @Bindable private Table customerTable = new Table("customertable");
    @Bindable private ActionLink creditLink = new ActionLink("credit", "修改积分");
    @Bindable private ActionLink vipLink = new ActionLink("vip", "修改VIP日期");
    @Bindable private ActionLink detailLink = new ActionLink("detail", "查看详细");
    @Bindable private ActionLink viewCreditHistory = new ActionLink("viewCreditHistory", "查看积分历史");
    @Bindable private String newcredit;
    @Bindable private String newvipdate;
    
    @Bindable private Form search_form = new Form("sform");
    
    private EmailField emailf = new EmailField("email", "Email:");
    private Submit search = new Submit("search", "搜索");
    private Submit reset = new Submit("reset", "重置");
    
    private String email = null;
    
    @Bindable private Form bat_form = new Form("bform");
    @Bindable private TextField textCredit = new TextField("textCredit", "批量增加积分", true);
    @Bindable private TextArea textAreaBatEmail = new TextArea("textAreaBatEmail", "批量调整积分EMAIL", 50, 10, true);
    @Bindable private TextArea errorAreaBatEmail = new TextArea("errorAreaBatEmail", "错误调整EMAIL", 50, 3, false);
    private Submit batUpdate = new Submit("batUpdate", "批量更新", this, "onOkClick");
    private Submit batReset = new Submit("batReset", "重置", this, "onCancelClick");

    @Override
    public void onInit() {

    	search_form.add(emailf);
    	search_form.add(search);
    	search_form.add(reset);
    	
    	search.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				email = emailf.getValue();
				return true;
			}
		});
    	
    	reset.setActionListener(new ActionListener() {

			@Override
			public boolean onAction(Control source) {
				email = null;
				return true;
			}
		});
    	
    	bat_form.add(textCredit);
    	bat_form.add(textAreaBatEmail);
    	bat_form.add(errorAreaBatEmail);
    	bat_form.add(batUpdate);
    	bat_form.add(batReset);
    	
		// ----- Display customer table -----
		customerTable.setClass(Table.CLASS_SIMPLE);
		customerTable.setWidth("100%");
		customerTable.setPageSize(20);
		customerTable.setShowBanner(true);

        // Use setDecorator to show Chinese characters and special symbos like < and >
        Column column = new Column("email", "账号");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return curCustomer.getEmail(); 
			}
        });
        customerTable.addColumn(column);
        
        column = new Column("chinesename","中文名");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return curCustomer.getChinesename();
			}
        });
        customerTable.addColumn(column);
        
        column = new Column("bopomofo","拼音");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return curCustomer.genFullName();
			}
        });
        customerTable.addColumn(column);
        
        column = new Column("reg_time","注册时间");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return TimeFormater.format1(curCustomer.getReg_time());
			}
        });
        customerTable.addColumn(column);

        column = new Column("last_login","上次登录");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return TimeFormater.format1(curCustomer.getLast_login_time());
			}
        });
        customerTable.addColumn(column);

        column = new Column("is_vip","VIP");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return curCustomer.genIsVip()?"是":"否";
			}
        });
        customerTable.addColumn(column);
        
        column = new Column("vip_date","VIP到期");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return TimeFormater.format2(curCustomer.getVip_valid());
			}
        });
        customerTable.addColumn(column);
        
        column = new Column("curcredit","积分");
        column.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Customer curCustomer = (Customer) object;
				return String.valueOf(curCustomer.getPoint());
			}
        });
        customerTable.addColumn(column);

        // Add "credit" and "detail" link
        column = new Column("action", "操作");

        //Update Credit
        creditLink.setAttribute("class", "ajaxCredit");
        creditLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				String jsonResult = "";
				int newCredit;
				try {
					newCredit = Integer.parseInt(newcredit);
					
					// Update credit of chosen customer
					Staff curStaff = (Staff)getContext().getSession().getAttribute("staff");
					ServiceResponse sr = getCustomerService().reset_point(newCredit, creditLink.getValueLong(), curStaff.getId());
					if(sr.isSucc())
					{
						jsonResult = "操作执行成功";
					} else
					{
						jsonResult = "操作执行失败";
					}
					
				}
				catch (NumberFormatException e) {
					jsonResult = "积分必须是正整数";
				}
				
				jsonResult = "{\"result\": \""+jsonResult+"\"}";
				return new ActionResult(jsonResult, ActionResult.JSON);
			}
		});

        // Update VIP date
        vipLink.setAttribute("class", "ajaxVip");
        vipLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				String jsonResult = "";
				
				Calendar vipDate = TimeFormater.parse2(newvipdate);
				
				// Update credit of chosen customer
				ServiceResponse sr = getCustomerService().reset_vip(vipDate, vipLink.getValueLong());
				if(sr.isSucc())
				{
					jsonResult = "操作执行成功";
				} else
				{
					jsonResult = "操作执行失败";
				}
					
				jsonResult = "{\"result\": \""+jsonResult+"\"}";
				return new ActionResult(jsonResult, ActionResult.JSON);
			}
		});
        
        // Show customer detail information
        detailLink.setAttribute("class", "ajaxDetail");
        detailLink.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				Customer targetCustomer = (Customer)getCustomerService().view_customer(detailLink.getValueLong());
				Map<String, String> customerHash = new HashMap<String, String>();
				customerHash.put("lastname", targetCustomer.getLastname());
				customerHash.put("firstname", targetCustomer.getFirstname());
				customerHash.put("chinesename", targetCustomer.getChinesename());
				customerHash.put("email", targetCustomer.getEmail());
				customerHash.put("gender", targetCustomer.genGenderStr());
				customerHash.put("dob", TimeFormater.format2(targetCustomer.getDob()));
				customerHash.put("address", targetCustomer.getAddress());
				customerHash.put("city", targetCustomer.getCity());
				customerHash.put("postcode", targetCustomer.getPostcode());
				customerHash.put("point", String.valueOf(targetCustomer.getPoint()));
				customerHash.put("cardno", targetCustomer.getCardno());

				StringWriter writer = new StringWriter();
				  ObjectMapper mapper = new ObjectMapper();
				  try {
				   mapper.writeValue(writer, customerHash);
				  } catch (JsonGenerationException e) {
				   e.printStackTrace();
				  } catch (JsonMappingException e) {
				   e.printStackTrace();
				  } catch (IOException e) {
				   e.printStackTrace();
				  }

				return new ActionResult(writer.toString(), ActionResult.JSON);
			}
		});
        ActionLink[] links = new ActionLink[]{creditLink,viewCreditHistory};
        column.setDecorator(new LinkDecorator(customerTable, links, "id"));
        customerTable.addColumn(column);
        
        viewCreditHistory.setActionListener(new ActionListener() {
            private static final long serialVersionUID = 1L;
            public boolean onAction(Control source) {
            	
                setRedirect("./view_credit_history.htm?id="+viewCreditHistory.getValueInteger());
                return true;
            }
        });
        
    }
    
    public void onRender()
    {
    	if(email==null)
        {
        	 // Get menu items from database as data provider
        	 customerTable.setDataProvider(new PagingDataProvider<Customer>() {
     			private static final long serialVersionUID = 2539474463720137969L;

     			// Customer data List
     			public List<Customer> getData() { 
                 	int page = customerTable.getPageNumber();
                     int size = customerTable.getPageSize(); 
                     List<Customer> pagedList = getCustomerService().getPagedCustomerList(page, size);
                     return pagedList;
                 }

     			// Menu item data size
     			@Override
     			public int size() {
     				return getCustomerService().getCustomerCount();
     			} 
             });
        } else
        {
        	customerTable.setDataProvider(new PagingDataProvider<Customer>() {
     			private static final long serialVersionUID = 2539474463720137969L;

     			// Customer data List
     			public List<Customer> getData() { 
                 	int page = customerTable.getPageNumber();
                     int size = customerTable.getPageSize(); 
                     List<Customer> pagedList = getCustomerService().searchCustomer(page,size, email, 0);
                     return pagedList;
                 }

     			// Menu item data size
     			@Override
     			public int size() {
     				return getCustomerService().searchCustomerCount(email, 0);
     			} 
             });
        }
    }
    
    public boolean onOkClick() {
    	String[] email = null;
    	int crdit = Integer.parseInt( this.textCredit.getValue());

		if (bat_form.isValid()) {
			email = textAreaBatEmail.getValue().trim().replace("\\s","").replace("\r", "").split("\n");
		}
		Staff s = (Staff)getContext().getSession().getAttribute("staff");
		
		String errorEmail = getCustomerService().bat_adjust_vip_credit(email,crdit , s);
		if(errorEmail!=null)
		{
			this.errorAreaBatEmail.setValue(errorEmail);
		}
		return true;
	}
    
    public boolean onCancelClick() {
    	bat_form.clearValues();
		return true;
	}

}

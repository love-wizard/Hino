package com.hino.page.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ControlRegistry;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.CheckList;
import org.apache.click.util.Bindable;

import com.hino.click.extension.Image;
import com.hino.dto.StaffCreateDto;
import com.hino.model.AreaBranch;
import com.hino.model.Staff;
import com.hino.util.PriviledgeParser;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class StaffViewSingle extends BasePage{
	@Bindable
	private Integer id;
	
	public String staffno;
	public String dob;
	public String email;
	public String fullname;
	public String chinesename;
	public String gender;
	//public String mobile;
	public String phone;
	public String address;
	public String city;
	public String postcode;
	public String create_time;
	public String last_login_time;
	public Image avatar = new Image("avatar");
	public String status;
	public CheckList priviledge = new CheckList("priviledge", "权限选择");
	public Button changestatus = new Button("changestatus", "激活/冻结");
	public Button resetpriviledge = new Button("resetpriviledge", "重设权限");
	
	
	public Select area = new Select("area", "所属地区");
	public HiddenField staffid = new HiddenField("staffid", "staffid");
	public String msg;
	public Form form = new Form("pri_change_form");
	
	@SuppressWarnings("unchecked")
	public void onInit()
	{
		changestatus.setId("changestatus");
		changestatus.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	int id = 0;
            	try{
            	id = Integer.parseInt(getContext().getRequest().getParameter("id"));
            	} catch (NumberFormatException nfe)
            	{
            		//TODO
            	} finally
            	{
            		//TODO
            	}
            	int new_status = getHumanResourceService().staff_status_change(id);
            	String jsondata = "" + new_status;
                return new ActionResult(jsondata, ActionResult.HTML);
            }
        });
		
		resetpriviledge.setId("resetpriviledge");
		resetpriviledge.addBehavior(new DefaultAjaxBehavior() {
            public ActionResult onAction(Control source) {
            	
            	int id = 0;
                // Form data can be saved here
            	Integer areaid = null;
            	List<Integer> pil = new ArrayList<Integer>();
				try {
					areaid = Integer.parseInt(getContext().getRequest().getParameter("area"));
					id = Integer.parseInt(getContext().getRequest().getParameter("id"));
					String[] values = getContext().getRequest().getParameterValues("priviledge");
					if(values!=null)
					{
						for (int i = 0; i < values.length; i++) {
							pil.add(Integer.parseInt(values[i]));
						}
					}
				} catch (NumberFormatException nfe) {
					// TODO
				} finally {
					// TODO
				}

				String privstr = PriviledgeParser.make_pri_string(pil);
				StaffCreateDto scdto = new StaffCreateDto();
				scdto.setId(id);
				scdto.setPriviledge(privstr);
				scdto.setAreaselect(areaid);
				
				//System.out.println(scdto);
				ServiceResponse sr = getHumanResourceService().reset_staff_priviledge(scdto);
				String returnmsg = "";
				if(sr.isSucc())
				{
					returnmsg="重设成功";
				} else
				{
					returnmsg="重设失败";
				}
				
				
                return new ActionResult(returnmsg, ActionResult.HTML);
            }
        });
		
		ControlRegistry.registerAjaxTarget(changestatus);
		ControlRegistry.registerAjaxTarget(resetpriviledge);
		
		
		if(id==null)
		{
			msg = "不存在此员工";
		} else
		{
			staffid.setValue(id+"");
			
			Staff s = getHumanResourceService().view_staff(id);
			
			if(s!=null)
			{
				
				priviledge.setDataProvider(new DataProvider() {
		            
					private static final long serialVersionUID = 1L;

					public List getData() {
		                List optionList = new ArrayList();
		                optionList.add(new Option("0", "销售代表"));
		                optionList.add(new Option("1", "地区代表"));
		                optionList.add(new Option("2", "财务管理"));
		                optionList.add(new Option("3", "市场管理"));
		                optionList.add(new Option("4", "人力资源管理"));
		                optionList.add(new Option("5", "导游"));
		                optionList.add(new Option("6", "销售管理"));
		                optionList.add(new Option("7", "导游管理"));
		                optionList.add(new Option("8", "旅游资料管理"));
		                optionList.add(new Option("9", "网站管理"));
		                return optionList;
		            }
		        });
				
				List<Integer> li = PriviledgeParser.list_all_priviledge_index(s.getPriviledge());
				List<String> ls = new ArrayList<String>();
				for(int i=0;i<li.size();i++)
				{
					ls.add(li.get(i)+"");
				}
				priviledge.setSelectedValues(ls);
				
				
				area.setDataProvider(new DataProvider() {
		            
					private static final long serialVersionUID = 1L;

					public List getData() {
						List optionList = new ArrayList();
						List<AreaBranch> abl = getHumanResourceService()
								.list_all_area();
						optionList.add(new Option(-1, ""));
						for (int i = 0; i < abl.size(); i++) {
							optionList.add(new Option(abl.get(i).getId(), abl.get(i)
									.getName()));
						}
		                
		                return optionList;
		            }
		        });
				//ArrayList<String> al = new ArrayList<String>();
				//al.add(""+s.getAreaid());
				area.setValue(""+s.getAreaid());
				//area.sD
				form.add(priviledge);
				form.add(area);
				
				staffno = s.getStaffno();
				
				dob = TimeFormater.format2(s.getDob());
				
				email = s.getEmail();
				fullname = s.genFullName();
				chinesename = s.getChinesename();
				gender = s.genGenderStr();
				phone = s.getPhone();
				address = s.getAddress();
				city = s.getCity();
				postcode = s.getPostcode();
				create_time = TimeFormater.format1(s.getCreate_time());
				last_login_time = TimeFormater.format1(s.getLast_login_time());
				avatar.setSrc(s.getAvatar_url());
				
				status = s.genStatusStr();
				//priviledge = PriviledgeParser.make_pri_string_inline(s.getPriviledge());
				if(s.getStatus()==0)
				{
					System.out.println("----setvalue activated");
					//changestatus.setAttribute("value","冻结账户");
					changestatus.setValue("冻结账户");
				} else
				{
					System.out.println("----setvalue inactivated");
					//changestatus.setAttribute("value","激活帐户");
					changestatus.setValue("激活帐户");
				}
				
				//ControlRegistry.registerAjaxTarget(form);
				
			} else
			{
				msg = "不存在此员工";
			}	
		}
	}
	
	@Override
    public List<Element> getHeadElements() {
        if (headElements == null) {
            headElements = super.getHeadElements();
            //headElements.add(new JsImport("/assets/js/jquery-1.4.2.js"));
            headElements.add(new JsScript("/internal/js/staff_view_single_ajax.js", new HashMap()));
        }
        return headElements;
    }
	
}

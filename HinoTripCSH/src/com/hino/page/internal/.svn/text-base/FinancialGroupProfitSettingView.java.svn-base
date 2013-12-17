package com.hino.page.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.click.ActionListener;
import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.ajax.DefaultAjaxBehavior;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.extras.control.DoubleField;
import org.apache.click.extras.control.IntegerField;
import org.apache.click.util.Bindable;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hino.dto.FinancialProfitFeedbackDto;
import com.hino.model.Booking;
import com.hino.model.Group;
import com.hino.model.GroupProfit;
import com.hino.util.ServiceResponse;
import com.hino.util.TimeFormater;

public class FinancialGroupProfitSettingView extends BasePage{

	@Bindable 
	private Integer gid;
	@Bindable 
	private String groupinfo = "缺少信息";
	
	
	@Bindable
	private Form form = new Form("profit_form");
	private HiddenField hf = new HiddenField("hidden", Integer.class);
	private DoubleField bus_exp = new DoubleField("bus_exp", "巴士支出");
	private DoubleField hotel_exp = new DoubleField("hotel_exp", "酒店支出");
	private DoubleField guide_exp = new DoubleField("guide_exp", "导游支出");
	private DoubleField driver_exp = new DoubleField("driver_exp", "司机支出");
	private DoubleField gift_exp = new DoubleField("gift_exp", "礼物支出");
	private DoubleField food_exp = new DoubleField("food_exp", "食品支出");
	private DoubleField plane_exp = new DoubleField("plane_exp", "机票支出");
	private DoubleField ship_exp = new DoubleField("ship_exp", "船票支出");

	private DoubleField others_exp = new DoubleField("others_exp", "其他支出");
	
	private IntegerField point_exp = new IntegerField("point_exp", "积分发放");
	private IntegerField point_in = new IntegerField("point_in", "积分收回");
	private IntegerField rep_order = new IntegerField("rep_order", "代表产生的订单数");
	private IntegerField total_order = new IntegerField("total_order", "总订单数");
	private DoubleField total_exp = new DoubleField("total_exp", "总计支出");
	
	private DoubleField tips_in = new DoubleField("tips_in", "小费收入");
	private DoubleField sales_total = new DoubleField("sales_total", "销售总收入");
	private DoubleField total_in = new DoubleField("total_in", "总收入");
	
	private DoubleField total_profit = new DoubleField("total_profit", "净利润");
	
	private DoubleField sales_in = new DoubleField("sales_in", "销售代表产生");
	private DoubleField sales_exp = new DoubleField("sales_exp", "销售代表提成");
	private Select reward_type = new Select("reward_type", "提成方式");
	private DoubleField reward_rate = new DoubleField("reward_rate", "提成值");
	private TextArea comments = new TextArea("comments", "备注");
	
	private ActionLink update = new ActionLink("update", "更新销售数据");
	
	private Submit save = new Submit("save", "保存结算数据", this, "onSaveClicked");
	
	public void onInit()
	{
		if(gid!=null)
		{
			hf.setValueObject(gid);
		}
		hf.setId("hidden");
		reward_type.add(new Option(0, "按百分比"));
		reward_type.add(new Option(1, "按固定值"));
		
		form.add(hf);
		
		form.add(bus_exp);
		form.add(hotel_exp);
		form.add(guide_exp);
		form.add(driver_exp);
		form.add(gift_exp);
		form.add(food_exp);
		form.add(plane_exp);
		form.add(ship_exp);
		form.add(others_exp);
		form.add(tips_in);
		form.add(sales_total);
		form.add(sales_in);
		form.add(sales_exp);
		form.add(rep_order);
		form.add(total_order);
		form.add(point_in);
		form.add(point_exp);
		form.add(reward_type);
		form.add(reward_rate);
		form.add(update);
		form.add(total_exp);
		form.add(total_in);
		form.add(total_profit);
		form.add(comments);
		
		form.add(save);
		
		update.setValueObject(hf.getValueObject());
		update.setAttribute("class", "ajaxReload");
		update.addBehavior(new DefaultAjaxBehavior() {
			@Override
			public ActionResult onAction(Control source) {
				int id = 0;
            	try{
            	id = Integer.parseInt(getContext().getRequest().getParameter("gid"));
            	} catch (NumberFormatException nfe)
            	{
            		//TODO
            	} finally
            	{
            		//TODO
            	}
				FinancialProfitFeedbackDto fpfdto = getFinancialService().profit_reload_sales(id);
				
				Map<String, String> bookingHash = new HashMap<String, String>();

				bookingHash.put("point_exp", fpfdto.getPoint_exp() + "");
				bookingHash.put("point_in", fpfdto.getPoint_in() + "");
				bookingHash.put("sales_in", fpfdto.getSales_in() + "");
				bookingHash.put("sales_total", fpfdto.getT_sales_in() + "");
				bookingHash.put("rep_order", fpfdto.getRep_order()+"");
				bookingHash.put("total_order", fpfdto.getTotal_order()+"");

				StringWriter writer = new StringWriter();
				ObjectMapper mapper = new ObjectMapper();
				try {
					mapper.writeValue(writer, bookingHash);
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
		
	}
	
	public void onRender()
	{
		Group g = null;
		if(gid!=null)
		{
			g = this.getSalesService().viewGroupById(gid);
			groupinfo = g.genBookingInfo();
		}
		
		if(g!=null)
		{
			GroupProfit gp = g.getProfit();
			if(gp!=null)
			{
				form.copyFrom(gp);	
			} else
			{
				
			}
		}
	}
	
	public boolean onSaveClicked() {
        if (form.isValid()) {
        	GroupProfit gp = new GroupProfit();
    		form.copyTo(gp);
    		if(hf.getValueObject()!=null)
    		{
    			System.out.println("profit save called");
    			this.getFinancialService().profit_save((Integer)hf.getValueObject(), gp);
    			gid = (Integer)hf.getValueObject();
    		}
        }
        return true;
    }

	
}

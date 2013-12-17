package com.hino.util;

import java.text.SimpleDateFormat;

import com.hino.model.Booking;

public class HinoMailUtil {
	public static void SendBookingEmail(Booking bookingInfo)
	{	
		//+ Devon King - 2012/09/04 - Uncomment this to enable the booking mail function
		// Send booking information email
		//Booking bookingInfo = (Booking)sr.getResponse();
		String bookingMethod = null, bookingMethodEn = null;
		switch(bookingInfo.getBooking_method()) {
		case Info.BKM_EXTERNAL_BANKTRANSFER:
			bookingMethod = "银行转账付款";
			bookingMethodEn = "Bank transfer";
			break;
		case Info.BKM_EXTERNAL_GOOGLECHECKOUT:
			bookingMethod = "在线支付";
			bookingMethodEn = "Online checkout";
			break;
		case Info.BKM_EXTERNAL_PAYATBRANCH:
			bookingMethod = "公司前台支付";
			bookingMethodEn = "Cash";
			break;
		}
		
		//Kevin Zhong - 14/11/2012 - dont send email when type is seckill
		//Devon King - 09/11/2012 - TD#68 Format dates
		SimpleDateFormat zhDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat enDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String content = ""+
		"尊敬的游客 "+ bookingInfo.genFullname()+"\n"+
		"恭喜您！"+"\n"+
		"您已经成功预订了海诺旅游 "+bookingInfo.getBookingRef() +" "+ zhDateFormat.format(bookingInfo.getGroup().getDepart_date().getTime()) +" "+bookingInfo.getGroup().getName()+
		"以下是给您的基本信息包括：\n"+
		"游客姓名:  "+bookingInfo.genFullname()+"\n"+
		"游客性别:  "+bookingInfo.genGenderStr()+"\n"+
		"接车点：  "+bookingInfo.getPickup()+"\n"+
		"联系电话:  "+bookingInfo.getPhone()+"\n"+
		"房间分配情况： "+bookingInfo.getRoom()+"\n"+"\n"+

		"请仔细核对报名信息，若信息无误，请及时付款。\n\n"+
		"付款方式： \n"+
		"\t"+"1.通过银行转账付款"+"\n"+
		"\t"+ "    "+"银行转账付款信息如下："+"\n"+
		"\t"+ "    "+"银行账户:Hino Travel"+"\n"+
		"\t"+ "  "+"Sort code: 20-63-25"+"\n"+
		"\t"+ "  "+"Account number: 33250482"+"\n"+
		"\t"+ "    "+"请务必在reference中写清楚您的名字拼音。"+"\n"+
		"\t"+"2.联系当地销售代表缴纳现金"+"\n"+
		"\t"+"3.到海诺旅游办公室缴纳现金"+"\n"+
		"\t"+ "    "+"海诺办公室地址：Crystal One，Ramada Hotel，17-31 Wollaton Street，Nottingham NG1 5FW."+"\n"+"\n"+
		"付款之后请发送邮件到：info@hinotravel.com 写清您的付款金额和银行转账reference内容。"+"\n"+"\n"+
		"如有任何问题请拨打03339009888咨询。\n" +
		"感谢您选择海诺旅游，祝您旅途愉快！"+"\n"+"\n"+
		"海诺旅游"+"\n"+
				
		"Dear "+ bookingInfo.genFullname()+"\n"+
		"You have booked Hino Travel "+bookingInfo.getBookingRef() +" "+ enDateFormat.format(bookingInfo.getGroup().getDepart_date().getTime()) +" "+bookingInfo.getGroup().getName()+
		"\n" + 
		"Name: "+bookingInfo.genFullname()+"\n"+
		"Gender: "+bookingInfo.genGenderStr()+"\n"+
		"Pick-up point: "+bookingInfo.getPickup()+"\n"+
		"Contact no.: "+bookingInfo.getPhone()+"\n"+
		"Roommate: "+bookingInfo.getRoom()+"\n"+"\n"+
		
		"Please check the information carefully.\n\n"+
		"Payment method： \n"+
		"\t"+"1.Bank transfer："+"\n"+
		"\t"+ "  "+"Payment information:"+"\n"+
		"\t"+ "  "+"Account name:Hino Travel"+"\n"+
		"\t"+ "  "+"Sort code: 20-63-25"+"\n"+
		"\t"+ "  "+"Account number: 33250482"+"\n"+
		"\t"+ "  "+"Please write down your name and trip name in the reference."+"\n"+
		"\t"+"2.Pay cash to our sales in your town."+"\n"+
		"\t"+"3.Come to office of Hino Travel to pay your tour fee. "+"\n"+
		"\t"+ "  "+"Address: Crystal One，Ramada Hotel，17-31 Wollaton Street，Nottingham NG1 5FW."+"\n"+"\n"+
		"Thank you for travelling with us, wish you a pleasant journey!"+"\n"+"\n"+
		"Best Regards,"+"\n"+"\n"+
		"Hino Travel"+"\n" ;
		
		try {
			/*String filePath = new FileCenter().getContractDir()+"Hino_Customer_Contract.pdf";
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.send(bookingInfo.getEmail(), Info.EMAIL_TO, 
					"[Hino Travel]Booking reservation information",
					content,
					filePath, "Hino_Customer_Contract.pdf");*/
			//String filePath = new FileCenter().getContractDir()+"Hino_Customer_Contract.pdf";
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.send(bookingInfo.getEmail(), Info.EMAIL_TO, 
					"[Hino Travel]Booking reservation information",
					content,
					"", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//- Devon King - 2012/09/04
	}
		
}

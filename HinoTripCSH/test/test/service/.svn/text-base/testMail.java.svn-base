package test.service;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;


public class testMail {

	@Test
	public void testMail2() {
	try {
			Properties props = new Properties();
	        props.setProperty("mail.smtp.host", "auth.smtp.1and1.co.uk");//key   value
	        props.setProperty("mail.smtp.auth", "true");
	        
	        Session s = Session.getInstance(props, null);
	        Transport transport = s.getTransport("smtp");//???????(SMTP)                
	        transport.connect("auth.smtp.1and1.co.uk", 
	        	 "webmaster@s355602427.onlinehome.info", 
	        	 "zzhinoc2010");//???????            
	        
	        MimeMessage msg = new MimeMessage(s);
	        msg.setSubject("testing");//?????? 
	        msg.setSentDate(new Date());//????????
	        
	        //*****???*****//
	        //msg.addFrom(InternetAddress.parse(st.getMail().getFrom()));//?????
	
	        msg.addFrom(InternetAddress.parse("test@hinotravel.com"));//?????
	
	        msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse("kevin.zhong1987@gmail.com"));//?????                       
	   
	        BodyPart curBody = null;
	        try {
	        	curBody = new MimeBodyPart();
	            DataHandler dh = new DataHandler("test", "text/html;charset=UTF-8");
	            curBody.setDataHandler(dh);
	        	} catch (Exception exception) {
	        }
	        	
	    	Multipart mm = new MimeMultipart();
	        mm.addBodyPart(curBody);//???????
	        /*
	        for (int i = 0; i < curAttList.size(); i++) {
	            BodyPart part = (BodyPart) curAttList.get(i);
	            mm.addBodyPart(part);
	        }               
	        */      
	        msg.setContent(mm);//??????? 
	        //*****???*****//
	        msg.saveChanges();//??????
	        transport.sendMessage(msg, msg.getAllRecipients());//???? 
	        transport.close();//??????
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Error");
		}
		
	}
	
	public static void main(String[] args){
		testMail tm = new testMail();
		System.out.println("send");
		tm.testMail2();
		System.out.println("Finished");
	}
}

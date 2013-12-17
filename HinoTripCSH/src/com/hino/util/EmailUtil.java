package com.hino.util;

/**
* @author star
* Available at: 
* 	http://www.360doc.com/content/10/1126/21/3356862_72735210.shtml
* 
* History:
* 1. 2011-01-09 Imported into Project Hino
* 2. 2011-01-09 Modified by Lilei Zhai to use UTF-8 encoding and make sure 
* 				chinese email subject will be displayed correctly.
* 2. 2011-03-05 Modified by Lilei Zhai to make it thread safe.
*/
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
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

public class EmailUtil {
    public static String TEXT = "text/plain;charset=UTF-8";
    public static String HTML = "text/html;charset=UTF-8";
    private String host; //邮件服务器
    private String user; //用户名
    private String pass;//用户密码
    private String from;//发信人
    private String to;//收信人
    private String cc;//Carbon Copy, 抄送邮件给某人
    private String bc;//bcc Blind Carbon Copy,隐蔽副本 隐蔽抄送给某人
    private String subject;//邮件主题
    private BodyPart body;//邮件内容
    private boolean needAuth; //是否需要认证
    private List<BodyPart> attaches;//邮件附件
        
	/**
	* 构造方法
	*
	*/
    public EmailUtil() {
        needAuth = true;
        attaches = new ArrayList<BodyPart>();
    }
    
    /**
     * 构造方法
     * @param host
     */
    public EmailUtil(String host) {
        needAuth = true;
        attaches = new ArrayList<BodyPart>();
        this.host = host;
    }
    
    /**
     * 构造方法
     * @param host
     * @param user
     * @param pass
     */
    public EmailUtil(String host, String user, String pass) {
        needAuth = true;
        attaches = new ArrayList<BodyPart>();
        this.host = host;
        this.user = user;
        this.pass = pass;
    }

    public static String getTEXT() {
		return TEXT;
	}

	public static void setTEXT(String tEXT) {
		TEXT = tEXT;
	}

	public static String getHTML() {
		return HTML;
	}

	public static void setHTML(String hTML) {
		HTML = hTML;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBc() {
		return bc;
	}

	public void setBc(String bc) {
		this.bc = bc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		try {
			this.subject = MimeUtility.encodeWord(subject, "UTF-8", null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public BodyPart getBody() {
		return body;
	}

	public boolean isNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
	}

	public List<BodyPart> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<BodyPart> attaches) {
		this.attaches = attaches;
	}

	/**
	* 设置邮件内容的形式
	* @param string
	* @param contentType
	*/
    public void setBody(String string, String contentType) {
        try {
            body = new MimeBodyPart();
            DataHandler dh = new DataHandler(string, contentType);
            body.setDataHandler(dh);
        } catch (Exception exception) {
        }
    }
    
	/**
	* 设置邮件的内容的格式为文本格式
	* @param string
	*/
    public void setBodyAsText(String string) {
        setBody(string, TEXT);
    }
	        
	/**
	* 以HTMl的形式存放内容
	* @param string
	*/
    public void setBodyAsHTML(String string) {
        setBody(string, HTML);
    }
    
	/**
	* 从文件中自动导入邮件内容
	* @param filename
	*/
    public void setBodyFromFile(String filename) {
	    try {
            BodyPart mdp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(filename);
            DataHandler dh = new DataHandler(fds);
            mdp.setDataHandler(dh);
            attaches.add(mdp);
	    } catch (Exception exception) {
	    }
    }
    
	/**
	* 从一个URL导入邮件的内容
	* @param url
	*/
    public void setBodyFromUrl(String url) {
        try {
            BodyPart mdp = new MimeBodyPart();
            URLDataSource ur = new URLDataSource(new URL(url));
            DataHandler dh = new DataHandler(ur);
            mdp.setDataHandler(dh);
            attaches.add(mdp);
        } catch (Exception exception) {
        }
    }
    
	/**
	* 将String中的内容存放入文件showname，并将这个文件作为附件发送给收件人
	* @param string 为邮件的内容
	* @param showname 显示的文件的名字
	*/
    public void addAttachFromString(String string, String showname) {
        try {
            BodyPart mdp = new MimeBodyPart();
            DataHandler dh = new DataHandler(string, TEXT);
            mdp.setFileName(MimeUtility.encodeWord(showname, "UTF-8", null));
            mdp.setDataHandler(dh);
            attaches.add(mdp);
        } catch (Exception exception) {
        }
    }
    
	/**
	* filename为邮件附件
	* 在收信人的地方以showname这个文件名来显示
	* @param filename
	* @param showname
	*/
    public void addAttachFromFile(String filename, String showname) {
        try {
            BodyPart mdp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(filename);
            DataHandler dh = new DataHandler(fds);
            mdp.setFileName(MimeUtility.encodeWord(showname, "UTF-8", null));
            mdp.setDataHandler(dh);
            attaches.add(mdp);
        } catch (Exception exception) {
        }
    }
    
	/**
	* 将互联网上的一个文件作为附件发送给收信人
	* 并在收信人处显示文件的名字为showname
	* @param url
	* @param showname
	*/
    public void addAttachFromUrl(String url, String showname) {
        try {
            BodyPart mdp = new MimeBodyPart();
            URLDataSource ur = new URLDataSource(new URL(url));
            DataHandler dh = new DataHandler(ur);
            mdp.setFileName(MimeUtility.encodeWord(showname, "UTF-8", null));
            mdp.setDataHandler(dh);
            attaches.add(mdp);
        } catch (Exception exception) {
        }
    }
    
	/**
	* 发送邮件,需要身份认证
	* @throws Exception
	*/
    public void unsafe_send() throws Exception {
        try {
           //*****会话类*****//
            Properties props = new Properties();
            if (host != null && !host.trim().equals(""))
                    props.setProperty("mail.smtp.host", host);//key   value
            else
                    throw new Exception("没有指定发送邮件服务器");
            if (needAuth)
                    props.setProperty("mail.smtp.auth", "true");
            Session s = Session.getInstance(props, null);
            //*****消息类*****//
            MimeMessage msg = new MimeMessage(s);                
            msg.setSubject(subject);//设置邮件主题 
            msg.setSentDate(new Date());//设置邮件发送时间
            //*****地址类*****//
            if (from != null)
                msg.addFrom(InternetAddress.parse(from));//指定发件人
            else
                throw new Exception("没有指定发件人");                        
            if (to != null)
                msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));//指定收件人
            else
                throw new Exception("没有指定收件人地址");                        
            if (cc != null)
                msg.addRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));//指定抄送                        
            if (bc != null)
                msg.addRecipients(Message.RecipientType.BCC,InternetAddress.parse(bc));//指定密送
            	Multipart mm = new MimeMultipart();
            if (body != null)
                mm.addBodyPart(body);//设置邮件的附件
            for (int i = 0; i < attaches.size(); i++) {
                BodyPart part = (BodyPart) attaches.get(i);
                mm.addBodyPart(part);
            }                     
            msg.setContent(mm);//设置邮件的内容 
            //*****传输类*****//
            msg.saveChanges();//保存所有改变                     
            Transport transport = s.getTransport("smtp");//发送邮件服务器（SMTP）                
            transport.connect(host, user, pass);//访问邮件服务器            
            transport.sendMessage(msg, msg.getAllRecipients());//发送信息 
            transport.close();//关闭邮件传输
        } catch (Exception e) {
        	e.printStackTrace();
                throw new Exception("发送邮件失败:", e);
        }
    }
    
	/**
	 * 修改过的发送邮件,线程安全
	 * 
	 * @param addressStr 收件人地址列表，多个收件人用逗号隔开
	 * @param sendType 收件人类型：Info.Email_TO/CC/BC
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param attPath 附件路径
	 * @param attName 附件名称
	 * @throws Exception
	 */
    public void send(String addressStr, int sendType, String title, 
    		String content, String attPath, String attName) 
    		throws Exception {
        try {
           //*****会话类*****//
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "auth.smtp.1and1.co.uk");//key   value

             props.setProperty("mail.smtp.auth", "true");
            
            // Session.getInstance() is thread safe as it return a new object:
            // http://download.oracle.com/javaee/5/api/javax/mail/Session.html
            Session s = Session.getInstance(props, null);
            
            //*****消息类*****//
            MimeMessage msg = new MimeMessage(s);
            try {
            	title = MimeUtility.encodeWord(title, "UTF-8", null);
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
            msg.setSubject(title);//设置邮件主题 
            msg.setSentDate(new Date());//设置邮件发送时间
            
            //*****地址类*****//
            msg.addFrom(InternetAddress.parse("NoReply@HinoTravel.com"));//指定发件人
            
            // 如果是抄送和暗送，to指定为邮件服务器自身
            switch(sendType) {
            	case Info.EMAIL_TO:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse(addressStr));//指定收件人
            		break;
            	case Info.EMAIL_CC:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse("webmaster@s355602427.onlinehome.info"));
                    msg.addRecipients(Message.RecipientType.CC,InternetAddress.parse(addressStr));//指定抄送    
            		break;
            	case Info.EMAIL_BC:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse("webmaster@s355602427.onlinehome.info"));
            		msg.addRecipients(Message.RecipientType.BCC,InternetAddress.parse(addressStr));//指定密送
            		break;
            	default:
            		throw new Exception("没有指定收件人地址");
            }
            
            //设置邮件正文
            BodyPart curBody = null;
            try {
            	curBody = new MimeBodyPart();
                DataHandler dh = new DataHandler(content, TEXT);
                curBody.setDataHandler(dh);
            } catch (Exception exception) {
            }
            
            //设置邮件附件
            List<BodyPart> curAttList = new ArrayList<BodyPart>();
            
            if(attPath != null && !attName.equals("")) {
	            try {
	                BodyPart mdp = new MimeBodyPart();
	                FileDataSource fds = new FileDataSource(attPath);
	                DataHandler dh = new DataHandler(fds);
	                mdp.setFileName(MimeUtility.encodeWord(attName, "UTF-8", null));
	                mdp.setDataHandler(dh);
	                curAttList.add(mdp);
	            } catch (Exception exception) {
	            }
            }

            Multipart mm = new MimeMultipart();
            mm.addBodyPart(curBody);//设置邮件的附件
            for (int i = 0; i < curAttList.size(); i++) {
                BodyPart part = (BodyPart) curAttList.get(i);
                mm.addBodyPart(part);
            }                     
            msg.setContent(mm);//设置邮件的内容 
            //*****传输类*****//
            msg.saveChanges();//保存所有改变                     
            Transport transport = s.getTransport("smtp");//发送邮件服务器（SMTP）  
            //TODO Ken Chen - 01/09/2012 - 修改为最新的邮件发送服务器，需要测试 
            transport.connect("auth.smtp.1and1.co.uk", 
            		"webmaster@s355602427.onlinehome.info", 
            		"zzhinoc2010");//访问邮件服务器            
            transport.sendMessage(msg, msg.getAllRecipients());//发送信息 
            transport.close();//关闭邮件传输
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("发送邮件失败:", e);
        }
    }
    
    public void sendWithMultiAtts(String addressStr, int sendType, String title, 
    		String content, String[] attPaths, String[] attNames) 
    		throws Exception {
        try {
           //*****会话类*****//
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "auth.smtp.1and1.co.uk");//key   value

             props.setProperty("mail.smtp.auth", "true");
            
            // Session.getInstance() is thread safe as it return a new object:
            // http://download.oracle.com/javaee/5/api/javax/mail/Session.html
            Session s = Session.getInstance(props, null);
            
            //*****消息类*****//
            MimeMessage msg = new MimeMessage(s);
            try {
            	title = MimeUtility.encodeWord(title, "UTF-8", null);
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
            msg.setSubject(title);//设置邮件主题 
            msg.setSentDate(new Date());//设置邮件发送时间
            
            //*****地址类*****//
            msg.addFrom(InternetAddress.parse("NoReply@HinoTravel.com"));//指定发件人
            
            // 如果是抄送和暗送，to指定为邮件服务器自身
            switch(sendType) {
            	case Info.EMAIL_TO:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse(addressStr));//指定收件人
            		break;
            	case Info.EMAIL_CC:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse("webmaster@s355602427.onlinehome.info"));
                    msg.addRecipients(Message.RecipientType.CC,InternetAddress.parse(addressStr));//指定抄送    
            		break;
            	case Info.EMAIL_BC:
            		msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse("webmaster@s355602427.onlinehome.info"));
            		msg.addRecipients(Message.RecipientType.BCC,InternetAddress.parse(addressStr));//指定密送
            		break;
            	default:
            		throw new Exception("没有指定收件人地址");
            }
            
            //设置邮件正文
            BodyPart curBody = null;
            try {
            	curBody = new MimeBodyPart();
                DataHandler dh = new DataHandler(content, TEXT);
                curBody.setDataHandler(dh);
            } catch (Exception exception) {
            }
            
            //设置邮件附件
            List<BodyPart> curAttList = new ArrayList<BodyPart>();
            
            if(attPaths.length > 0 && attNames.length > 0){
            	for(int i = 0; i < attPaths.length; i ++) {
            		if(!"".equals(attNames[i].trim())) {
	            		try {
	    	                BodyPart mdp = new MimeBodyPart();
	    	                FileDataSource fds = new FileDataSource(attPaths[i]);
	    	                DataHandler dh = new DataHandler(fds);
	    	                mdp.setFileName(MimeUtility.encodeWord(attNames[i], "UTF-8", null));
	    	                mdp.setDataHandler(dh);
	    	                curAttList.add(mdp);
	    	            } catch (Exception exception) {
	    	            }
            		}
            	}
            }

            Multipart mm = new MimeMultipart();
            mm.addBodyPart(curBody);//设置邮件的附件
            for (int i = 0; i < curAttList.size(); i++) {
                BodyPart part = (BodyPart) curAttList.get(i);
                mm.addBodyPart(part);
            }                     
            msg.setContent(mm);//设置邮件的内容 
            //*****传输类*****//
            msg.saveChanges();//保存所有改变                     
            Transport transport = s.getTransport("smtp");//发送邮件服务器（SMTP）  
            //TODO Ken Chen - 01/09/2012 - 修改为最新的邮件发送服务器，需要测试 
            transport.connect("auth.smtp.1and1.co.uk", 
            		"webmaster@s355602427.onlinehome.info", 
            		"zzhinoc2010");//访问邮件服务器            
            transport.sendMessage(msg, msg.getAllRecipients());//发送信息 
            transport.close();//关闭邮件传输
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("发送邮件失败:", e);
        }
    }
    
}




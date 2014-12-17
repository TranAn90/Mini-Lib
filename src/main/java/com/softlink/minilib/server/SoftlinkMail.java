package com.softlink.minilib.server;

import com.softlink.minilib.server.mailapi.SendMailService;
import com.softlink.minilib.server.mailapi.SendMailServiceImplService;


public class SoftlinkMail {
	
	public static String displaySender = "softlink.vn";
	public static String username = "transporter@softlink.vn";
	public static String password = "sltransporter20012014";
	public static String smtpServer = "smtp.gmail.com";
	public static String smtpPort = "587";
	
	public static SendMailService transporter = new SendMailServiceImplService().getSendMailServiceImplPort();
	
	public static String Send(String mailTo, String subject, String content){
		String ret = "NOK";
		try{
			ret = transporter.sendMail(mailTo, subject, content, 
				username, password, displaySender, smtpServer, smtpPort);
		}
		catch(javax.xml.ws.WebServiceException e){
			ret = "Seem OK";
		}
		return ret;
	}
	
	public static String Send(String mailTo, String subject, String content, String mailCC){
		String ret = "NOK";
		try{
			ret = transporter.sendMailWithCC(mailTo, subject, content, 
				username, password, displaySender, smtpServer, smtpPort, mailCC);
		}
		catch(javax.xml.ws.WebServiceException e){
			ret = "Seem OK";
		}
		return ret;
	}
	
}

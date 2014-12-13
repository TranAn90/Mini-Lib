package com.itpro.minilib.server.register;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;

import com.itpro.minilib.shared.Invite_Token;
import com.itpro.minilib.shared.System_Account;
import com.itpro.minilib.shared.System_Organization;

public class Register extends RemoteServiceServlet{

	public Register() {
		super();
		ObjectifyService.register(System_Organization.class);
		ObjectifyService.register(System_Account.class);
		ObjectifyService.register(Invite_Token.class);
	}
	
	

}

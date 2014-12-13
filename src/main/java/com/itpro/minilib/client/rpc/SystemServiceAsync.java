package com.itpro.minilib.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.itpro.minilib.shared.Invite_Token;
import com.itpro.minilib.shared.System_Account;
import com.itpro.minilib.shared.System_Organization;

public interface SystemServiceAsync {
	
	void insertOrganization(System_Organization organization, AsyncCallback<System_Organization> callback);
	void updateOrganization(System_Organization organization, AsyncCallback<Boolean> callback);
	void retrieveOrganization(Long id, AsyncCallback<System_Organization> callback);
	
	void insertAccount(System_Account account, AsyncCallback<Boolean> callback);
	void updateAccount(System_Account account, AsyncCallback<Boolean> callback);
	void retrieveAccount(String email, AsyncCallback<System_Account> callback);
	
	void insertInviteToken(Invite_Token token, AsyncCallback<Boolean> callback);
	void retrieveInviteTokens(String email, AsyncCallback<Invite_Token> callback);
	
}

package com.softlink.minilib.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Account;
import com.softlink.minilib.shared.System_Organization;

public interface SystemServiceAsync {
	
	void insertOrganization(System_Organization organization, AsyncCallback<System_Organization> callback);
	void updateOrganization(System_Organization organization, AsyncCallback<Boolean> callback);
	void retrieveOrganization(Long id, AsyncCallback<System_Organization> callback);
	
	void insertAccount(System_Account account, AsyncCallback<Boolean> callback);
//	void updateAccount(System_Account account, AsyncCallback<Boolean> callback);
	void retrieveAccount(String email, AsyncCallback<System_Account> callback);
	void goToOrganization(System_Organization organization, AsyncCallback<Boolean> callback);
	
	void insertInviteToken(String userEmail, System_Organization organization, AsyncCallback<Boolean> callback);
	void retrieveInviteTokens(String email, AsyncCallback<List<Invite_Token>> callback);
	void inviteTokenAccepted(Invite_Token token, AsyncCallback<System_Organization> callback);
	void inviteTokenDenied(Invite_Token token, AsyncCallback<Void> callback);
	
}

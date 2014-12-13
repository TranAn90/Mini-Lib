/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.itpro.minilib.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.itpro.minilib.client.rpc.SystemService;
import com.itpro.minilib.shared.Invite_Token;
import com.itpro.minilib.shared.System_Account;
import com.itpro.minilib.shared.System_Organization;

public class SystemServiceImpl extends RemoteServiceServlet implements SystemService {
	
	private static final UserService userService = UserServiceFactory
			.getUserService();
	
	static String[] charRandomList = {"1","2","3","4","5","6","7","8","9","q","w","e"
		,"r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
	
	static int maxOrganization = 5;

	private String generateCompanyID() {
		int totalOrganization = ofy().load().type(System_Organization.class).list()
				.size();
		String preid = "";
		String sufid = "";
		String id = "";
		for (int i = 1; i <= 8; i++) {
			int idx = new Random().nextInt(charRandomList.length);
			String random = (charRandomList[idx]);
			preid = preid + random;
		}
		sufid = "-c" + (totalOrganization + 1);
		id = preid + sufid;
		return id;
	}
	
	private boolean isUserLogin() {
		return userService.isUserLoggedIn();
	}
	
	private System_Account getCurrentUser() {
		String user_email = userService.getCurrentUser().getEmail();
		System_Account current_user = ofy().load().type(System_Account.class)
				.id(user_email).now();
		return current_user;
	}

	@Override
	public System_Organization insertOrganization(System_Organization organization) {
		if(isUserLogin() && (getCurrentUser().getOrganizationList() == null || 
				getCurrentUser().getOrganizationList().size() < maxOrganization)) {
			organization.setId(generateCompanyID());
			System_Account currentUser = getCurrentUser();
			if(currentUser.getOrganizationList() == null) {
				List<String> organizationList = new ArrayList<String>();
				currentUser.setOrganizationList(organizationList);
			}
			currentUser.addOrganization(organization.getId());
			ofy().save().entity(organization);
			ofy().save().entity(currentUser);
			return organization;
		}
		return null;
	}

	@Override
	public boolean updateOrganization(System_Organization organization) {
		if(isUserLogin() && getCurrentUser().getOrganizationList().contains(organization.getId())) {
			ofy().save().entity(organization);
			return true;
		}
		return false;
	}

	@Override
	public System_Organization retrieveOrganization(Long id) {
		if(isUserLogin() && getCurrentUser().getOrganizationList().contains(id)) {
			return ofy().load().type(System_Organization.class).id(id).now();
		}
		return null;
	}

	@Override
	public boolean insertAccount(System_Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(System_Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public System_Account retrieveAccount(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertInviteToken(Invite_Token token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Invite_Token> retrieveInviteTokens(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

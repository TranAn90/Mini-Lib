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
package com.softlink.minilib.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.softlink.minilib.client.rpc.SystemService;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Account;
import com.softlink.minilib.shared.System_Organization;

public class SystemServiceImpl extends RemoteServiceServlet implements SystemService {
	
	private static final UserService userService = UserServiceFactory.getUserService();
	
	public SystemServiceImpl() {}

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
	
	private String generateInviteMail(System_Organization organization) {
		String homeUrl = "http://softlinklab.appspot.com/";
		String msgBody = "<div id=':1qo' class='ii gt m14449037a714da04 adP adO'><div id=':1q1' class='a3s' style='overflow: hidden;'><div style='border:1px solid #f5f5f5;max-width:90%'><div style='background-color:#eee;padding:5px 15px'><a title=' Mini Task '><img src='http://www.softlink.vn/s/setting/images/ItproLogo.png' style='width:40px' alt=' Itpro '></a></div><div style='padding:0 15px'><p>Xin chào,</p><p style='line-height:20px'>Mục đích của thư này là nhằm thông báo cho bạn rằng  "
				+ organization.getAdmin()
				+ " đã mời bạn gia nhập vào tổ chức  '"
				+ organization.getName()
				+ "' của họ. Với Mini Task bạn có thể sắp xếp và quản lý công việc một cách dễ dàng. Để chấp nhận lời mời này, nhấp vào nút bên dưới.</p><p><a href='"
				+ homeUrl
				+ "'; style='background:dodgerblue;border:1px solid dodgerblue;border-radius:2px;color:#fff;display:inline-block;font-family:arial,sans-serif;font-size:12px;font-weight:bold;margin:5px 0;padding:5px 8px;white-space: nowrap;text-decoration:none' target='_blank'>Đồng ý</a></p><p style='line-height:20px'>Quan trọng: Bạn cần dùng đúng email được mời này để đăng nhập vào Mini Task</p>"
				+ "<p>Chúc bạn sử dụng vui vẻ,</p><p><span class='il'>Mini-ERP</span> </p></div></div></div><div class='yj6qo'></div></div>";
		return msgBody;
	}
	
	private void sendGoogleMailApi(String toAddress, String subject, String msgBody)
			throws IOException {
		String fromAddress = userService.getCurrentUser().getEmail();
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddress));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));
			msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
			msg.setContent(msgBody, "text/html");
			Transport.send(msg);
		} catch (AddressException addressException) {
		} catch (MessagingException messageException) {
		}
	}

	@Override
	public boolean insertInviteToken(String userEmail, System_Organization organization) {
		if(organization.getAdmin().equals(userService.getCurrentUser().getEmail())) {
			if(organization.getInviteList() == null)
				organization.setInviteList(new ArrayList<String>());
			organization.getInviteList().add(userEmail);
			Invite_Token token = new Invite_Token();
			token.setAccount(userEmail);
			token.setOrganizationId(organization.getId());
			token.setOrganizationName(organization.getName());
			token.setOrganizationAdmin(organization.getAdmin());
			Date expireDate;
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.roll(Calendar.DATE, 3);
			expireDate = c.getTime();
			token.setExpireDate(expireDate);
			ofy().save().entity(organization);
			ofy().save().entity(token);
			//Send invite mail
			try {
				String subject = "Mời bạn tham gia tổ chức công việc với Mini Task";
				sendGoogleMailApi(userEmail, subject, generateInviteMail(organization));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Invite_Token> retrieveInviteTokens(String email) {
		List<Invite_Token> inviteToken = ofy().load().type(Invite_Token.class).filter("account" , email).list();
		List<Invite_Token> result = new ArrayList<Invite_Token>();
		result.addAll(inviteToken);
		return result;
	}

	@Override
	public System_Organization inviteTokenAccepted(Invite_Token token) {
		System_Organization organization = ofy().load().type(System_Organization.class).id(token.getOrganizationId()).now();
		System_Account account = ofy().load().type(System_Account.class).id(token.getAccount()).now();
		if(organization != null) {
			organization.getUserList().add(account.getEmail());
			organization.getInviteList().remove(account.getEmail());
			account.getOrganizationList().add(organization.getId());
			ofy().save().entity(organization);
			ofy().save().entity(account);
			ofy().delete().entity(token);
			return organization;
		}
		ofy().delete().entity(token);
		return null;
	}

	@Override
	public void inviteTokenDenied(Invite_Token token) {
		// TODO Auto-generated method stub
		
	}
	
}

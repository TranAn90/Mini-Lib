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
package com.softlink.minilib.client.rpc;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Account;
import com.softlink.minilib.shared.System_Organization;

public interface SystemService extends RemoteService {
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static SystemServiceAsync instance;
		public static SystemServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(SystemService.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint("/system");
			}
			return instance;
		}
	}
	
	System_Organization insertOrganization(System_Organization organization);
	
	boolean updateOrganization(System_Organization organization);
	
	System_Organization retrieveOrganization(Long id);
	
	boolean insertAccount(System_Account account);
	
	boolean updateAccount(System_Account account);
	
	System_Account retrieveAccount(String email);
	
	boolean insertInviteToken(Invite_Token token);
	
	List<Invite_Token> retrieveInviteTokens(String email);
	
}

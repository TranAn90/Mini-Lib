package com.softlink.minilib.server.filter;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.softlink.minilib.shared.System_Account;

public class MiniTaskFilter implements Filter {

	private static final UserService userService = UserServiceFactory.getUserService();
	
	private System_Account account;

	@Override
	public void init(FilterConfig config) throws ServletException {
//		System.out.println("System: init filter");
	}

	@Override
	public void destroy() {
//		System.out.println("System: destroy filter");
	}
	
	private boolean checkPermission() {
		if(userService.isUserLoggedIn()) {
			account = ofy().load().type(System_Account.class)
					.id(userService.getCurrentUser().getEmail()).now();
			if(account == null) {
				System.out.println("Mini-Task Filter: system account is null!");
				return false;
			}
			if(account.getOrganizationCurrently() == null) {
				System.out.println("Mini-Task Filter: system account's organization is null!");
				return false;
			}
			return true;
		}
		System.out.println("Mini-Task Filter: not login!");
		return false;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		if(checkPermission()) {
			NamespaceManager.set(account.getOrganizationCurrently());
			filterChain.doFilter(request, response);
		}
		else {
			response.sendError(401);
		}
	}
	
}

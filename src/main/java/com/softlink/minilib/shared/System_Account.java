package com.softlink.minilib.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity(name = "system_account")
@Index
public class System_Account implements Serializable{

	@Id
	private String email;
	@Unindex
	private String organizationCurrently;
	@Unindex
	private List<String> organizationList = new ArrayList<String>();
	
	public System_Account() {
		super();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganizationCurrently() {
		return organizationCurrently;
	}

	public void setOrganizationCurrently(String organizationCurrently) {
		this.organizationCurrently = organizationCurrently;
	}

	public List<String> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<String> organizationList) {
		this.organizationList = organizationList;
	}
	
	public void addOrganization(String organization) {
		this.organizationList.add(organization);
	}
	
	public void removeOrganization(String organization) {
		this.organizationList.remove(organization);
	}
	
	@Override
	public boolean equals(Object o) {
		System_Account data = (System_Account) o;
		if (data.email.equals(this.email))
			return true;
		return false;
	}
	
}

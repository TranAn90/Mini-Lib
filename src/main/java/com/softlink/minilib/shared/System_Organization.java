package com.softlink.minilib.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity(name = "system_organization")
@Index
public class System_Organization implements Serializable {

	@Id
	private String id;
	private String name;
	private String description;
	private String admin;
	@Unindex
	private List<String> userList = new ArrayList<String>();
	@Unindex
	private List<String> inviteList = new ArrayList<String>();
	
	public System_Organization() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	
	public void addUser(String user) {
		this.userList.add(user);
	}
	
	public void removeUser(String user) {
		this.userList.remove(user);
	}

	public List<String> getInviteList() {
		return inviteList;
	}

	public void setInviteList(List<String> inviteList) {
		this.inviteList = inviteList;
	}
	
	public void addInvite(String invite) {
		this.inviteList.add(invite);
	}
	
	public void removeInvite(String invite) {
		this.inviteList.remove(invite);
	}
	
	@Override
	public boolean equals(Object o) {
		System_Organization data = (System_Organization) o;
		if (data.id.equals(this.id))
			return true;
		return false;
	}

}

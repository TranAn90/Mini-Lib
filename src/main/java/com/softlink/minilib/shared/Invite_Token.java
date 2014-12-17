package com.softlink.minilib.shared;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity(name = "invite_token")
@Index
public class Invite_Token implements Serializable {
	
	@Id
	private Long id;
	private String account;
	private String organizationId;
	private String organizationName;
	private String organizationAdmin;
	private Date expireDate;
	
	public Invite_Token() {
		super();	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	public String getOrganizationAdmin() {
		return organizationAdmin;
	}

	public void setOrganizationAdmin(String organizationAdmin) {
		this.organizationAdmin = organizationAdmin;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@Override
	public boolean equals(Object o) {
		Invite_Token data = (Invite_Token) o;
		if (data.id.equals(this.id))
			return true;
		return false;
	}
	
}

package com.cablesfb.model;

import java.util.Date;

public class Registers {
	private int id;
	private Date date;
	private String description;
	private String user;
	private String action;

	public Registers() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Registers [id=" + id + ", date=" + date + ", description=" + description + ", user=" + user
				+ ", action=" + action + "]";
	}

}

package com.everythingbiig.cuando.model;

public class Reminder {

	private long id; // Might need to rename this _id
	private String name;
	private String description;
	private ReminderType type;
	private boolean completed;

	public Reminder() {
		type = ReminderType.None;
		completed = false;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public ReminderType getType() {
		return type;
	}
	public void setType(ReminderType type) {
		this.type = type;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean acknowledged) {
		this.completed = acknowledged;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(name != null) {
			sb.append(name);
			if(description != null) {
				sb.append(" - ").append(description);
			}
		} else if(description != null) {
			sb.append(description);
		}
		sb.append("(").append(type.name()).append(")");
		return sb.toString();
	}
}

package com.everythingbiig.cuando.model;


public enum ReminderType {
	None(1), AtSpecificTime(2), BeforeArriving(3), OnArrival(4), AfterArrival(5);
	
	private int id;
	ReminderType(int index) {
		this.id = index;
	}

	public int getId() {
		return this.id;
	}

	public int getPosition() {
		return this.ordinal();
	}

	public static ReminderType getById(int id) {
		ReminderType atIndex = null;
		for(ReminderType t : ReminderType.values()) {
			if(t.id == id) {
				atIndex = t;
				break;
			}
		}
		return atIndex;
	}
}
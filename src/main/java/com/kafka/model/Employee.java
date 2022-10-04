package com.kafka.model;

public class Employee {

	String nationalId;
	String name;
	String amount;

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String[] toArray() {
		String[] str = new String[3];
		str[0] = nationalId;
		str[1] = name;
		str[2] = amount;
		return str;
	}

	@Override
	public String toString() {
		return nationalId + "," + name + "," + amount;
	}

}

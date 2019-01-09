package com.id.dynamointegration.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address1;
	private String city;
	private String state;
	@DynamoDBAttribute
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	@DynamoDBAttribute
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@DynamoDBAttribute
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}

package com.vokal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle
{
	@JsonProperty
	String registration;
	
	@JsonProperty
	String color;
	
	@JsonProperty
	SizeType sizeType;
	
	public String getRegistration()
	{
		return registration;
	}
	public void setRegistration(String registration)
	{
		this.registration = registration;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public SizeType getSizeType()
	{
		return sizeType;
	}
	public void setSizeType(SizeType sizeType)
	{
		this.sizeType = sizeType;
	}
}

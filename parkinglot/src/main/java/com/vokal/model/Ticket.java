package com.vokal.model;

import java.util.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket
{
	Slot slot;
	String registration;
	
	@JsonIgnore
	DateTime date;
	
	String dateTime;
	ActionType actionType;
	
	@JsonProperty
	public ActionType getActionType()
	{
		return actionType;
	}
	
	public void setActionType(ActionType actionType)
	{
		this.actionType = actionType;
	}
	@JsonProperty
	public Slot getSlot()
	{
		return slot;
	}
	public void setSlot(Slot slot)
	{
		this.slot = slot;
	}
	
	@JsonProperty
	public String getRegistration()
	{
		return registration;
	}
	public void setRegistration(String registration)
	{
		this.registration = registration;
	}
	
	@JsonProperty
	public String getDateString()
	{
		return dateTime;
	}
	
	public DateTime getDate()
	{
		return date;
	}
	public void setDate(DateTime date)
	{
		this.date = date;
		this.dateTime = date.toString();
	}
	
}

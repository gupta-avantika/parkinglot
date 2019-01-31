package com.vokal.mappings;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vokal.model.Ticket;

@Component
public class RegistrationToTicketMapping
{
	private Map<String, Ticket> map;
	
	public RegistrationToTicketMapping()
	{
		map = new HashMap<>();
	}
	
	public void put(Ticket ticket)
	{
		map.put(ticket.getRegistration(), ticket);
	}
	
	public Ticket remove(String reg)
	{
		return map.remove(reg);
	}
	
	public Ticket get(String registration)
	{
		return map.get(registration);
	}
}

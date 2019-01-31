package com.vokal.mappings;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vokal.model.Vehicle;

@Component
public class RegistrationToVehicleMap
{
	private Map<String, Vehicle> map;
	
	public RegistrationToVehicleMap()
	{
		map = new HashMap<>();
	}
	
	public void put(Vehicle vehicle)
	{
		map.put(vehicle.getRegistration(), vehicle);
	}
	
	public Vehicle remove( String reg)
	{
		return map.remove(reg);
	}
	
	public Vehicle get(String registration)
	{
		return map.get(registration);
	}
}

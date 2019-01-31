package com.vokal.mappings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.vokal.model.Vehicle;

@Component
public class colorToVehicleMap
{
	Map<String, Map<String, Vehicle>> map;
	
	public colorToVehicleMap()
	{
		map = new HashMap<>();
	}
	
	public Set<Vehicle> getVehiclefromColor(String color)
	{
		if(map.get(color) == null)
		{
			return null;
		}
		
		Set<Vehicle> set = new HashSet<>();
		
		for(Entry<String, Vehicle> e : map.get(color).entrySet())
		{
			set.add(e.getValue());
		}
		
		return set;
	}
	
	public void putVehicleWithColor(Vehicle vehicle)
	{
		String color = vehicle.getColor();
		if(map.get(color) == null)
		{
			Map<String, Vehicle> innerMap = new HashMap<>();
			innerMap.put(vehicle.getRegistration(), vehicle);
			map.put(vehicle.getColor(), innerMap);
		}
		else
			map.get(vehicle.getColor()).put(vehicle.getRegistration(), vehicle);
	}
	
	public void removeVehicle(Vehicle vehicle)
	{
		if(map.get(vehicle.getColor()) != null)
		{
			map.get(vehicle.getColor()).remove(vehicle.getRegistration());
		}
	}
}

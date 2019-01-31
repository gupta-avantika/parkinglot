package com.vokal.mappings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import java.util.Map.Entry;

import com.vokal.model.SizeType;
import com.vokal.model.Vehicle;

@Component
public class SizeToVehicleMapping
{
	Map<SizeType, Map<String, Vehicle>> map;
	
	public SizeToVehicleMapping()
	{
		map = new HashMap<>();
	}
	
	public Set<Vehicle> getVehiclefromColor(SizeType sz)
	{
		if(map.get(sz) == null)
		{
			return null;
		}
		
		Set<Vehicle> set = new HashSet<>();
		
		for(Entry<String, Vehicle> e : map.get(sz).entrySet())
		{
			set.add(e.getValue());
		}
		
		return set;
	}
	
	public void putVehicleWithColor(Vehicle vehicle)
	{
		SizeType sz = vehicle.getSizeType();
		if(map.get(sz) == null)
		{
			Map<String, Vehicle> innerMap = new HashMap<>();
			innerMap.put(vehicle.getRegistration(), vehicle);
			map.put(vehicle.getSizeType(), innerMap);
		}
		else
			map.get(vehicle.getSizeType()).put(vehicle.getRegistration(), vehicle);
	}
	
	public void removeVehicle(Vehicle vehicle)
	{
		if(map.get(vehicle.getSizeType()) != null)
		{
			map.get(vehicle.getSizeType()).remove(vehicle.getRegistration());
		}
	}
}

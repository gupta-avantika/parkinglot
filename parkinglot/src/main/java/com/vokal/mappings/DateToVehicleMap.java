package com.vokal.mappings;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Map.Entry;

import com.vokal.model.Vehicle;

@Component
public class DateToVehicleMap
{
	TreeMap<DateTime, Vehicle> map;
	
	public DateToVehicleMap()
	{
		map = new TreeMap<>();
	}
	
	public void put(DateTime date, Vehicle v)
	{
		map.put(date, v);
		//System.out.println("added + " + date.toString());
	}
	
	public Vehicle remove(DateTime date)
	{
		return map.remove(date);
	}
	
	public Set<Vehicle> getVehicleBetweenDates(DateTime date1, DateTime date2)
	{
		Set<Vehicle> set = new HashSet<>();
		
		for(Entry<DateTime, Vehicle> e: map.entrySet())
		{
			if(date1.compareTo(e.getKey()) == -1 && date2.compareTo(e.getKey()) == 1)
			{
				set.add(e.getValue());
				continue;
			}
			//System.out.println("dates not matched " + date1.toString() + "  " + e.getKey() + "  " + date2.toString());
		}
		return set;
	}
}

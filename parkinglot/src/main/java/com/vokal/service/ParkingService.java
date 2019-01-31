package com.vokal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vokal.mappings.DateToVehicleMap;
import com.vokal.mappings.RegistrationToTicketMapping;
import com.vokal.mappings.RegistrationToVehicleMap;
import com.vokal.mappings.SizeToVehicleMapping;
import com.vokal.mappings.colorToVehicleMap;
import com.vokal.model.ActionType;
import com.vokal.model.SizeType;
import com.vokal.model.Slot;
import com.vokal.model.Ticket;
import com.vokal.model.Vehicle;
import com.vokal.parkinglot.ParkingLot;

@Service
public class ParkingService
{
	@Autowired
	ParkingLot parkingLot;
	
	@Autowired
	RegistrationToVehicleMap registrationToVehicleMap;
	
	@Autowired
	colorToVehicleMap colorToVehicleMap;
	
	@Autowired
	SizeToVehicleMapping sizeToVehicleMapping;
	
	@Autowired
	DateToVehicleMap dateToVehicleMap;
	
	@Autowired
	RegistrationToTicketMapping registrationToTicketMapping;
	
	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy mm:ss");
	
	public Ticket park(Vehicle vehicle)
	{
		if(registrationToVehicleMap.get(vehicle.getRegistration()) != null)
			throw new RuntimeErrorException(null, " registration number " + 
						vehicle.getRegistration() + " already exists!!!!");
		
		Slot slot = parkingLot.getFreeSlot(vehicle.getSizeType());
		slot.setTaken(true);
		
		registrationToVehicleMap.put(vehicle);
		colorToVehicleMap.putVehicleWithColor(vehicle);
		sizeToVehicleMapping.putVehicleWithColor(vehicle);
		Ticket ticket = populateTicketData(slot, vehicle);
		registrationToTicketMapping.put(ticket);
		dateToVehicleMap.put(ticket.getDate(), vehicle);
		return ticket;
	}
	
	public Ticket unPark(String registration)
	{
		Ticket ticket = registrationToTicketMapping.remove(registration);
		ticket.setActionType(ActionType.UNPARK);
		Slot sl = ticket.getSlot();
		sl.setTaken(false);
		Vehicle vh = registrationToVehicleMap.remove(registration);
		colorToVehicleMap.removeVehicle(vh);
		sizeToVehicleMapping.removeVehicle(vh);
		dateToVehicleMap.remove(ticket.getDate());
		ticket.setDate(new DateTime());
		return ticket;
	}
	
	public Vehicle getVehicleByRegistration(String registration)
	{
		return registrationToVehicleMap.get(registration);
	}
	
	public Set<Vehicle> getVehiclesByColor(String color)
	{
		return colorToVehicleMap.getVehiclefromColor(color);
	}
	
	public Set<Vehicle> getVehiclesBySize(SizeType sz)
	{
		return sizeToVehicleMapping.getVehiclefromColor(sz);
	}
	
	public Set<Vehicle> getAllVehiclesBetweenDates(DateTime d1, DateTime d2)
	{
		return dateToVehicleMap.getVehicleBetweenDates(d1, d2);
	}
	
	private Ticket populateTicketData(Slot slot, Vehicle vehicle)
	{
		Ticket t = new Ticket();
		t.setSlot(slot);
		t.setRegistration(vehicle.getRegistration());
		t.setDate(new DateTime());
		t.setActionType(ActionType.PARK);
		return t;
	}
}

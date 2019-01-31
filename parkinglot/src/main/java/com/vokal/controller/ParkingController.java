package com.vokal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vokal.model.SizeType;
import com.vokal.model.Ticket;
import com.vokal.model.Vehicle;
import com.vokal.service.ParkingService;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.vokal")
@RequestMapping("/parkinglot")
public class ParkingController
{
	@Autowired
	ParkingService parkingService;
	
	@RequestMapping("/park")
	 public Ticket park(@RequestBody Vehicle vehicle)
	 {
		 return parkingService.park(vehicle);
	 }
	
	@RequestMapping("/getVehicleByRegistration")
	public Vehicle getVehicleByRegistration(@RequestParam String registration)
	{
		return parkingService.getVehicleByRegistration(registration);
	}
	
	@RequestMapping("/getVehiclesByColor")
	public Set<Vehicle> getVehicleByColor(@RequestParam String color)
	{
		return parkingService.getVehiclesByColor(color);
	}
	
	@RequestMapping("/getVehiclesBySize")
	public Set<Vehicle> getVehicleBySize(@RequestParam SizeType sz)
	{
		return parkingService.getVehiclesBySize(sz);
	}
	
	@RequestMapping("/unpark")
	public Ticket unpark(String registration)
	{
		return parkingService.unPark(registration);
	}
	
	@RequestMapping("/getVehiclesBtwDates")
	public Set<Vehicle> getAllVehiclesBtwDate(@RequestParam String ds1, @RequestParam String ds2) throws ParseException
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		
		DateTime d1 = DateTime.parse(ds1, formatter);
		DateTime d2 = DateTime.parse(ds2, formatter);
		
		return parkingService.getAllVehiclesBetweenDates(d1, d2);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ParkingController.class, args);
	}
}

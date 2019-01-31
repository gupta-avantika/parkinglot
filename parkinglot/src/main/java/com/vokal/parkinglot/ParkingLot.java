package com.vokal.parkinglot;

import org.springframework.stereotype.Component;

import com.vokal.model.SizeType;
import com.vokal.model.Slot;

@Component
public class ParkingLot
{
	private Slot [][] parkinglot;
	
	public ParkingLot()
	{
		parkinglot = new Slot[10][100];
		initizaLize();
	}
	
	/**
	 * assumptions made
	 * levels 10
	 * each level has 90 slots
	 * 1 - 40 are small slots
	 * 40 - 80 are large slots
	 * 20 - big slots
	 * */
	private void initizaLize()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if(j < 40)
				{
					Slot s = new Slot(j, SizeType.SMALL, false);
					parkinglot[i][j] = s;
				}
				else if(j >= 40 && j < 80)
				{
					Slot s = new Slot(j, SizeType.MEDIUM, false);
					parkinglot[i][j] = s;
				}
				else
				{
					Slot s = new Slot(j, SizeType.LARGE, false);
					parkinglot[i][j] = s;
				}
				
			}
		}
	}
	
	public Slot getFreeSlot(SizeType sizeType)
	{
		for(int i = 0; i < 10; i++)
		{
			switch(sizeType)
			{
				case SMALL: {
					for(int j = 0; j < 40; j++)
					{
						if(!parkinglot[i][j].isTaken())
						{
							return parkinglot[i][j];
						}
					}
					break;
				}
				case MEDIUM: {
					for(int j = 40; j < 80; j++)
					{
						if(!parkinglot[i][j].isTaken())
						{
							return parkinglot[i][j];
						}
					}
					break;
				}
				case LARGE: {
					for(int j = 80; j < 100; j++)
					{
						if(!parkinglot[i][j].isTaken())
						{
							return parkinglot[i][j];
						}
					}
					break;
				}
			}
		}
		
		return null;
	}
}

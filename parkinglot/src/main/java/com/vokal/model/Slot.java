package com.vokal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Slot
{
	int slotNumber;
	
	SizeType sizeType;
		
	boolean isTaken;
	
	public Slot() {};
	
	public Slot(int sNum, SizeType stype, boolean isT)
	{
		slotNumber = sNum;
		sizeType = stype;
		isTaken = isT;
	}
	
	@JsonProperty
	public int getSlotNumber()
	{
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber)
	{
		this.slotNumber = slotNumber;
	}
	
	@JsonProperty
	public SizeType getSizeType()
	{
		return sizeType;
	}

	public void setSizeType(SizeType sizeType)
	{
		this.sizeType = sizeType;
	}
	
	@JsonProperty
	public boolean isTaken()
	{
		return isTaken;
	}

	public void setTaken(boolean isTaken)
	{
		this.isTaken = isTaken;
	}
}

package com.skilldistillery.jets.entities;

public class ScoutPlane extends Jet implements CargoCarrier,CombatReady{

	public ScoutPlane() {
	}
	
	public ScoutPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	
	@Override
	public void fight() {
		System.out.println("Golden retrievers are ready to fight, found a lot of squirrels!");
	}

	@Override
	public void loadCargo() {
		System.out.println("Golden retriever can carry lots of toys!");
	}

	@Override
	public void fly() {
		System.out.println("ScoutPlane details: "+getModel()+" "+getSpeed()
		+" "+getRange()+" "+getPrice());
		System.out.println("   ---> The amout of time it can fly(hour): "+ getRange()/getSpeed());		
	}
	
	@Override
	public String toString() {
		return "ScoutPlane [model=" + getModel() + ", speed=" + getSpeed() + ", range=" + getRange() + ", price=" + getPrice() + "]";
	}

}

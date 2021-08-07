package com.skilldistillery.jets.entities;

public class CargoPlane extends Jet implements CargoCarrier{

	public CargoPlane() {
	}
	
	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	
	@Override
	public void fly() {
		System.out.println("CargoPlane details: "+getModel()+" "+getSpeed()
		+" "+getRange()+" "+getPrice());
		System.out.println("   ---> The amout of time it can fly(hour): "+ getRange()/getSpeed());
	}
	
	@Override
	public void loadCargo() {
		System.out.println("Huskies can carry tons of bones!");
	}
	
	@Override
	public String toString() {
		return "CargoPlane [model=" + getModel() + ", speed=" + getSpeed() + ", range=" + getRange() + ", price=" + getPrice() + "]";
	}
}

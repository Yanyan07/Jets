package com.skilldistillery.jets.entities;

public class FighterJet extends Jet implements CombatReady{

	public FighterJet() {
	}
	
	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	
	@Override
	public void fly() {
		System.out.println("FighterJet details: "+getModel()+" "+getSpeed()
		+" "+getRange()+" "+getPrice());
		System.out.println("   ---> The amout of time it can fly(hour): "+ getRange()/getSpeed());
	}
	
	@Override
	public void fight() {
		System.out.println("Labradors are ready to fight, going to eat a lot!");
	}

	@Override
	public String toString() {
		return "Fighter [model=" + getModel() + ", speed=" + getSpeed() + ", range=" + getRange() + ", price=" + getPrice() + "]";
	}

}

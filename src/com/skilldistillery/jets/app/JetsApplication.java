package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.entities.CargoCarrier;
import com.skilldistillery.jets.entities.CargoPlane;
import com.skilldistillery.jets.entities.CombatReady;
import com.skilldistillery.jets.entities.FighterJet;
import com.skilldistillery.jets.entities.Jet;
import com.skilldistillery.jets.entities.ScoutPlane;

public class JetsApplication {
	private AirField airField;
	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		JetsApplication obj = new JetsApplication();
		obj.run();
		obj.sc.close();
	}
	
	public void run() {
		String fileName = "Jets.txt";
		airField = new AirField();
		readName(fileName);
		
		boolean keepGoing = true;
		while(keepGoing) {
			showMenu();
			System.out.print("Please enter your choice: ");
			System.out.println();
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				listFleet();
				break;
			case 2:
				flyAllJets();
				break;
			case 3:
				fastestJet();
				break;
			case 4:
				longestRange();
				break;
			case 5:
				loadAllCargo();
				break;
			case 6:
				dogFight();
				break;
			case 7:
				addJet();
				break;
			case 8:
				removeJet();
				break;
			case 9:
				System.out.println("You are exiting, have a good day!");
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid input, please enter a number(1-9):");
				break;	
			}
		}
	}
	
	private void showMenu() {
		System.out.println("---------------------------------------");
		System.out.println("|      1.List fleet                   |");
		System.out.println("|      2.Fly all jets                 |");
		System.out.println("|      3.View fastest jet             |");
		System.out.println("|      4.View jet with longest range  |");
		System.out.println("|      5.Load all Cargo Jets          |");
		System.out.println("|      6.Dogfight!                    |");
		System.out.println("|      7.Add a jet to Fleet           |");
		System.out.println("|      8.Remove a jet from Fleet      |");
		System.out.println("|      9.Quit                         |");
		System.out.println("---------------------------------------");
		System.out.println();
	}
	
	private void listFleet() {
		for (Jet jet : airField.getJets()) {
			System.out.println(jet);
		}
	}
	private void flyAllJets() {
		for(Jet jet : airField.getJets()) {
			jet.fly();
		}
	}
	private void fastestJet() {
		double maxSpeed = Double.MIN_VALUE;
		Jet maxJet = null;
		for(Jet jet : airField.getJets()) {
			if(jet.getSpeed() > maxSpeed) {
				maxSpeed = jet.getSpeed();
				maxJet = jet;
			}
		}
		System.out.println("Fastest jet: "+ maxJet);
	}
	private void longestRange() {
		int maxRange = Integer.MIN_VALUE;
		Jet maxJet = null;
		for(Jet jet : airField.getJets()) {
			if(jet.getRange() > maxRange) {
				maxRange = jet.getRange();
				maxJet = jet;
			}
		}
		System.out.println("longest range jet: "+ maxJet);
	}
	private void loadAllCargo() {
		for(Jet jet : airField.getJets()) {
			if(jet instanceof CargoCarrier) {
				((CargoCarrier) jet).loadCargo();
			}
		}
	}
	private void dogFight() {
		for(Jet jet : airField.getJets()) {
			if(jet instanceof CombatReady) {
				((CombatReady) jet).fight();
			}
		}
	}
	private void addJet() {
		System.out.println("Please enter the type of jet: cargo, combat or scout?");
		String type = sc.next();
		System.out.print("Enter the model: ");
		String model = sc.next();
		System.out.print("Enter the speed: ");
		double speed = sc.nextDouble();
		System.out.print("Enter the range: ");
		int range = sc.nextInt();
		System.out.print("Enter the price: ");
		long price = sc.nextLong();
		
		Jet jet = null;
		if("cargo".equalsIgnoreCase(type)) {
			jet = new CargoPlane(model, speed, range, price);
		}else if("combat".equalsIgnoreCase(type)) {
			jet = new FighterJet(model, speed, range, price);
		}else if("scout".equalsIgnoreCase(type)) {
			jet = new ScoutPlane(model, speed, range, price);
		}
		airField.getJets().add(jet);
	}
	private void removeJet() {
		List<Jet> jets = airField.getJets();
		for (int i=0; i<jets.size(); i++) {
			System.out.println(i+": "+ jets.get(i));
		}
		while(true) {
			System.out.print("Enter the number(0-"+ (jets.size()-1)+") of jet to be deleted: ");
			int index = sc.nextInt();
			if(index>=0 && index<jets.size()) {
				jets.remove(index);
				break;
			}else {
				System.out.println("Invalid number, please choose again: ");
			}
		}
	}
	
	private List<Jet> readName(String fileName) {
		List<Jet> jetList = airField.getJets();
		try(BufferedReader bIn = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line=bIn.readLine()) != null) {
				String[] strs = line.split(",");
				Jet jet = null;
				if("cargo".equalsIgnoreCase(strs[0])) {
					jet = new CargoPlane(strs[1], Double.parseDouble(strs[2]),
							Integer.parseInt(strs[3]), Long.parseLong(strs[4]));
				}else if("combat".equalsIgnoreCase(strs[0])) {
					jet = new FighterJet(strs[1], Double.parseDouble(strs[2]),
							Integer.parseInt(strs[3]), Long.parseLong(strs[4]));
				}else if("scout".equalsIgnoreCase(strs[0])) {
					jet = new ScoutPlane(strs[1], Double.parseDouble(strs[2]),
							Integer.parseInt(strs[3]), Long.parseLong(strs[4]));
				}
				jetList.add(jet);
			}
		}catch(IOException e) {
			System.err.println(e);
		}
		return jetList;
	}
}

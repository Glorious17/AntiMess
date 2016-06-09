package com.antimess.resources;

import java.awt.List;
import java.util.ArrayList;

public class AntiMessDao implements AntiMessDaoInterface {
	
	private static ArrayList <String> Datenbank = new ArrayList<String>();
	private static List ObjektNamen = new List();
	
	public AntiMessDao(){
		ObjektNamen.add("Buch");
		ObjektNamen.add("Spiel");
		ObjektNamen.add("CollageBlock");
		ObjektNamen.add("Flasche");
	}

	@Override
	public boolean isIn(String data) {
		for(int i = 0; i<Datenbank.size(); i++){
			if(Datenbank.get(i).equals(data))
				return true;
		}
		return false;
	}

	@Override
	public boolean push(String data) {
		if(isIn(data))
			return false;
		Datenbank.add(data);
		return true;
	}

	@Override
	public String pull(int i) {
		return Datenbank.get(i);
	}
	
	public int length() {
		return Datenbank.size();
	}
	
// Just testfunctions
	
	public List objektReturn(){
		return ObjektNamen;
	}
}

package com.antimess.resources;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class AntiMessBusiness implements AntiMessBusinessInterface{

	private static AntiMessDao dao;
	
	public AntiMessBusiness(){
		dao = new AntiMessDao();
	}
	
	public boolean anmelden(String name, String passwort) {
		name.toLowerCase();
		String[] input = {name, passwort};
		for(String i : input)
			try {
				if(dao.isIn(i, "Benutzer", 2))
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	public boolean registrieren(String name, String passwort) {
		name.toLowerCase();
		String[] input = {name, passwort};
		try {
			return dao.push(input, "Benutzer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void close(){
		dao.close();
	}

}

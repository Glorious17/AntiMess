package com.antimess.resources;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AntiMessBusiness implements AntiMessBusinessInterface{

	private static AntiMessDao dao;
	
	public AntiMessBusiness(){
		dao = new AntiMessDao();
	}
	
	public boolean anmelden(String name, String passwort) {
		name.toLowerCase();
		try {
			if(!dao.isInUser(name))
				return false;
			
			try (ResultSet rs = dao.pullUser(name)) {
				rs.next();
				if(passwort.equals(rs.getString(2))){
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean registrieren(String name, String passwort) {
		name.toLowerCase();
		String[] input = {name, passwort};
		try {
			return dao.pushUser(input);
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

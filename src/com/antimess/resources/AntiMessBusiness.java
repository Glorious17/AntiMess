package com.antimess.resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AntiMessBusiness implements AntiMessBusinessInterface{

	private static AntiMessDao dao;
	
	public AntiMessBusiness(){
		dao = new AntiMessDao();
	}
	
	@Override
	public void setSession(String name, String id){
		try {
			dao.setSession(name, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isLogedIn(String id){
		try {
			if(dao.isOnline(id))
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean anmelden(String name, String passwort) {
		name.toLowerCase();
		try {
			if(!dao.isInUser(name))
				return false;
			
			try (ResultSet rs = dao.pullUser(name)) {
				if(rs.next() && passwort.equals(rs.getString(2))){
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
	
	public ArrayList<String> getItems(String name){
		ResultSet rs;
		ArrayList<String>ls = new ArrayList<String>();
		try {
			rs = dao.pullItem(name);
			while(rs.next()){
				ls.add("{'GegenstandName':  '" + rs.getString(1) + "', 'Lagerort': '" + rs.getString(2) + "', 'LagerDatum': '"
						+ rs.getDate(3) + "', 'Icon': '" + rs.getString(4) + "}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	@Override
	public boolean registrieren(String name, String passwort, String nickname) {
		name.toLowerCase();
		String[] input = {name, passwort, nickname};
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

package com.antimess.resources;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AntiMessBusiness implements AntiMessBusinessInterface{

	private static AntiMessDao dao;
	
	public AntiMessBusiness(){
		dao = new AntiMessDao();
	}
	
	@Override
	public boolean forgotToLogoff(String username){
		try {
			ResultSet rs = dao.getSessionName(username);
			if(rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void setSession(String name, String id){
		try {
			dao.setSession(name, id);
		} catch (SQLException e) {
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
	public void deleteItem(int id){
		try {
			dao.deleteItem(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	@Override
	public void updateItemPic(int id, String url){
		try {
			dao.updateItemPic(id, url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int addItem(String name, Date date, String url, String lagerort, String username, String keyword){
		int output;
		try {
			ResultSet rs = dao.addItem(name, date, url, lagerort, username, keyword);
			if(rs.next())
				output = rs.getInt(1);
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return output;
	}
	
	@Override
	public ArrayList<String> getItems(String name){
		ResultSet rs;
		ArrayList<String>ls = new ArrayList<String>();
		try {
			rs = dao.pullItem(name);
			while(rs.next()){
				ls.add("{\"GegenstandName\":  \"" + rs.getString(1) + "\", \"Lagerort\": \"" + rs.getString(2) + "\", \"LagerDatum\": \""
						+ rs.getDate(3) + "\", \"Icon\": \"" + rs.getString(4) + "\", \"Keywords\": \"" + rs.getString(5)
						+ "\", \"ID\": " + rs.getInt(6) + ", \"Berechtigt\": \"" + rs.getString(7) + "\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	@Override
	public ArrayList<String> getItems(int id){
		ResultSet rs;
		ArrayList<String>ls = new ArrayList<String>();
		try {
			rs = dao.pullItem(id);
			while(rs.next()){
				for(int zaehler = 1; zaehler < 8; zaehler++)
				switch(zaehler){
				case 3:
					ls.add(rs.getDate(zaehler) + "");
					break;
				default:
					ls.add(rs.getString(zaehler));
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public ArrayList<String> getLagerort(String name){
		ArrayList<String> output = null;
		try {
			ResultSet rs = dao.getLagerortBesitz(name);
			output = new <String> ArrayList();
			while(rs.next()){
				output.add(rs.getString(1));
			}
			rs = dao.getLagerortBerechtigt(name);
			while(rs.next()){
				output.add("Berechtigter Zugriff von " + rs.getString(2) + ": " + rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	@Override
	public int getLagerortID(String besitzer, String name){
		return dao.getLagerortID(name, besitzer);
	}
	
	@Override
	public void updateLagerortName(int id, String newName){
		try {
			dao.updateLagerortName(id, newName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean addLagerort(String name, String berechtigt, String user){
		try {
			return dao.addLagerort(name, berechtigt, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void deletePermission(int lagerID, String username){
		try {
			dao.deletePermission(lagerID, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addPermission(int lagerortID, String benutzer){
		try {
			dao.addPermission(lagerortID, benutzer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean registrieren(String name, String passwort, String nickname) {
		name.toLowerCase();
		String[] input = {name, passwort, nickname};
		try {
			return dao.pushUser(input);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean logout(String id){
		try {
			dao.deleteOnlineStatus(id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void logoutUser(String username){
		try {
			dao.userLogoff(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkAcc(String name, String id){
		try {
			ResultSet rs = dao.getSession(id);
			if(rs.next())
				if(name.equals(rs.getString(2)))
					return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public String getUserThroughId(String id){
		try {
			return dao.getUserThroughId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close(){
		dao.close();
	}

}

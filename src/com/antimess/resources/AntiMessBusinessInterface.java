package com.antimess.resources;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AntiMessBusinessInterface {
	public boolean anmelden(String name, String passwort) throws SQLException;
	void setSession(String name, String id);
	boolean isLogedIn(String id);
	boolean registrieren(String name, String passwort, String nickname);
	ArrayList<String> getItems(String name);
	boolean checkAcc(String name, String id);
	String getUserThroughId(String id);
	boolean logout(String id);
	boolean forgotToLogoff(String username);
	boolean addItem(String name, Date date, String url, String lagerort, String username, String keyword);
	void logoutUser(String username);
	boolean addLagerort(String name, String berechtigt, String user);
}

package com.antimess.resources;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AntiMessBusinessInterface {
	public boolean anmelden(String name, String passwort) throws SQLException;
	void setSession(String name, String id);
	boolean isLogedIn(String id);
	boolean registrieren(String name, String passwort, String nickname);
	ArrayList<String> getItems(String name);
	boolean checkAcc(String name, String id);
}

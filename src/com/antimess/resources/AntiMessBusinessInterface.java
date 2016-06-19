package com.antimess.resources;

import java.sql.SQLException;

public interface AntiMessBusinessInterface {
	public boolean anmelden(String name, String passwort) throws SQLException;
	void setSession(String name, String id);
	boolean isLogedIn(String id);
	boolean registrieren(String name, String passwort, String nickname);
}

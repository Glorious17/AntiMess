package com.antimess.resources;

import java.sql.SQLException;

public interface AntiMessBusinessInterface {
	public boolean anmelden(String name, String passwort) throws SQLException;
	public boolean registrieren(String name, String passwort);
}

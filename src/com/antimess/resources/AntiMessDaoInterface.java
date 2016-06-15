package com.antimess.resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AntiMessDaoInterface {

	ResultSet pullUser(String username) throws SQLException;
	boolean pushUser(Object[] data) throws SQLException;
	boolean isInUser(String username) throws SQLException;
	void setSession(String Username, String id) throws SQLException;
	boolean isOnline(String id) throws SQLException;
}

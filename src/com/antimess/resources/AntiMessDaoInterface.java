package com.antimess.resources;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AntiMessDaoInterface {

	ResultSet pullUser(String username) throws SQLException;
	boolean pushUser(Object[] data) throws SQLException;
	boolean isInUser(String username) throws SQLException;
	void setSession(String Username, String id) throws SQLException;
	boolean isOnline(String id) throws SQLException;
	ResultSet pullItem(String name) throws SQLException;
	ResultSet getSession(String id) throws SQLException;
	ResultSet getSessionName(String username) throws SQLException;
	String getUserThroughId(String id) throws SQLException;
	void deleteOnlineStatus(String id) throws SQLException;
	boolean addLagerort(String name, String berechtigt, String user) throws SQLException;
}

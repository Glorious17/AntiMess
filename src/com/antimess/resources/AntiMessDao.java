package com.antimess.resources;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;



public class AntiMessDao implements AntiMessDaoInterface {
	
	private DataSource ds = null;
	private Connection conn = null; 
	private Statement stmt = null;
	
	public AntiMessDao(){
		try {
			Context initCtx = new InitialContext();;
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/AntiMess");
			conn = ds.getConnection();
			stmt = conn.createStatement();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isInUser(String username) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT BenutzerName FROM Benutzer");
		while (rs.next()){
			if(rs.getString(1).equals(username))
				return true;
		}
		return false;
	}

	@Override
	public boolean pushUser(Object data[]) throws SQLException {
		String insertValues = "(";
		if(isInUser((String) data[0]))
			return false;
		
		for(int i = 0; i < 2; i++){
			switch(i){
			case 0:
				insertValues += "'" + (String) data[i] + "', ";
				break;
			case 1:
				insertValues += "'" + (String) data[i] + "' , DEFAULT)";
				break;
			}
		}
		stmt.executeUpdate("INSERT INTO Benutzer VALUES " + insertValues);
		return true;
	}
	
	@Override
	public void setSession(String username, String id) throws SQLException{
		stmt.executeUpdate("INSERT INTO Aktive_Session VALUES ('" + id + "', '" + username + "')");
	}
	
	@Override
	public boolean isOnline(String id) throws SQLException{
		ResultSet rs = stmt.executeQuery("SELECT * FROM Aktive_Session WHERE Session_ID = '" + id + "'");
		if(rs.next())
			return true;
		return false;
	}

	@Override
	public ResultSet pullUser(String username) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Benutzer WHERE BenutzerName = '" + username + "'");
		return rs;
	}
	
	public void close(){
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

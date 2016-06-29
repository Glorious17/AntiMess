package com.antimess.resources;

import java.sql.*;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;



public class AntiMessDao implements AntiMessDaoInterface {
	
	private DataSource ds = null;
	private Connection conn = null; 
	private Statement stmt = null;
	private PreparedStatement prpStPushUser, prpStSetSession, prpStDelOnlineStatus, prpStAddLagerort;
	private PreparedStatement prpStAddItem; 
	
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
		if(isInUser((String) data[0]))
			return false;

		prpStPushUser = conn.prepareStatement("INSERT INTO Benutzer VALUES (?, ?, ?)");
		prpStPushUser.setString(1, (String) data[0]);
		prpStPushUser.setString(2, (String) data[1]);
		prpStPushUser.setString(3, (String) data[2]);
		prpStPushUser.execute();
		return true;
	}
	
	@Override
	public void userLogoff(String username) throws SQLException{
		prpStDelOnlineStatus = conn.prepareStatement("DELETE FROM Aktive_Session WHERE BenutzerName_FK = ?");
		prpStDelOnlineStatus.setString(1, username);
		prpStDelOnlineStatus.execute();
	}
	
	@Override
	public ResultSet addItem(String name, java.sql.Date date, String url, String lagerort, String username, String keyword) throws SQLException{
		prpStAddItem = conn.prepareStatement("INSERT INTO Gegenstand VALUES (?, ?, ?, ?, DEFAULT, ?, ?)");
		prpStAddItem.setString(1, name);
		prpStAddItem.setDate(2, date);
		prpStAddItem.setString(3, url);
		if(lagerort.contains("Berechtigter Zugriff")){
			prpStAddItem.setInt(4, getLagerortID(lagerort.substring(lagerort.indexOf(':')+2), lagerort.substring(25,lagerort.indexOf(':'))));
		}else{
			prpStAddItem.setInt(4, getLagerortID(lagerort, username));
		}
		prpStAddItem.setString(5, username);
		prpStAddItem.setString(6, keyword);
		prpStAddItem.execute();
		return stmt.executeQuery("SELECT LAST_INSERT_ID() FROM Gegenstand");
	}
	
	@Override
	public void updateItemPic(int id, String url) throws SQLException{
		prpStAddItem = conn.prepareStatement("UPDATE Gegenstand SET Icon = '" + url + "' WHERE GegenstandID = " + id);
		prpStAddItem.execute();
	}
	
	@Override
	public int getLagerortID(String name, String user){
		try {
			ResultSet rs = stmt.executeQuery("SELECT LagerortID FROM Lagerort WHERE Lagerort_Name = '" + name + "' and Ersteller = '" + user + "'");
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public boolean addLagerort(String lagerortname, String berechtigt, String user) throws SQLException{
		if(getLagerortID(lagerortname, user) != -1)
			return false;
		prpStAddLagerort = conn.prepareStatement("INSERT INTO Lagerort VALUES (DEFAULT, ?, DEFAULT, ?)");
		prpStAddLagerort.setString(1, lagerortname);
		prpStAddLagerort.setString(2, user);
		prpStAddLagerort.execute();
		prpStAddLagerort = conn.prepareStatement("INSERT INTO Lagerort_Berechtigt VALUES (?, ?)");
		prpStAddLagerort.setInt(1, getLagerortID(lagerortname, user));
		prpStAddLagerort.setString(2, berechtigt);
		prpStAddLagerort.execute();
		return true;
	}
	
	@Override
	public ResultSet getLagerortBesitz(String user) throws SQLException{
		return stmt.executeQuery("SELECT Lagerort_Name FROM Lagerort WHERE Ersteller = '" + user + "'");
	}
	
	@Override
	public ResultSet getLagerortBerechtigt(String user) throws SQLException{
		return stmt.executeQuery("SELECT Lagerort_Name, Ersteller FROM Lagerort, Lagerort_Berechtigt WHERE Berechtigt = '" + user + "' and LagerortID_FK = LagerortID");
	}
	
	@Override
	public ResultSet pullItem(String name) throws SQLException{
		return stmt.executeQuery("SELECT GegenstandName, Lagerort_Name, Lagerdatum, Icon, Keywords FROM Gegenstand, Lagerort WHERE LagerortID_FK = LagerortID and BenutzerNameFK = '" + name + "'");
	}
	
	@Override
	public void setSession(String username, String id) throws SQLException{
		prpStSetSession = conn.prepareStatement("INSERT INTO Aktive_Session VALUES (?, ?)");
		prpStSetSession.setString(1, id);
		prpStSetSession.setString(2, username);
		prpStSetSession.execute();
	}
	
	@Override
	public void deleteOnlineStatus(String id) throws SQLException{
		prpStDelOnlineStatus = conn.prepareStatement("DELETE FROM Aktive_Session WHERE Session_ID = ?");
		prpStDelOnlineStatus.setString(1, id);
		prpStDelOnlineStatus.execute();
	}
	
	@Override
	public boolean isOnline(String id) throws SQLException{
		ResultSet rs = stmt.executeQuery("SELECT * FROM Aktive_Session WHERE Session_ID = '" + id + "'");
		if(rs.next())
			return true;
		return false;
	}
	
	@Override
	public ResultSet getSession(String id) throws SQLException{
		return stmt.executeQuery("SELECT * FROM Aktive_Session WHERE Session_ID = '" + id + "'");
	}
	
	@Override
	public ResultSet getSessionName(String username) throws SQLException{
		return stmt.executeQuery("SELECT * FROM Aktive_Session WHERE BenutzerName_FK = '" + username + "'");
	}

	@Override
	public ResultSet pullUser(String username) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Benutzer WHERE BenutzerName = '" + username + "'");
		return rs;
	}
	
	@Override
	public String getUserThroughId(String id) throws SQLException{
		ResultSet rs = stmt.executeQuery("SELECT BenutzerName_FK FROM Aktive_Session WHERE Session_ID = '" + id + "'");
		String output = "";
		if(rs.next()){
			output = rs.getString(1);
		}
		return output;
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

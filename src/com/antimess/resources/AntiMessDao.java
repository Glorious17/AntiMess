package com.antimess.resources;

import java.sql.*;
import java.util.ArrayList;

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
	public boolean isIn(String data, String table, int length) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
		while (rs.next()){
			for(int i = 0; i < length; i++){
				if(rs.getString(i+1).equals(data))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean push(String data[], String table) throws SQLException {
		String insertValues = "(";
		for(int i = 0; i < data.length; i++){
			if(isIn(data[i], table, data.length))
				return false;
			if(i != 0)
				insertValues += ", '" + data[i] + "'";
			else
				insertValues += "'" + data[i] + "'";
		}
		insertValues += ", DEFAULT)";
		stmt.executeUpdate("INSERT INTO " + table + " VALUES " + insertValues);
		return true;
	}

	@Override
	public ArrayList pull(String table, int index) throws SQLException {
		ArrayList output = null;
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
		while(rs.next()){
			output.add(rs.getObject(index));
		}
		return output;
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

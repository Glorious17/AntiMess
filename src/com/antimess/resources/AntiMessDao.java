package com.antimess.resources;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;



public class AntiMessDao implements AntiMessDaoInterface {
	
	private static ArrayList <String> Datenbank = new ArrayList<String>();
	private DataSource ds = null;
	private Connection conn;
	
	public AntiMessDao(){
		try {
			Context initCtx = new InitialContext();;
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/AntiMess");
			conn = ds.getConnection();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isIn(String data) {
		for(int i = 0; i<Datenbank.size(); i++){
			if(Datenbank.get(i).equals(data))
				return true;
		}
		return false;
	}

	@Override
	public boolean push(String data) {
		if(isIn(data))
			return false;
		Datenbank.add(data);
		return true;
	}

	@Override
	public String pull(int i) {
		return Datenbank.get(i);
	}
	
	public int length() {
		return Datenbank.size();
	}

}

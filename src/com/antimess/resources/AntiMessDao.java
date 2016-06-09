package com.antimess.resources;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

public class AntiMessDao implements AntiMessDaoInterface {
	
	private static ArrayList <String> Datenbank = new ArrayList<String>();
	private Connection conn;
	
	public AntiMessDao(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/");
		} catch (SQLException e) {
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

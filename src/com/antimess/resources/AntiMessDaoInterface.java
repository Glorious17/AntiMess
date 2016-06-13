package com.antimess.resources;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AntiMessDaoInterface {

	boolean push(String[] data, String table) throws SQLException;
	boolean isIn(String data, String table, int length) throws SQLException;
	ArrayList pull(String table, int index) throws SQLException;
}

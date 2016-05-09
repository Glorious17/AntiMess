package com.antimess.resources;

public interface AntiMessDaoInterface {

	public boolean isIn(String data);
	public boolean push(String data);
	public String pull(int i);
}

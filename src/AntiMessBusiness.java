import java.util.ArrayList;

public class AntiMessBusiness implements AntiMessBusinessInterface{

	private AntiMessDao dao;
	
	public AntiMessBusiness(){
		dao = new AntiMessDao();
	}
	
	public boolean anmelden(String name, String passwort) {
		name.toLowerCase();
		if(dao.isIn(name+";"+passwort))
			return true;
		return false;
	}

	public boolean registrieren(String name, String passwort) {
		name.toLowerCase();
		return dao.push(name+";"+passwort);
	}


}

import java.util.ArrayList;

public class AntiMessDao implements AntiMessDaoInterface{

	private static ArrayList <String> anmeldeDaten = new ArrayList<String>();
	
	public boolean anmelden(String name, String passwort) {
		for(int i = 0; i<anmeldeDaten.size(); i+=2){
			if(anmeldeDaten.get(i).equalsIgnoreCase(name))
				if(anmeldeDaten.get(i+1).equals(passwort))
					return true;
		}
		return false;
	}

	public boolean registrieren(String name, String passwort) {
		for(int i = 0; i<anmeldeDaten.size(); i+=2){
			if(anmeldeDaten.get(i).equalsIgnoreCase(name))
				return false;
		}
		anmeldeDaten.add(name);
		anmeldeDaten.add(passwort);
		return true;
	}


}

import java.util.ArrayList;

public class AntiMessDao{

	private static ArrayList <String> anmeldeDaten = new ArrayList<String>();
	
	public AntiMessDao(){
		anmeldeDaten.add("Student@hotmail.de");
		anmeldeDaten.add("freutSich");
	}
	
	public static boolean anmelden(String name, String passwort) {
		System.out.println("name: " + name + " passwort: " + passwort);
		for(int i = 0; i<anmeldeDaten.size(); i+=2){
			if(anmeldeDaten.get(i).equalsIgnoreCase(name))
				if(anmeldeDaten.get(i+1).equals(passwort))
					return true;
		}
		return false;
	}

	public static boolean registrieren(String name, String passwort) {
		System.out.println("name: " + name + " passwort: " + passwort);
		for(int i = 0; i<anmeldeDaten.size(); i+=2){
			if(anmeldeDaten.get(i).equalsIgnoreCase(name))
				return false;
		}
		anmeldeDaten.add(name);
		anmeldeDaten.add(passwort);
		return true;
	}


}

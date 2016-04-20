import java.util.ArrayList;

public class AntiMessDao implements AntiMessDaoInterface {

	private ArrayList <String> anmeldeDaten = new ArrayList<String>();
	
	public AntiMessDao(){
		anmeldeDaten.add("DeineMutter@hotmail.de");
		anmeldeDaten.add("freutSich");
	}
	

	@Override
	public boolean anmelden(String name, String passwort) {
		for(int i = 0; i<anmeldeDaten.size(); i+=2){
			if(anmeldeDaten.get(i).equalsIgnoreCase(name))
				if(anmeldeDaten.get(i+1).equals(passwort))
					return true;
		}
		return false;
	}

	@Override
	public void registrieren(String name, String passwort) {
		anmeldeDaten.add(name);
		anmeldeDaten.add(passwort);
	}


}

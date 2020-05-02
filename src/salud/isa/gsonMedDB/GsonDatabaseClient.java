package salud.isa.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try {
			Medicines med = new Medicines(null);
			ActiveIngredients ai = new ActiveIngredients(med);
			Physiotherapies pt = new Physiotherapies(ai);
			Inhalers ih = new Inhalers(pt);
			Posologies ps = new Posologies(ih);
			MedicinePresentations mp = new MedicinePresentations(ps);
			RescueMedicinePresentations rmp = new RescueMedicinePresentations(mp);
			UserManualPhysioSteps umps = new UserManualPhysioSteps(rmp);
			UserManualSteps ums = new UserManualSteps(umps);
			DatabaseJSonReader dbjp = new DatabaseJSonReader(ums);
			try {
				System.out.println(dbjp.parse("/Users/jaherreraconde/Desktop/datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ActiveIngredients extends elementoCadena {
	private static final String ACTINGREDIENTS_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";

	public ActiveIngredients(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(ACTINGREDIENTS_TAGNAME)) {
			return super.mismo(reader, nombre);
		}

		else {
			if (suces != null) {
				return super.readCategory(reader, nombre);
			} else {
				reader.skipValue();
				System.err.println("Categoría " + nombre + " no compilada.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) throws IOException {
		String ingName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				ingName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return ingName;
	}
}

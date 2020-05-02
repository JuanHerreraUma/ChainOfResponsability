package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Medicines extends elementoCadena {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public Medicines(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(MEDICINES_TAGNAME)) {
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
		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return medName;
	}
}

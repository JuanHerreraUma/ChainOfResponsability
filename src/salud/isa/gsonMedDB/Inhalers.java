package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Inhalers extends elementoCadena {
	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = ";";

	public Inhalers(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(INHALERS_TAGNAME)) {
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
		String inhName = null;
		String inhImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				inhName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				inhImage = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return inhName + FIELD_SEPARATOR + inhImage;
	}
}

package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends elementoCadena {
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = ";";

	public Physiotherapies(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(PHYSIOTHERAPIES_TAGNAME)) {
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
		String phyName = null;
		String phyImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				phyName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				phyImage = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return phyName + FIELD_SEPARATOR + phyImage;
	}
}

package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Posologies extends elementoCadena {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public Posologies(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(POSOLOGIES_TAGNAME)) {
			return super.mismo(reader, nombre);
		} else {
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
		String posName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
				posName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return posName;
	}
}

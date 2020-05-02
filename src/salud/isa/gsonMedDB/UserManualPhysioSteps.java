package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends elementoCadena {
	private static final String USERMANUALPHY_TAGNAME = "userManualPhysioSteps";
	private static final String STITLE_FIELD_TAGNAME = "stepTitle";
	private static final String SIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYREF_FIELD_TAGNAME = "physioRef";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualPhysioSteps(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(USERMANUALPHY_TAGNAME)) {
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
		String stepTitle = null;
		String stepImage = null;
		String stepText = null;
		String physioRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STITLE_FIELD_TAGNAME)) {
				stepTitle = reader.nextString();
			} else if (name.equals(SIMAGE_FIELD_TAGNAME)) {
				stepImage = reader.nextString();
			} else if (name.equals(STEXT_FIELD_TAGNAME)) {
				stepText = reader.nextString();
			} else if (name.equals(PHYREF_FIELD_TAGNAME)) {
				physioRef = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR + stepText + FIELD_SEPARATOR + physioRef;
	}
}

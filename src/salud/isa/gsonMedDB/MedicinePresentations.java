package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MedicinePresentations extends elementoCadena {
	private static final String MEDPRESENTATION_TAGNAME = "medicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";
	private static final String FIELD_SEPARATOR = ";";

	public MedicinePresentations(elementoCadena suces) {
		super(suces);
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(MEDPRESENTATION_TAGNAME)) {
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
		String medRef = null;
		String aiRef = null;
		String inhRef = "";
		String dose = "";
		String posRef = "";
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					inhRef = reader.nextString();
				} else if (isArray(reader)) {
					reader.beginArray();
					while (reader.hasNext()) {
						inhRef = inhRef + reader.nextString() + ", ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final
					inhRef = inhRef.substring(0, inhRef.length() - 2);
				} else {
					inhRef = "ERROR";
				}
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					dose = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						dose = dose + "(" + reader.nextString() + "), ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final.
					dose = dose.substring(0, dose.length() - 2);
				}
			} else if (name.equals(POSREF_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					posRef = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						posRef = posRef + reader.nextString() + ", ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final
					posRef = posRef.substring(0, posRef.length() - 2);
				}
			} else {
				reader.skipValue();
			}
		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose + FIELD_SEPARATOR
				+ posRef;
	}

	private boolean isArray(JsonReader reader) throws IOException {
		Boolean array = false;
		if (reader.peek() == JsonToken.BEGIN_ARRAY) {
			array = true;
		}
		return array;
	}
}

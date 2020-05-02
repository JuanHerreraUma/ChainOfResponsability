package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class elementoCadena {
	public elementoCadena suces;

	public elementoCadena(elementoCadena next) {
		this.suces = next;
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		return suces.readCategory(reader, nombre);

	}


	public StringBuffer mismo(JsonReader reader, String nombre) throws IOException {
		StringBuffer data = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			data.append(readEntry(reader)).append("\n");
			reader.endObject();
		}
		data.append("\n");
		reader.endArray();
		return data;
	}

	public abstract String readEntry(JsonReader reader) throws IOException;

}
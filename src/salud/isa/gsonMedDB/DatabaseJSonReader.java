package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class DatabaseJSonReader {
	elementoCadena ecadena;

	public DatabaseJSonReader(elementoCadena ec) {
		ecadena = ec;
	}

	public StringBuffer readCategory(JsonReader reader, String nombre) throws IOException {
		return ecadena.readCategory(reader, nombre);
	}

	public String parse(String jsonFileName) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		
		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(name.toUpperCase()).append("\n").append(readCategory(reader, name)).append("\n");
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}
}

package com.gla.util;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class MyTypeAdapter<T> extends TypeAdapter<T> {
	public T read(JsonReader reader) throws IOException {
		return null;
	}

	public void write(JsonWriter writer, T obj) throws IOException {
		if (obj == null) {
			writer.nullValue();
			return;
		}
		writer.value(obj.toString());
	}
}
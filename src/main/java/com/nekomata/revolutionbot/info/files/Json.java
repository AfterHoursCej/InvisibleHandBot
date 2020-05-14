package com.nekomata.revolutionbot.info.files;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json<T> {
	
	private final Class<T> type;
	
	private String path;
	private ObjectMapper objectMapper;
	
	public Json(Class<T> type, String folder, Long id) {

        this.type = type;
		
		this.path = "./assets/data/" + folder + "/" + id + ".json";
		objectMapper = new ObjectMapper();
	}
	
	public Json(Class<T> type, String filename) {
		this.type = type;
		this.path = "./assets/data/" + filename + ".json";
		objectMapper = new ObjectMapper();
	}
	
	public Json(Class<T> type, File file) {
		this.type = type;
		this.path = file.getPath();
		objectMapper = new ObjectMapper();
	}
	
	public void save(T instance) {
		try {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(path), instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public T load() {
        try {
			return objectMapper.readValue(new File(path), this.type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

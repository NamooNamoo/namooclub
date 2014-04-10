package com.namoo.ns1.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.namoo.ns1.common.Identifiable;
import com.namoo.ns1.common.StoredObject;

public class EntityManager {
	//
	private Map<String,StoredObject> objectMap;
	private static EntityManager repository = new EntityManager();

	private String NAMOO_CLUB_DAT;

	public static EntityManager getInstance() {
		//
		return repository;
	}

	//--------------------------------------------------------------------------
	private EntityManager() {
		//
		// load properties file
		Properties prop = new Properties();

		try {
			prop.load(this.getClass().getResourceAsStream("config.properties"));
			NAMOO_CLUB_DAT = (String) prop.get("save.file");

		} catch (IOException e) {
			e.printStackTrace();
		}

		objectMap = load();
		if (objectMap == null) {
			objectMap = new LinkedHashMap<String, StoredObject>();
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, StoredObject> load() {
		//
		File file = new File(NAMOO_CLUB_DAT);
		if (file.exists()) {
			//
			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);

				return (Map<String, StoredObject>) ois.readObject();

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ois != null) ois.close();
					if (fis != null) fis.close();
				} catch (IOException e) { }
			}
		}
		return null;
	}

	public void store(Identifiable entity) {
		//
		StoredObject object = new StoredObject(entity);
		objectMap.put(object.getId(), object);

		persist();
	}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> entityClass, String primaryKey) {
		//
		String key = StoredObject.createKey(entityClass.getName(), primaryKey);
		StoredObject storedObject = objectMap.get(key);
		if (storedObject == null) {
			return null;
		}

		return (T)storedObject.getObject();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> entityClass) {
		//
		List<T> results = new ArrayList<T>();
		for (StoredObject obj : objectMap.values()) {
			if(obj.getObject().getClass() == entityClass){
				results.add((T) obj.getObject());
			}
		}
		return results;
	}

	public void remove(Identifiable entity) {
		//
		String key = StoredObject.createKey(entity);
		if (objectMap.containsKey(key)) {
			objectMap.remove(key);
			persist();
		} else {
			throw new RuntimeException("Invalid key --> " + key);
		}
	}

	private void persist() {
		//
		File file = new File(NAMOO_CLUB_DAT);
		File dataFolder = file.getParentFile();
		if (dataFolder.exists() == false) {
			dataFolder.mkdirs();
		}

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(objectMap);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) oos.close();
				if (fos != null) fos.close();
			} catch (Exception e){}
		}

	}

	public <T> void remove(Class<T> entityClass, String primaryKey) {
		//
		String key = StoredObject.createKey(entityClass.getName(), primaryKey);
		objectMap.remove(key);
		persist();
	}
}

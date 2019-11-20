package com.parser;

import java.util.ArrayList;
import java.util.List;

public class Bean {
	private String id;
	private String clazz;
	private List<Property> properties = new ArrayList<>();

	public Bean() {
		super();
	}

	public Bean(String id, String clazz) {
		super();
		this.id = id;
		this.clazz = clazz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Bean [id=" + id + ", clazz=" + clazz + ", properties=" + properties + "]";
	}

}

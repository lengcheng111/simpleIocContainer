package com.parser;

public class Property {
	private String name;
	private String ref;
	private String value;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(String name, String ref, String value) {
		super();
		this.name = name;
		this.ref = ref;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", ref=" + ref + ", value=" + value + "]";
	}

}

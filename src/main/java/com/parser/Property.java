package com.parser;

public class Property {
	private String ref;
	private String value;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(String ref, String value) {
		super();
		this.ref = ref;
		this.value = value;
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
		return "Property [ref=" + ref + ", value=" + value + "]";
	}

}

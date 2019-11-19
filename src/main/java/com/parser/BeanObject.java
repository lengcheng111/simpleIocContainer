package com.parser;

import java.util.ArrayList;
import java.util.List;

public class BeanObject {
	List<Bean> beans = new ArrayList<Bean>();

	public List<Bean> getBeans() {
		return beans;
	}

	public void setBeans(List<Bean> beans) {
		this.beans = beans;
	}
}

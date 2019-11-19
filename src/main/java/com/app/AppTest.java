package com.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppTest {
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = SampleClass.class.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
		SampleClass sampleObject = new SampleClass();
		methods[1].invoke(sampleObject, "data");
		Object invoke = methods[0].invoke(sampleObject);
		System.out.println(invoke);
	}
}

class SampleClass {
	private String sampleField;

	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}
}

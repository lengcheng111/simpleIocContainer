package com.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private List<Bean> beans = new ArrayList<>();
	private Bean bean;
	private Property property;
	private List<Property> properties = new ArrayList<>();
	private String content = null;

	public BeanObject getBeanObject() {
		final BeanObject beanObj = new BeanObject();
		beanObj.setBeans(beans);
		return beanObj;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if ("bean".equals(qName)) {
			final String id = attributes.getValue("id");
			final String clazz = attributes.getValue("class");
			bean = new Bean(id, clazz);
		} else if ("property".equals(qName)) {
			final String ref = attributes.getValue("ref");
			final String value = attributes.getValue("value");
			property = new Property(ref, value);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("bean".equals(qName)) {
			beans.add(bean);
		} else if ("property".equals(qName)) {
			properties.add(property);
			bean.getProperties().add(property);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

	@Override
	public String toString() {
		return "SAXHandler [beans=" + beans + ", bean=" + bean + ", property=" + property + ", properties=" + properties
				+ ", content=" + content + "]";
	}

}

package com.parser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private List<Bean> beans = new ArrayList<Bean>();
	private Bean bean;
	private Property property;
	List<Property> properties = new ArrayList<Property>();
	String content = null;

	public BeanObject getBeanObject() {
		BeanObject beanObj = new BeanObject();
		beanObj.setBeans(beans);
		return beanObj;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if ("bean".equals(qName)) {
			String id = attributes.getValue("id");
			String clazz = attributes.getValue("class");
			bean = new Bean(id, clazz);
		} else if ("property".equals(qName)) {
			String name = attributes.getValue("name");
			String ref = attributes.getValue("ref");
			String value = attributes.getValue("value");
			property = new Property(name, ref, value);
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
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		content = String.copyValueOf(ch, start, length).trim();
	}

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			saxParser.parse(ClassLoader.getSystemResourceAsStream("config.xml"), handler);
			System.out.println(handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "SAXHandler [beans=" + beans + ", bean=" + bean + ", property=" + property + ", properties=" + properties
				+ ", content=" + content + "]";
	}

}

package com.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.service.ProductService;

public class BeanFactory {
	private String configFile;
	private Map<String, Object> map = new HashMap<>();

	public BeanFactory(String configFile) {
		this.configFile = configFile;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		final BeanFactory beanFactory = new BeanFactory("config.xml");
		final String serviceName = "storeService";
		final ProductService parse = (ProductService) beanFactory.createBean(serviceName);
		System.out.println(parse.getProducts());
	}

	public Object getBean(String name) {
		Object result = null;
		if (map.containsKey(name)) {
			result = map.get(name);
		} else {
			result = this.createBean(name);
			map.put(name, result);
		}
		return result;
	}

	public Object createBean(String name) {
		try {
			final SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			final SAXParser parser = parserFactor.newSAXParser();
			final SAXHandler handler = new SAXHandler();
			parser.parse(ClassLoader.getSystemResourceAsStream(configFile), handler);
			final BeanObject beanObject = handler.getBeanObject();
			final List<Bean> beans = beanObject.getBeans();
			Object instanceClazz = null;
			for (final Bean bean : beans) {
				if (bean.getProperties().isEmpty()) {
					continue;
				}
				final Stream<Bean> remainingBeans = beans.stream().filter(b -> !b.getId().equals(bean.getId()));
				final Optional<Bean> beanRef = remainingBeans
						.filter(b -> bean.getProperties().stream().anyMatch(p -> p.getRef().equals(b.getId())))
						.findAny();
				if (beanRef.isPresent()) {
					instanceClazz = injectRefBeanToCurrentInstance(bean, beanRef.get());
					map.put(bean.getId(), instanceClazz);
				} else {
					return getBean(name);
				}
			}
			return instanceClazz;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Object injectRefBeanToCurrentInstance(Bean bean, Bean beanRef)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		Object instanceClazz;
		instanceClazz = Class.forName(bean.getClazz()).newInstance();
		final Object beanRefClazz = Class.forName(beanRef.getClazz()).newInstance();
		final Method[] methods = instanceClazz.getClass().getMethods();
		final String setterMethod = "set" + beanRef.getId().substring(0, 1).toUpperCase()
				+ beanRef.getId().substring(1);
		boolean foundSetterMethodDefined = false;
		for (final Method method : methods) {
			if (method.getName().equals(setterMethod)) {
				method.invoke(instanceClazz, beanRefClazz);
				foundSetterMethodDefined = true;
			}
		}
		if (!foundSetterMethodDefined) {
			throw new RuntimeException("no setter defined!");
		}
		return instanceClazz;
	}
}

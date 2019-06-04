package com.gym.wechat.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml转为java类
 * 
 * @author janehuang
 * @version 1.0
 * @创建时间：2015年8月24日 下午8:36:24
 */
public class JAXBUtil {

	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, Class<T> clazz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		T t = (T) unmarshaller.unmarshal(new StringReader(xml));
		return t;
	}

	/**
	 * java对象转换为xml文件
	 * 
	 * @param xmlPath
	 *            xml文件路径
	 * @param load
	 *            java对象.Class
	 * @return xml文件的String
	 * @throws JAXBException
	 */
	public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(load);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

}

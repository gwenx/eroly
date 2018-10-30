package com.eroly.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializableUtil {
	private static Logger logger = LoggerFactory.getLogger(SerializableUtil.class);
	/**
	 * 将二进制数组反序列化为Object对象
	 * 
	 * @param objByte
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object unSerializable(byte[] objByte){
		if (objByte == null || objByte.length == 0) {
			logger.info("objByte is null or length is 0");
			return null;
		}
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(objByte);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (Exception e) {
			logger.error("unSerializable error",e);
		}
		return obj;
	}
	/**
	 * 将对象序列化为二进制数组
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] serializable(Object obj){
		if (obj == null) {
			return null;
		}
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (Exception e) {
			logger.error("serializable error",e);
		}
		return bytes;
	}
}

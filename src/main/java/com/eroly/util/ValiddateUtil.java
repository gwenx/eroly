package com.eroly.util;

import com.eroly.exception.ErolyException;

public class ValiddateUtil {
	/**
	 * 验证参数是否为空
	 * @param param
	 * @throws ErolyException
	 */
	public static void validParam(String param) throws ErolyException {
		if(StringUtil.isEmpty(param)) {
			throw new ErolyException("请求参数"+param+"不能为空");
		}
	}
}

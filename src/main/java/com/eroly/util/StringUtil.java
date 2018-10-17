package com.eroly.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 *
 */
public class StringUtil { 
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	private final static char hex_asc_tab[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	/*序号	货币代码	货币描述	货币符号	简化标识
		1、    	36	澳大利亚元	AUD	U
		2、   	124	加拿大元		CAD	A
		3、	    156	人民币		CNY	C
		4、	    344	港元			HKD	K
		5、	    392	日元			JPY	P
		6、	    826	英镑			GBP	B
		7、	    840	美元			USD	S
		8、	    978	欧元			EUR	E

		默认没有传的时候是人民币156

	 *  字母型币种转数字
	 */
	public static String strGetNumCoinType(String strCoinType) {
		String coinType = "";
		if("AUD".equals(strCoinType)){
			coinType = "36";
		}else if("AUD".equals(strCoinType)){
			coinType = "124";
		}else if("HKD".equals(strCoinType)){
			coinType = "344";
		}else if("JPY".equals(strCoinType)){
			coinType = "392";
		}else if("GBP".equals(strCoinType)){
			coinType = "826";
		}else if("USD".equals(strCoinType)){
			coinType = "840";
		}else if("EUR".equals(strCoinType)){
			coinType = "978";
		}else{
			coinType = "156";
		}
		return coinType;
	}
	//三位币种转两位币种
	public static String coinType3to2(String coinType3) {
		String coinType2 = "01";
		if("156".equals(coinType3)){//人民币
			coinType2 = "01";
		}else if("826".equals(coinType3)){//英镑
			coinType2 = "12";
		}else if("344".equals(coinType3)){//港币
			coinType2 = "13";
		}else if("840".equals(coinType3)){//美元
			coinType2 = "14";
		}else if("036".equals(coinType3)){//澳元
			coinType2 = "29";
		}else if("124".equals(coinType3)){//加拿大
			coinType2 = "28";
		}else if("392".equals(coinType3)){//日元
			coinType2 = "27";
		}else if("978".equals(coinType3)){//欧元
			coinType2 = "38";
		}else if("756".equals(coinType3)){//瑞士法郎
			coinType2 = "15";
		}else if("702".equals(coinType3)){//新加坡
			coinType2 = "18";
		}else if("752".equals(coinType3)){//瑞典克朗
			coinType2 = "21";
		}else if("578".equals(coinType3)){//挪威克朗
			coinType2 = "23";
		}else if("246".equals(coinType3)){//芬兰马克
			coinType2 = "42";
		} else if("208".equals(coinType3)){//丹麦克朗
			coinType2 = "22";
		}
		return coinType2;
	}
	
	
	//三位币种转两位币种
	public static String coinType2to3(String coinType2) {
		if(StringUtil.isNotEmpty(coinType2) && coinType2.length()==3) {
			return coinType2; 
		}
		String coinType3 = "156";
		if("01".equals(coinType2)){//人民币
			coinType3 = "156";
		}else if("12".equals(coinType2)){//英镑
			coinType3 = "826";
		}else if("13".equals(coinType2)){//港币
			coinType3 = "344";
		}else if("14".equals(coinType2)){//美元
			coinType3 = "840";
		}else if("29".equals(coinType2)){//澳元
			coinType3 = "036";
		}else if("28".equals(coinType2)){//加拿大
			coinType3 = "124";
		}else if("27".equals(coinType2)){//日元
			coinType3 = "392";
		}else if("38".equals(coinType2)){//欧元
			coinType3 = "978";
		}else if("15".equals(coinType2)){//瑞士法郎
			coinType3 = "756";
		}else if("18".equals(coinType2)){//新加坡
			coinType3 = "702";
		}else if("21".equals(coinType2)){//瑞典克朗
			coinType3 = "752";
		}else if("23".equals(coinType2)){//挪威克朗
			coinType3 = "578";
		}else if("42".equals(coinType2)){//芬兰马克
			coinType3 = "246";
		} else if("22".equals(coinType2)){//丹麦克朗
			coinType3 = "208";
		}
		return coinType3;
	}
	
	/*
	 *  字符串类型转换
	 */
	public static String iso2utf8(String src) {
		try {
			if (isEmpty(src))
				return "";
			return new String(src.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return "?";
		}
	}

	public static String iso2gbk(String src) {
		try {
			if (isEmpty(src))
				return "";
			return new String(src.getBytes("iso-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e) {
			return "?";
		}
	}

	public static String utf2gbk(String src) {
		try {
			if (isEmpty(src))
				return "";
			return new String(src.getBytes("utf-8"), "gbk");
		} catch (UnsupportedEncodingException e) {
			return "?";
		}
	}

	public static String gbk2utf(String src) {
		try {
			if (isEmpty(src))
				return "";
			return new String(src.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			return "?";
		}
	}
	
	public static String gbk2utf(byte[] gbk) {
		try {
			return new String(gbk, "gbk");
		} catch (UnsupportedEncodingException e) {
			return "?";
		}
	}
	
	/**
	 * 截取字符串的长度
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getSubStrLen(String str,int len){
		return getSubStrLen(str,0,len);
	}
	
	/**
	 * 截取字符串指定长度
	 * 
	 * @param srcStr
	 * @param beginIndex
	 * @param len
	 * @return
	 */
	public static String getSubStrLen(String srcStr,int beginIndex,int len){
		if(StringUtil.isEmpty(srcStr) || srcStr.trim().length() < beginIndex + len){
			return srcStr;
		}
		return srcStr.substring(beginIndex, beginIndex+len);
	}


	
	/**
	 * 设置MESSAGE中的变量{0}....{N}
	 * 
	 * @param msg
	 * @param vars
	 * @return
	 */
	public static String getMessage(String msg,String[] vars){
		for(int i = 0; i < vars.length; i++){
			msg = msg.replaceAll("\\{" + i + "\\}", vars[i]);
		}
		return msg;
	}

	/**
	 * <li>判断字符串是否为空值</li> <li>NULL、空格均认为空值</li>
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return null == value || 
				"".equals(value.trim()) || 
				"null".equals(value.toLowerCase(Locale.ENGLISH));
	}
	
	/**
	 * 判断是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Object s){
		return s == null || isEmpty(s.toString());
	}

	/**
	 * 判断是否为空白字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	/**
	 * 内容不为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return null != value && !"".equals(value.trim());
	}

	/**
	 * 重复字符串 如 repeatString("a",3) ==> "aaa"
	 * 
	 * @author uke
	 * @param src
	 * @param repeats
	 * @return
	 */
	public static String repeatString(String src, int repeats) {
		if (null == src || repeats <= 0) {
			return src;
		} else {
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < repeats; i++) {
				bf.append(src);
			}
			return bf.toString();
		}
	}

	/**
	 * 左对齐字符串 * lpadString("X",3); ==>" X" *
	 * 
	 * @param src
	 * @param length
	 * @return
	 */
	public static String lpadString(String src, int length) {
		return lpadString(src, length, " ");
	}

	/**
	 * 以指定字符串填补空位，左对齐字符串 * lpadString("X",3,"0"); ==>"00X"
	 * 
	 * @param src
	 * @param byteLength
	 * @param temp
	 * @return
	 */
	public static String lpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return repeatString(single, length - src.getBytes().length) + src;
		}
	}

	/**
	 * 右对齐字符串 * rpadString("9",3)==>"9 "
	 * 
	 * @param src
	 * @param byteLength
	 * @return
	 */
	public static String rpadString(String src, int byteLength) {
		return rpadString(src, byteLength, " ");
	}

	/**
	 * 以指定字符串填补空位，右对齐字符串 rpadString("9",3,"0")==>"900"
	 * 
	 * @param src
	 * @param byteLength
	 * @param single
	 * @return
	 */
	public static String rpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return src + repeatString(single, length - src.getBytes().length);
		}
	}

	/**
	 * 去除,分隔符，用于金额数值去格式化
	 * 
	 * @param value
	 * @return
	 */
	public static String decimal(String value) {
		if (null == value || "".equals(value.trim())) {
			return "0";
		} else {
			return value.replaceAll(",", "");
		}
	}

	/**
	 * 在数组中查找字符串
	 * 
	 * @param params
	 * @param name
	 * @param ignoreCase
	 * @return
	 */
	public static int indexOf(String[] params, String name, boolean ignoreCase) {
		if (params == null)
			return -1;
		for (int i = 0, j = params.length; i < j; i++) {
			if (ignoreCase && params[i].equalsIgnoreCase(name)) {
				return i;
			} else if (params[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将字符转数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] toArr(String str) {
		String inStr = str;
		String a[] = null;
		try {
			if (null != inStr) {
				StringTokenizer st = new StringTokenizer(inStr, ",");
				if (st.countTokens() > 0) {
					a = new String[st.countTokens()];
					int i = 0;
					while (st.hasMoreTokens()) {
						a[i++] = st.nextToken();
					}
				}
			}
		} catch (Exception e) {
			logger.error("e printStack:{}",e);
		}
		return a;
	}

	/**
	 * 字符串数组包装成字符串
	 * 
	 * @param ary
	 * @param s
	 *            包装符号如 ' 或 "
	 * @return
	 */
	public static String toStr(String[] ary, String s) {
		if (ary == null || ary.length < 1)
			return "";
		StringBuffer bf = new StringBuffer();
		bf.append(s);
		bf.append(ary[0]);
		for (int i = 1; i < ary.length; i++) {
			bf.append(s).append(",").append(s);
			bf.append(ary[i]);
		}
		bf.append(s);
		return bf.toString();
	}

	/**
	 * 取整数值
	 * 
	 * @param map
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Map map, String key, int defValue) {
		if (null != map && isNotEmpty(key)) {
			try {
				return Integer.parseInt((String) map.get(key));
			} catch (Exception e) {
			}
		}
		return defValue;
	}

	/**
	 * 取浮点值
	 * 
	 * @param map
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Map map, String key, int defValue) {
		if (null != map && isNotEmpty(key)) {
			try {
				return Float.parseFloat(map.get(key).toString());
			} catch (Exception e) {
			}
		}
		return defValue;
	}

	public static boolean isNumber(String s) {
		if (s == null)
			return false;
		return s.matches("[0-9\\.]+");
	}

	/**
	 * 转整数
	 * 
	 * @param str
	 * @return
	 */
	public static int parseInt(String str) {
		if (!isNumber(str))
			return 0;
		return Integer.parseInt(str);
	}
	/**
	 * 
	 * @param str
	 * @param def
	 * @return
	 */
	public static int parseInt(String str, int def) {
		int rst = parseInt(str);
		if (rst < 0) {
			return def;
		}
		return rst;
	}

	public static void generyXmlEntry(StringBuffer bf, String entry, String value) {
		bf.append(String.format("<%s>%s</%s>", entry, value, entry));
	}

	/**
	 * 从Map中取String类型值
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Object getMapValue(Map map, Object key) {
		if (null == map || null == key)
			return "";

		if ((key instanceof String)) {
			String keystr = (String) key;
			keystr = keystr.toUpperCase();
			key = keystr;
		}
		Object value = map.get(key);
		return null == value ? "" : value;
	}

	public static void generyXmlEntryCData(StringBuffer bf, String entry, Object value) {
		bf.append("<").append(entry).append("><![CDATA[");
		if (null != value)
			bf.append(value);
		bf.append("]]></").append(entry).append(">");
	}

	public static String generyImgUrl(Object rootUrl, Object date, Object imgId, Object imgInfo) {
		StringBuffer bf = new StringBuffer();
		try {
			String ext = StringUtil.getFileExtName((String) imgInfo);
			bf.append(rootUrl).append("/");
			bf.append(date).append("/");
			bf.append(imgId).append(ext);
		} catch (Exception e) {
			bf.append("");
		}
		return bf.toString();
	}

	/**
	 * 解析文件的扩展名
	 * 
	 * @param oldName
	 * @return
	 */
	public static String getFileExtName(String oldName) {
		String ext = "";
		int lastIndex = oldName.lastIndexOf(".");
		if (lastIndex > 0) {
			ext = oldName.substring(lastIndex);
		}
		return ext;
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将以splitToken分隔的字符串换为集合
	 * 
	 * @param str
	 * @param splitToken
	 * @return
	 */
	public static List<String> getSplitStrColl(String str,String splitToken){
		if(StringUtil.isEmpty(str)){
			return new ArrayList<String>();
		}
		String[] strArray = str.split(splitToken);
		return Arrays.asList(strArray);
	}
	
   
	public static int string2Int(String str) {
		return parseInt(str);
	}

	/**
	 * 格式化xml数据
	 * 
	 * @param stringWriter
	 * @param doc
	 * @throws IOException
	 */
	public static void formateXMLStr(Writer stringWriter, Document doc) throws IOException {
		XMLWriter xmlWriter = null;
		try {
			OutputFormat of = new OutputFormat();
			of.setIndent(true);
			of.setEncoding("UTF-8");
			of.setNewlines(true);
			xmlWriter = new XMLWriter(stringWriter, of);
			xmlWriter.write(doc);
		} catch (IOException e) {
			throw e;
		} finally {
			if (null != xmlWriter) {
				xmlWriter.close();
			}
		}
	}

	/**
	 * replaceXmlPwd 屏蔽密码输出
	 * 
	 * @param log
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String replaceXmlPwd(String log) {
		String repLog = log;
		try {
			repLog = repLog.replaceAll("<PASSWORD>.*?</PASSWORD>", "<PASSWORD>******</PASSWORD>");
			repLog = repLog.replaceAll("<password>.*?</password>", "<password>******</password>");
			repLog = repLog.replaceAll("<Passwd>.*?</Passwd>", "<Passwd>******</Passwd>");
		} catch (Exception e) {
			logger.error("e printStack:{}",e);
			return log;
		}
		return repLog;
	}

	/**
	 * replaceJsonPwd 屏蔽密码输出
	 * 
	 * @param sendData
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String replaceJsonPwd(String log) {
		String repLog = log;
		try {
			repLog = repLog.replaceAll("\\\"PASSWORD\\\":\\\".*?\\\"", "\"PASSWORD\":\"\"");
			repLog = repLog.replaceAll("\\\"password\\\":\\\".*?\"", "\"password\":\"\"");
			repLog = repLog.replaceAll("\\\"Passwd\":\\\".*?\\\"", "\"Passwd\":\"\"");
		} catch (Exception e) {
			logger.error("e printStack:{}",e);
			return log;
		}
		return repLog;
	}

	/**
	 * 
	 * StringUtil.stripToEmpty(null) = "" StringUtil.stripToEmpty("") = "" StringUtil.stripToEmpty("   ") = ""
	 * StringUtil.stripToEmpty("abc") = "abc" StringUtil.stripToEmpty("  abc") = "abc" StringUtil.stripToEmpty("abc  ")
	 * = "abc" StringUtil.stripToEmpty(" abc ") = "abc" StringUtil.stripToEmpty(" ab c ") = "ab c"
	 * 
	 * @param value
	 * @return
	 */
	public static String stripToEmpty(Object value) {

		if (null == value) {
			return "";
		}
		String str = String.valueOf(value);
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return StringUtils.stripToEmpty(str);
	}
	
	/**
     * 带参字符串文本
     * 
     * @param temp
     * @param params
     * @return
     */
	public static String message(String temp, Object... params) {
		for (int i = 0; i < params.length; i++) {
			temp = temp.replaceAll("\\{" + i + "\\}", params[i].toString());
		}
		return temp;
	}

	public static String getPrefixStr(String str, char ch, int nWantLength) {

		if (str == null)
			str = ch + "";

		while (str.length() < nWantLength)
			str = ch + str;

		return str;
	}
	
	public static int bytesToInt(byte[] intByte) {
		int fromByte = 0;
		for (int i = 0; i < 2; i++) {
			int n = (intByte[i] < 0 ? (int) intByte[i] + 256 : (int) intByte[i]) << (8 * i);
			// System.out.println(n);
			fromByte += n;
		}
		return fromByte;
	}
	

	public static String bytes2Hex(byte[] intByte) {
		
		return StringUtil.toStringBlock(intByte);
	}
	
	public static String toStringBlock(byte[] intByte) {
		if(null==intByte)
			return "";
		return StringUtil.toStringBlock(intByte,0,intByte.length);
	}
	
	public static String toStringBlock(byte[] intByte,int off,int len) {
		StringBuilder sb = new StringBuilder();
		for(int i =0 ;i<len;i++){
			sb.append(hex_asc_tab[(intByte[off+i]>>>4)& 0x0f]);
			sb.append(hex_asc_tab[(intByte[off+i])& 0x0f]);
		}
		
		return sb.toString();
	}

	/**
	 * change unsigned byte to int
	 * 
	 * @param b
	 *            unsigend byte
	 * @return int value
	 */
	public static int unsignedByteToInt(byte b) {
		int i = b & 0xff;
		return i;
	}
	
	public static int unsignedByteToInt(byte[] b) {
		int len = b.length;
		int temp = 0;
		int b_temp;
		for (int i = 0; i < len; i++) {
			if (b[i] < 0) {
				b_temp = b[i] + 256;
			} else {
				b_temp = b[i];
			}
			temp += b_temp * Math.pow(256, len - i - 1);
		}
		return temp;
	}
	private static byte uniteBytes(String src0, String src1) {

		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	public static byte[] hexStr2Bytes(String src) {

		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}
	public static String byte2Hex(byte[] byteArr) {

		return toStringBlock(byteArr);
	}
	
	/**
	 * 过滤掉url中的/page/ 和  xx.html
	 * 
	 * @param url
	 * @return
	 */
	public static String replaceBothSlant(String url){
		if(StringUtil.isEmpty(url)){
			return "";
		}
		
		//去掉 /page/
		int pageIndex = (url.indexOf("page") == -1 ? 0 : url.indexOf("page") + 4);
		String replaceUrl = url.substring(pageIndex);
		if(replaceUrl.startsWith("/")){
			replaceUrl = replaceUrl.substring(1);
		}
		
		// 去掉 .html
		if(StringUtil.isEmpty(replaceUrl)){
			return "";
		}
		int htmIndex = 0;
		if(replaceUrl.lastIndexOf(".") == -1){
			htmIndex = replaceUrl.length();
		}else{
			htmIndex = replaceUrl.lastIndexOf("/");
		}
		replaceUrl = replaceUrl.substring(0, htmIndex);
		if(replaceUrl.endsWith("/")){
			replaceUrl = replaceUrl.substring(0,replaceUrl.length() - 1);
		}
		return replaceUrl;
	}	

	/*public static String toStringBlock(byte[] block, int off, int len) {

		
		 * StringBuffer buf = new StringBuffer(); for( int i=0; i < len; i++ ) {
		 * buf.append( hex_asc_tab[ ( block[off+i] >>> 4) & 0x0f ] );
		 * buf.append( hex_asc_tab[ ( block[off+i] ) & 0x0f ] ); } return
		 * buf.toString();
		 
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(hex_asc_tab[(block[off + i] >>> 4) & 0x0f]);
			sb.append(hex_asc_tab[(block[off + i]) & 0x0f]);
		}
		return sb.toString();
	}*/
	/**
	 * <p>
	 * convert a block into string.
	 * </p>
	 * 
	 * @param block
	 *            the input byte array
	 * @return the hex string
	 */
/*	public static String toStringBlock(byte[] block) {
		if (null == block)
			return "";
		return toStringBlock(block, 0, block.length);
	}*/
	/**
	 * 比较二个版本的大小,上送版本大于等于配置版本返回true
	 * 
	 * @param sver:上传版本
	 * @param pver:配置版本
	 * @return
	 */
	public static boolean compareVer(String sver,String pver){
		String[] svers = sver.split("\\.");
		String[] pvers = pver.split("\\.");
		int size = Math.min(svers.length, pvers.length);
		for(int i=0;i<size;i++) {
			int s = Integer.parseInt(svers[i]);//上传版本
			int p = Integer.parseInt(pvers[i]);//配置版本
			if(s > p) { //大于配置版本
				return true;
			} else if(s < p) { //小于配置版本
				return false;
			} 			
		}
		//前面的版本都相同如：4.1.3和4.1;默认为两个版本相同
		return true;		
	}
	
	//去零操作
	public static String clearZero(String val) {
		if(StringUtil.isEmpty(val)) {
			return "0";
		}
		boolean isfushu = false;
		if(val.contains("-")){//存在-
			val = val.replaceAll("^-0*", "");
			isfushu = true;
		} else {//正数
			val = val.replaceAll("^0*", "");
		}		
		if(StringUtil.isBlank(val) || StringUtil.isEmpty(val)) {
			val = "0";
		} else if(val.indexOf(".")==0) {
			val = "0"+val;
		} 
		if(isfushu) {
			return "-"+val;
		} else {
			return val;
		}
	}
	
}

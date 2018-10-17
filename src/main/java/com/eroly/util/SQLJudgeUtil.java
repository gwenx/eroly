package com.eroly.util;
/**
 * sql放输入工具类
 * @author yapeng
 * @version 2018-02-09
 */
public class SQLJudgeUtil {
	/**
	 * 检测输入的字串是否有SQL关键字
	 * 防止SQL从URL注入
	 * @param str
	 * @return true包含，false不包含
	 */
	public static boolean sql_inj(String str) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}
	public static boolean matchePattern(String str) {
		String CHECKSQL1 = "^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
		String CHECKSQL2 = "^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
		String CHECKSQL3 = "^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
		String CHECKSQL4 = "^(.+)\\sand\\s(.+)|(.+)\\sor(.+)\\s$";
		if(str.matches(CHECKSQL1)&&str.matches(CHECKSQL2)&&str.matches(CHECKSQL3)&&str.matches(CHECKSQL4)) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(SQLJudgeUtil.matchePattern("''"));
	}
}

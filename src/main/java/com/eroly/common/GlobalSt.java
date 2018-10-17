package com.eroly.common;

public class GlobalSt {
	//redisTemplate的名字
	public final static String REDIS_TEMPLATE = "common"; //
	//所有课程信息放在redis的key
	public final static String ALL_COURSE_INFO = "ALL_COURSE_INFO";//所有课程信息
	public final static String TOP_SIX_GRA = "TOP_SIX_GRA";//前六个
	//返回信息
	public final static String SYSTEM_STATUS = "STATUS"; //返回状态码
	public final static String SYSTEM_MSG = "MSG"; //返回信息
	public final static String SYSTEM_STATU_ZERO = "0"; //返回状态 0失败
	public final static String SYSTEM_STATU_ONE = "1"; //成功
	public final static String SYSTEM_STATU_TWO = "2"; //返回，2特殊失败状态
	//公共信息
	public final static String PUBLIC_INFO_ALL = "PUBLIC_INFO_ALL";
	public final static String PUBLIC_INFO = "PUBLIC_INFO_";
	public final static String PUBLIC_INFO_ABOUT_US= "1";//关于我们infoType为1
	public final static String ONE= "1";//关于我们infoType为1
	//教师信息
	public final static String ALL_TEACHER_INFO= "ALL_TEACHER_INFO";//所有教师
	//课程
	public final static String COURSE_INFO= "COURSE_INFO_";//某课程的信息
	public final static String CHAPTER_LIST= "CHAPTER_LIST_";//某课程对应的章节信息
	//用户信息
	public final static String USER_INFO= "USER_INFO";//保存在session的用户信息
	public final static String LOGIN_FLAG= "LOGIN_FLAG";//是否登录标志
	public final static String LOGIN_YES= "1";//是否登录标志
	public final static String LOGIN_NOT= "0";//是否登录标志
	
	public final static String MESSAGE_SESSION= "MESSAGE_SESSION";//短信验证码放会话里
	public final static String MESSAGE_TIME= "MESSAGE_SESSION";//放入的时间
}

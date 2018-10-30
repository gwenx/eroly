package com.eroly.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.StudentServise;
import com.eroly.util.CourseUtil;
import com.eroly.util.DateUtil;
import com.eroly.util.OperaServletUtil;
import com.eroly.util.ValiddateUtil;

@Controller("SignUpController")
@RequestMapping("/signUp")
public class SignUpController {
	private static Logger logger = LoggerFactory.getLogger(SignUpController.class);
	@Autowired
	private CourseUtil courseUtil;
	@Autowired
	OperaServletUtil OperaServlet;
	@Autowired
	@Qualifier("StudentServise")
	private StudentServise studentServise;
	/**
	 * 在课程详情页点击报名-查询课程基本信息
	 * @param model
	 * @param courseId
	 * @return
	 */
	@RequestMapping("toSignUp")
	public String toSignUp(Model model,String courseId) {
		try {
			logger.error("------课程信息Id----"+courseId);
			ValiddateUtil.validParam(courseId);
			Map<String, Object> courseMap = new HashMap<String, Object>();
			courseMap = courseUtil.getCourseInfoById(courseId);
			if(courseMap != null && courseMap.size()>0) {//查到了课程信息
				model.addAttribute("courseMap",courseMap);//课程信息
				model.addAttribute("courseId",courseId);
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "成功");
			}else {//没查到
				logger.error("------未查询到课程信息----");
				throw new ErolyException("系统异常，请稍后再试");
			}
		}catch(ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}
		return "signup/signup";
	}
	/**
	 * 确认报名时调用
	 * @param courseId
	 * @return
	 */
	@RequestMapping("signUp")
	@ResponseBody
	public Map<String, Object> signUp(String courseId,String studentRealName,String studentAddr) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			logger.error("------课程信息Id----"+courseId);
			logger.error("------课程信息Id----"+studentRealName);
			logger.error("------课程信息Id----"+studentAddr);
			ValiddateUtil.validParam(courseId);
			ValiddateUtil.validParam(studentRealName);
			ValiddateUtil.validParam(studentAddr);
			Map<String, Object> userInfoMap = (Map<String, Object>) OperaServlet.getSessValue(GlobalSt.USER_INFO);
			if(userInfoMap==null) {
				logger.error("------未登录---");
				throw new ErolyException("您尚未登录，请登录后报名。");
			}
			//用户ID
			String userId=""+userInfoMap.get("userId");
			//根据用户ID和课程ID，查询用户是否报过名过该课程
			Map<String, Object> findSignUp = studentServise.findSignUp(userId, courseId);
			if(findSignUp!=null) {
				logger.error("------已报名--");
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				result.put(GlobalSt.SYSTEM_MSG, "您已报名该课程，请勿重复报名。");
				return result;
			}
			//用户报过的所有课程
			List<Map<String, Object>> findSignByUser = studentServise.findSignByUser(userId);
			Map<String, Object> courseMap =null;
			if(findSignByUser!=null && findSignByUser.size()>0) {
				courseMap = courseUtil.getCourseInfoById(courseId);
				if(courseMap!=null){
					String start=(String) courseMap.get("teachingStart");
					Date parseDate = DateUtil.parseDate(start);
					long mowTime = parseDate.getTime();
					for (Map<String, Object> map : findSignByUser) {
						Timestamp startTimestamp=(Timestamp) map.get("startTime");
						long startTime = startTimestamp.getTime();
						Timestamp endTimestamp=(Timestamp) map.get("endTime");
						long endTime = endTimestamp.getTime();
						if(mowTime>=startTime && mowTime<=endTime) {
							logger.error("------报名时间冲突--");
							result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_TWO);
							String courseName = (String) map.get("courseName");
							result.put(GlobalSt.SYSTEM_MSG, "当前课程与课程《"+courseName+"》冲突，请选择其他课程");
							return result;
						}
					}
				}else{
					logger.error("------未查询到选择的课程信息--");
					result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_TWO);
					result.put(GlobalSt.SYSTEM_MSG, "未查询到选择的课程信息");
					return result;
				}
			}
			//校验完毕，去报名。插入报名信息
			Map<String,String> param = new HashMap<String, String>();
			param.put("userId", userId);
			param.put("courseId", courseId);
			param.put("hasPay", userId);//是否缴费（0未交费，1已交全款，2已交押金，4已交定金尾款）
			param.put("payMoney", "0");//缴费金额
			param.put("studentStatus", "1");//学员状（1在学2毕业）
			studentServise.insertSignInfo(param);
			result.put("currentCourse", courseMap);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			result.put(GlobalSt.SYSTEM_MSG, "交易成功");
		}catch(ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
			return result;
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
 			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
			return result;
		}
		return result;
	}
}

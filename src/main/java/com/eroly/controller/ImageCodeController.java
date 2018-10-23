package com.eroly.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.util.ImageCodeUtil;

@Controller("ImageCodeController")
@RequestMapping("/imageCode")
public class ImageCodeController {
	private static Logger logger = LoggerFactory.getLogger(ImageCodeController.class);
	
	@RequestMapping("create")
	@ResponseBody
	public String imageCode(String courseId,HttpServletResponse hsr) {
		ServletOutputStream sos = null;
		String randomNum = ImageCodeUtil.createRandomCode();
		try {
			BufferedImage buffImg = ImageCodeUtil.imageCode(randomNum);
			hsr.setHeader("Pragma","no-cache");
			hsr.setHeader("Cche-Control","no-cache");
			hsr.setDateHeader("Expires",0);
			hsr.setContentType("iamge/png");
			sos = hsr.getOutputStream();
			ImageIO.write(buffImg, "png", sos);
			sos.flush();
		} catch (IOException e) {
			logger.error("ImageCodeController.imageCode"+e.getMessage(),e);
		}
		return "";
	}
}

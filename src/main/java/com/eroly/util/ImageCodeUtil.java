package com.eroly.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;
import javax.imageio.ImageIO;

public class ImageCodeUtil {

	private static final int width = 60;
	private static final int height = 20;
	
	public static BufferedImage imageCode(String randomNum) {
		// 创建具有可访问图像数据缓冲区的Image
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try{
			Graphics2D g = buffImg.createGraphics();
			// 创建一个随机数生成器
			SecureRandom random = new SecureRandom();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			// 设置字体  可设置几个字体值取随机的
			Font font = new Font("Times New Roman", Font.PLAIN, 19);
			// 画边框
			g.setColor(Color.BLUE);
			g.drawRect(0, 0, width - 1, height - 1);
			int red = 0, green = 0, blue = 0;
			red = random.nextInt(110);
			green = random.nextInt(80);
			blue = random.nextInt(180);
			// 随机产生10条干扰线
			g.setColor(new Color(red, green, blue));
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int x1 = random.nextInt(22);
				int y1 = random.nextInt(22);
				g.drawLine(x, y, x + x1, y + y1);
			}
			System.out.println("验证码："+randomNum);
			// 随机产生4位数字的验证码
			for (int i = 0; i < 4; i++) {
				// 得到随机产生的验证码数字
				String strRand = String.valueOf(randomNum.charAt(i));
				// 产生随机的颜色分量来构造颜色值
				red = random.nextInt(110);
				green = random.nextInt(80);
				blue = random.nextInt(180);
				// 用随机产生的颜色将验证码绘制到图像中
				g.setFont(font);
				g.setColor(new Color(red, green, blue));
				g.drawString(strRand, 13 * i + 6, 16);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffImg;
	}
	public static String createRandomCode(){
		SecureRandom random = new SecureRandom();
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<4;i++){
			buffer.append(random.nextInt(9)+"");
		}
		return buffer.toString();
	}
}

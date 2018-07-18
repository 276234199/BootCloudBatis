package com.casit;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public  class TestWaterPicture {

	// 定义上传的文件夹
	private static final String UPLOAD_PATH = "D:/";
	// 定义水印文字样式
	private static final String MARK_TEXT = "小卖铺的老爷爷";
	private static final String FONT_NAME = "微软雅黑";
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_SIZE = 60;
	private static final Color FONT_COLOR = Color.black;
	private static final float ALPHA = 1F;

	// 添加单图片水印
	public static String imageWaterMark() {
		
		OutputStream os = null;
		int X = 636;
		int Y = 763;

		try {
			Image image = ImageIO.read(new File("D:/123.jpg"));
			// 计算原始图片宽度长度
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			// 创建图片缓存对象
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// 创建java绘图工具对象
			Graphics2D graphics2d = bufferedImage.createGraphics();
			// 参数主要是，原图，坐标，宽高
			graphics2d.drawImage(image, 0, 0, width, height, null);
			graphics2d.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			graphics2d.setColor(FONT_COLOR);

			// 水印图片路径
			String logoPath = "D:/logo.png";
			File logo = new File(logoPath);
			Image imageLogo = ImageIO.read(logo);
			int widthLogo = imageLogo.getWidth(null);
			int heightLogo = imageLogo.getHeight(null);
			int widthDiff = width - widthLogo;
			int heightDiff = height - heightLogo;
			// 水印坐标设置
			if (X > widthDiff) {
				X = widthDiff;
			}
			if (Y > heightDiff) {
				Y = heightDiff;
			}
			// 水印透明设置
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
			graphics2d.drawImage(imageLogo, X, Y, null);

			graphics2d.dispose();
			os = new FileOutputStream("D:/456.jpg");
			// 创建图像编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			// 使用图像编码工具类，输出缓存图像到目标文件
			en.encode(bufferedImage);
			

			
			if (os != null) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public static void main(String[] args) {
        
		System.out.println(imageWaterMark());
    }

}

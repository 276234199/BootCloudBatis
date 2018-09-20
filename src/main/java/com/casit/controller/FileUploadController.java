package com.casit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年9月16日 下午4:18:27
 */
@RestController("file")
public class FileUploadController {

	@PostMapping("/singlefile.do")
	public String singleFile(HttpServletRequest req, @RequestParam("fileName") MultipartFile file) {
		if (file.isEmpty()) {
			return "error";
		}
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		String path = "F:/test";
		File dest = new File(path + "/" + fileName);
		if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // 保存文件
			return "true";
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/manyfile.do")
	//未使用多线程，如果大文件 可以考虑。
	public String manyFile(HttpServletRequest req) {
		if (!(req instanceof MultipartHttpServletRequest)) {
			return "error，仅仅支持multipart";
		}
		List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("fileName");

		if (files.isEmpty()) {
			return "false";
		}

		String path = "F:/test";

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			int size = (int) file.getSize();
			System.out.println(fileName + "-->" + size);

			if (file.isEmpty()) {
				return "false";
			} else {
				File dest = new File(path + "/" + fileName);
				if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
					dest.getParentFile().mkdir();
				}
				try {
					file.transferTo(dest);
				} catch (Exception e) {
					e.printStackTrace();
					return "false";
				}
			}
		}
		return "true";
	}

}

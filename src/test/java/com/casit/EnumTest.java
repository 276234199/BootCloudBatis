package com.casit;

public class EnumTest {
	
	public static void main(String[] args) {
		System.out.println(StringEnum.FAILURE);
		System.out.println(FileEnum.MP4.fileType);
	}
	
	public enum StringEnum{
		SUCCESS,FAILURE,EXCEPTION;
	}
	
	public enum FileEnum{
		
		IMG("img","图片"),
		JPG("jpg","图片"),
		MP4("mp4","视频");
		
		public String extension;
		public String fileType;
		
		private FileEnum(String extension, String fileType) {
			this.extension = extension;
			this.fileType = fileType;
		}
		
			
	}

}

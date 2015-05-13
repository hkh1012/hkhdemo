package hkhdemo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件类的操作
 * 
 */
public class FileDemo {

	public static void main(String[] args) {
		new FileDemo().createFile();
	}

	//创建文件
	public void createFile() {
		// 1、文件的创建与删除
		String path = "D:" + File.separator + "file.txt";
		File file = new File(path);
		try {
			if (file.exists()) {
				// 删除
				file.delete();
			} else {
				// 创建
				file.createNewFile();
				// 文件写入字符流
				FileWriter fopw = new FileWriter(file);
				fopw.write("hello world1.\r\n");
				fopw.write("hello world2.\r\n");
				fopw.write("hello world3.");
				fopw.flush();
				fopw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取文件
	

}

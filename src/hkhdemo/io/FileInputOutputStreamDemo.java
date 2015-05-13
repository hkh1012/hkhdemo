package hkhdemo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * FileInputStream、FileOutputStream 文件字节输入流、文件字节输出流 
 * 分别继承自InputStream、OutputStream,主要用来读写二进制文件，如图片、视频等。
 * 使用文件通道的方式效率比Buffer方式，尤其是拷贝大文件时
 */
public class FileInputOutputStreamDemo {

	//实现文件复制
	public static void main(String[] args) {
		new FileInputOutputStreamDemo().copyFileByChannel();
	}
	
	//BufferInputStream方式拷贝文件
	public void copyFileByBuffer(){
		String srcImgPath = "D:"+File.separator+"eclipse-java-luna-SR2-win32-x86_64.zip";	
		String desImgPath = "E:"+File.separator+"eclipse-java-luna-SR2-win32-x86_64(2).zip";	
		File srcFile = new File(srcImgPath);
		File desFile = new File(desImgPath);
		InputStream fis = null;
		OutputStream fos = null;
		long s = System.currentTimeMillis();
		try {
			fis = new BufferedInputStream(new FileInputStream(srcFile));
			fos = new BufferedOutputStream(new FileOutputStream(desFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[2048];
		int lenth;
		try {
			while((lenth = fis.read(buffer))!=-1){
				fos.write(buffer, 0, lenth);
			}
			long e = System.currentTimeMillis();
			System.out.println("复制文件："+(e-s)+"ms");
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//用文件通道的方式拷贝文件 since 1.7
	public void copyFileByChannel(){
		String srcPath = "D:"+File.separator+"eclipse-java-luna-SR2-win32-x86_64.zip";	
		String desPath = "E:"+File.separator+"eclipse-java-luna-SR2-win32-x86_64(2).zip";	
		File srcFile = new File(srcPath);
		File desFile = new File(desPath);
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in  = null;
		FileChannel out = null;
		long s = System.currentTimeMillis();
		try {
			fi = new FileInputStream(srcFile);
			fo = new FileOutputStream(desFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in = fi.getChannel();
		out = fo.getChannel();
		try {
			in.transferTo(0, in.size(), out);
			long e = System.currentTimeMillis();
			System.out.println("文件复制："+(e-s)+"ms");
			fi.close();
			in.close();
			fo.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

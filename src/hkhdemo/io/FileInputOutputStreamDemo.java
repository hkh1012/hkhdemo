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
 * FileInputStream��FileOutputStream �ļ��ֽ����������ļ��ֽ������ 
 * �ֱ�̳���InputStream��OutputStream,��Ҫ������д�������ļ�����ͼƬ����Ƶ�ȡ�
 * ʹ���ļ�ͨ���ķ�ʽЧ�ʱ�Buffer��ʽ�������ǿ������ļ�ʱ
 */
public class FileInputOutputStreamDemo {

	//ʵ���ļ�����
	public static void main(String[] args) {
		new FileInputOutputStreamDemo().copyFileByChannel();
	}
	
	//BufferInputStream��ʽ�����ļ�
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
			System.out.println("�����ļ���"+(e-s)+"ms");
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//���ļ�ͨ���ķ�ʽ�����ļ� since 1.7
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
			System.out.println("�ļ����ƣ�"+(e-s)+"ms");
			fi.close();
			in.close();
			fo.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

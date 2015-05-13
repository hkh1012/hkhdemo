package hkhdemo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * �ļ���Ĳ���
 * 
 */
public class FileDemo {

	public static void main(String[] args) {
		new FileDemo().createFile();
	}

	//�����ļ�
	public void createFile() {
		// 1���ļ��Ĵ�����ɾ��
		String path = "D:" + File.separator + "file.txt";
		File file = new File(path);
		try {
			if (file.exists()) {
				// ɾ��
				file.delete();
			} else {
				// ����
				file.createNewFile();
				// �ļ�д���ַ���
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
	
	//��ȡ�ļ�
	

}

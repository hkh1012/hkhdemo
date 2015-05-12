package hkhdemo.thread;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

class DaemonThread implements Runnable{

	@Override
	public void run() {
		System.out.println("进入守护线程");
		File file = new File("D:" + File.separator + "daemon.txt");
		OutputStream opt = null;
		try {
			opt = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num = 0;
		while (num < 1000) {
			try {
				opt.write(("\r\nword" + num).getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("写入文件word:" + num++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			opt.flush();
			opt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("退出守护线程");
	}
}

public class DaemonThreadDemo {
	public static void main(String[] args) {
		System.out.println("进入主进程");
		DaemonThread dt = new DaemonThread();
		Thread thread = new Thread(dt);
		thread.setDaemon(true);
		thread.start();
		Scanner sc = new Scanner(System.in);
		sc.next();
		System.out.println("退出主进程");
	}
}

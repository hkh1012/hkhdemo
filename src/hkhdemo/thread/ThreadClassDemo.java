package hkhdemo.thread;

public class ThreadClassDemo extends Thread {

	private int ticketCount = 50;
	
	@Override
	public void run() {
		//System.out.println("ӰԺһ����"+ticketCount+"��Ʊ");
		while(ticketCount>0){
			System.out.println(Thread.currentThread().getName()+"����һ��Ʊ����ʣ��"+--ticketCount+"��Ʊ��");
			try {
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void  main(String[] args){
		ThreadClassDemo tcd1 = new ThreadClassDemo();
		Thread tread1 = new Thread(tcd1,"����1");
		Thread tread2 = new Thread(tcd1,"����2");
		Thread tread3 = new Thread(tcd1,"����3");
		tread1.start();
		tread2.start();
		tread3.start();
		
	}

	
}

package hkhdemo.thread;

public class ThreadClassDemo extends Thread {

	private int ticketCount = 50;
	
	@Override
	public void run() {
		//System.out.println("影院一共有"+ticketCount+"张票");
		while(ticketCount>0){
			System.out.println(Thread.currentThread().getName()+"卖了一张票，还剩下"+--ticketCount+"张票。");
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
		Thread tread1 = new Thread(tcd1,"窗口1");
		Thread tread2 = new Thread(tcd1,"窗口2");
		Thread tread3 = new Thread(tcd1,"窗口3");
		tread1.start();
		tread2.start();
		tread3.start();
		
	}

	
}

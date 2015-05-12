package hkhdemo.thread;
import java.util.Date;


public class RunnableDemo implements Runnable{

	private int ticketCount = 20;
	
	@Override
	public void run() {
		while(ticketCount>0){
			ticketCount--;
			System.out.println(new Date().getTime()+":"+Thread.currentThread().getName()+"����һ��Ʊ����ʣ��"+ticketCount+"��Ʊ��");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args){
		RunnableDemo t1 = new RunnableDemo();
		Thread td1 = new Thread(t1,"����1");
		Thread td2 = new Thread(t1,"����2");
		td1.start();
		td2.start();
	}

}

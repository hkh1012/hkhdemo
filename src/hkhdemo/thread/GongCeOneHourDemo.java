package hkhdemo.thread;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GongCeOneHourDemo {

	public static void main(String[] args) {
		new GongCeOneHourDemo().test();
	}

	private void test() {
		GongCe toilet = new GongCe();
        
        boolean allowed = true;
        for (int i=0; allowed; i++) {
            People p = new People(i+"", toilet);
            allowed = toilet.allowIn(p);
        }
	}

	class GongCe {
		private int kengCount = 4;// 坑位总数
		private volatile int peopleIn = 0;// 进入的人数
		private ExecutorService holes = Executors.newFixedThreadPool(kengCount);

		private synchronized boolean allowIn(Runnable people) {
			if (peopleIn >= kengCount) {
				 try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			this.peopleIn(((People) people).name);
			holes.submit(people);
			return true;
			
		}

		private synchronized void enEn(String name, int v) {
			System.out.println("People[" + name + "] put in [" + v + "].");
		}

		private synchronized void peopleIn(String name) {
			System.out.println("People[" + name + "] comes in.");
			this.peopleIn++;
		}

		private synchronized void peopleOut(String name) {
			System.out.println("People[" + name + "] comes out.");
			this.peopleIn--;
			this.notifyAll();
		}

	}

	class People implements Runnable {
		private GongCe toilet;
		private String name;

		private People(String name, GongCe t) {
			this.toilet = t;
			this.name = name;
		}

		@Override
		public void run() {
			int ran = new Random().nextInt(20000);
			System.out.println("People[" + name + "] is en en en...[ "+ran+" ms ]");
			try {
				Thread.sleep(ran);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			toilet.enEn(name, new Random().nextInt(11));
			toilet.peopleOut(name);
		}

	}

}

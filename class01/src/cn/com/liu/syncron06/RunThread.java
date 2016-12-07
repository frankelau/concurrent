package cn.com.liu.syncron06;

/**
 * valitile关键字：使变量在多个线程间可见
 * 
 * @author liuzhao
 */
public class RunThread implements Runnable {
	private volatile boolean isRunning = true;

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		System.out.println("Thread is start!");
		while(isRunning){
			/**
			 * ...
			 */
		}
		System.out.println("Thread is end!");
	}

	public static void main(String[] args) {
		RunThread rt = new RunThread();
		Thread t1 = new Thread(rt);
		t1.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rt.setRunning(false);
		System.out.println("Already set isRunning false!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(rt.isRunning);
	}

}

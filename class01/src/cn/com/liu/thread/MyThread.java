package cn.com.liu.thread;

public class MyThread implements Runnable {
	public int count = 5;

	@Override
	public synchronized void run() {
		{
			count--;
			System.out.println(Thread.currentThread().getName() + ", count:" + count);
		}

	}

	public static void main(String[] args) {
		/**
		 * 1.当多个线程访问MyThread的run方法时，以排队的方式进行处理，这里排队是按CPU分配的先后顺序而定的
		 * 2.一个线程想要执行synchronized修饰的方法里的代码： 
		 * 	1).尝试获得锁
		 * 	2).如果拿到了锁，执行synchronized代码内容，拿不到锁，这个线程会不断尝试获得这把锁，直到拿到为止。
		 * 而且多个线程同时去竞争这把锁(也就是会有锁竞争的问题）。
		 */
		MyThread thread = new MyThread();
		Thread t1 = new Thread(thread, "t1");
		Thread t2 = new Thread(thread, "t2");
		Thread t3 = new Thread(thread, "t3");
		Thread t4 = new Thread(thread, "t4");
		Thread t5 = new Thread(thread, "t5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}

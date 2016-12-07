package cn.com.liu.syncron02;

/**
 * 对象锁的同步和异步问题:同步
 * 
 * @author liuzhao
 *
 */
public class MyObject2 {

	public synchronized void method1() {
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void method2() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		final MyObject2 mo = new MyObject2();
		/**
		 * t1现持有mo对象的Lock锁，t2线程如果这个时候调用对象中的同步（syncronized修饰的）方法则需要等待，也就是同步
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method2();
			}
		}, "t2");

		t1.start();
		t2.start();
	}
}

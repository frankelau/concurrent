package cn.com.liu.syncron02;

/**
 * 对象锁的同步和异步问题:异步
 * 
 * @author liuzhao
 *
 */
public class MyObject {

	public synchronized void method1() {
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void method2() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		final MyObject mo = new MyObject();
		
		/**
		 * t1现持有mo对象的Lock锁，t2线程可以以异步的方式调用对象中的非syncronized修饰的方法
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

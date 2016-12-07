package cn.com.liu.threadconn01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用countdownlatch实现实时的效果
 * 
 * @author liuzhao
 *
 */
public class ListAdd03 {

	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
//		final Object lock = new Object();
		CountDownLatch countdownlatch = new CountDownLatch(1);//代表t1发起几次后会执行t2
		final ListAdd03 list1 = new ListAdd03();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
//				synchronized (lock) {
					try {
						for (int i = 0; i < 10; i++) {
							list1.add();
							System.out.println("current Thread :" + Thread.currentThread().getName()
									+ " have added an element...");
							Thread.sleep(500);
							if (list1.size() == 5) {
//								lock.notify();
								countdownlatch.countDown();
								System.out.println(Thread.currentThread().getName() + " have notify another thread...");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				synchronized (lock) {
					while (true) {
						System.out.println(System.currentTimeMillis());
						if (list1.size() != 5) {
							try {
//								lock.wait();
								countdownlatch.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						
						}
						System.out.println(
								"current Thread recieved Notify:" + Thread.currentThread().getName() + "list size = 5");
						throw new RuntimeException();
					}
//				}

			}

		}, "t2");
		t2.start();
		t1.start();
	}

}

package cn.com.liu.threadconn01;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用notify和wait实现listAdd01的功能
 * 
 * @author liuzhao
 *
 */
public class ListAdd02 {

	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final Object lock = new Object();
		final ListAdd02 list1 = new ListAdd02();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						for (int i = 0; i < 10; i++) {
							list1.add();
							System.out.println("current Thread :" + Thread.currentThread().getName()
									+ " have added an element...");
							Thread.sleep(500);
							if (list1.size() == 5) {
								lock.notify();
								System.out.println(Thread.currentThread().getName() + " have notify another thread...");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					while (true) {
						System.out.println(System.currentTimeMillis());
						if (list1.size() != 5) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println(
								"current Thread recieved Notify:" + Thread.currentThread().getName() + "list size = 5");
						throw new RuntimeException();
					}
				}

			}

		}, "t2");
		t2.start();
		t1.start();
	}

}

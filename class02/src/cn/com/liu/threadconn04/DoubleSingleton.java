package cn.com.liu.threadconn04;

import java.util.concurrent.TimeUnit;

public class DoubleSingleton {
	private static DoubleSingleton ds;

	public static DoubleSingleton getInstance() {

		if (null == ds) {
			/*
			 * 模拟单例对象的初始化
			 */
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DoubleSingleton.class) {
				if (null == ds) {
					ds = new DoubleSingleton();
				}
			}
		}

		return ds;
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(DoubleSingleton.getInstance());
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(DoubleSingleton.getInstance());
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
}

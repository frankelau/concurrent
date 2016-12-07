package cn.com.liu.threadconn04;

public class InnerSingleton {

	private static class Singleton {
		private static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return Singleton.instance;
	}

	public static void main(String[] args) {

		InnerSingleton is = new InnerSingleton();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " getInstance: \n" + is.getInstance());
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " getInstance: \n" + is.getInstance());
			}
		}, "t2");

		t1.start();
		t2.start();
	}
}

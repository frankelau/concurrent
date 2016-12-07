package cn.com.liu.syncron01;
/**
 * @author liuzhao
 *一个对象一把锁
 */
public class MutiThread {
	private int num = 0;

	public synchronized void printNum(String tag) {
		try {
			if ("a".equals(tag)) {
				num = 100;
				System.out.println("tag a,set num over!");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b,set num over!");
			}

			System.out.println("tag:" + tag + ", num:" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 注意观察run方法输出顺序
	public static void main(String[] args) {
		// 两个不同的对象
		final MutiThread m1 = new MutiThread();
		final MutiThread m2 = new MutiThread();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				m2.printNum("b");
			}
		});
		t1.start();
		t2.start();
	}
}

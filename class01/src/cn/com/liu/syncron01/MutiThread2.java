package cn.com.liu.syncron01;
/**
 * @author liuzhao
 *	类级别的锁
 */
public class MutiThread2 {
	private static int num = 0;//静态成员变量
	
	//静态方法加锁
	public synchronized static void printNum(String tag) {
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
		final MutiThread2 m1 = new MutiThread2();
		final MutiThread2 m2 = new MutiThread2();

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

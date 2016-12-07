package cn.com.liu.syncron04;

/**
 * synchronized的重入
 * @author liuzhao
 *
 */
public class SyncDubbo2 {

	static class Main {
		public int i = 10;

		public synchronized void operationSup() {
			try {
				i--;
				System.out.println("Main print i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Sub extends Main {
		public synchronized void operationSub() {
			try {
				while (i > 0) {
					i--;
					System.out.println("Sub print i = " + i);
					Thread.sleep(100);
					this.operationSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		/**
		 * 父子继承关系的：子类的同步方法调用父类的同步方法也是线程安全的
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				new Sub().operationSub();
			}
		});
		t1.start();
	}
}

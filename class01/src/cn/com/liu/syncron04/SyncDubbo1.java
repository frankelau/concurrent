package cn.com.liu.syncron04;
/**
 * synchronized的重入
 * @author liuzhao
 *
 */
public class SyncDubbo1 {
	public synchronized void method1(){
		System.out.println("synchronized ... method1");
		method2();
	}
	public synchronized void method2(){
		System.out.println("synchronized ... method2");
		method3();
	}
	public synchronized void method3(){
		System.out.println("synchronized ... method3");
	}
	public static void main(String[] args) {
		final SyncDubbo1 sd = new SyncDubbo1();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//调用method1方法获得对象锁后，method1中调用method2,
				//method2调用method3都无需再次获得锁
				sd.method1();
			}
		});
		t1.start();
	}
	
	
	
}

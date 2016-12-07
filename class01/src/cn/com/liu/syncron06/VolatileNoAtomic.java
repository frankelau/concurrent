package cn.com.liu.syncron06;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {
//	private static volatile int count;
	private static volatile AtomicInteger count = new AtomicInteger(0);

	private static void addCount() {
		for (int i = 0; i < 1000; i++) {
//			count++;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	
	public void run(){
		addCount();
	}
	
	public static void main(String[] args) {
		VolatileNoAtomic[] vna = new VolatileNoAtomic[10];
		
		for (int i = 0; i < 10; i++) {
			vna[i] = new VolatileNoAtomic();
		}
		
		for (int i = 0; i < 10; i++) {
			vna[i].start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("最终结果："+VolatileNoAtomic.count);
		//最终结果并非10000，说明volatile并不能保证原子性,可以使用AtomicInteger保证原子性
	}
}

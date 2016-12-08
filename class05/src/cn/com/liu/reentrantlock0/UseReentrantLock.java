package cn.com.liu.reentrantlock0;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock {
	
	private Lock lock = new ReentrantLock();
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method1..");
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			lock.unlock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method1..");
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			lock.unlock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");

		}
	}
	
	public static void main(String[] args) {
		final UseReentrantLock ur = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.method1();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.method2();
			}
		}, "t2");

		t1.start();
		//等待10秒
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		
	}
	
	
}

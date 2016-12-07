package cn.com.liu.threadconn02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBlockingQueue {
	// 1.需要一个放元素的集合
	private final LinkedList<Object> list = new LinkedList<>();
	// 2.需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	// 3.需要指定上限和下限
	private final int minsize = 0;

	private final int maxsize;

	private final Object lock = new Object();

	public MyBlockingQueue(int size) {
		this.maxsize = size;
	}

	public int getSize() {
		return count.get();
	}

	// put(Object):把
	public void put(Object obj) {
		synchronized (lock) {
			while (count.get() == this.maxsize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			System.out.println(System.currentTimeMillis()+"新加入的元素为：" + obj);
			lock.notify();
			
		}
	}

	public Object take() {
		Object ret = null;
		synchronized (lock) {
			while (count.get() == this.minsize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 移除队列头上的元素
			ret = list.removeFirst();
			// 计数器递减
			count.decrementAndGet();
			System.out.println(System.currentTimeMillis()+"移除的元素为："+ret);
			// 唤醒另外一个线程
			lock.notify();
		}
		return ret;
	}

	public static void main(String[] args) {
		MyBlockingQueue mbq = new MyBlockingQueue(5);
		mbq.put("a");
		mbq.put("b");
		mbq.put("c");
		mbq.put("d");
		mbq.put("e");
		System.out.println("当前容器的长度为：" + mbq.getSize());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				mbq.put("f");
				mbq.put("g");
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				mbq.take();
				mbq.take();
			}
		}, "t2");
		t1.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}

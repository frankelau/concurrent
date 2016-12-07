package cn.com.liu.queue01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class UseBlockingQueue {
	public static void main(String[] args) throws Exception {
		/** 基于数组实现的有界阻塞队列 */
		/*
		 * ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		 * array.put("a"); array.put("b"); array.add("c"); array.add("d");
		 * array.add("e"); // array.add("f");
		 * //队列满了，抛异常java.lang.IllegalStateException: Queue full
		 * System.out.println(array.offer("g")); // array.put("h");//放不进去，阻塞等待
		 * System.out.println(array.offer("a", 3, TimeUnit.SECONDS));
		 * System.out.println(Arrays.asList(array).toString() + array.size());
		 * System.out.println(array.poll());
		 * System.out.println(Arrays.asList(array).toString() + array.size());
		 * System.out.println(array.peek());
		 * System.out.println(Arrays.asList(array).toString() + array.size());
		 * System.out.println(array.take());
		 * System.out.println(Arrays.asList(array).toString() + array.size());
		 */
		/**
		 * 总结：ArrayBlockingQueque基于数组实现的有界阻塞队列 添加元素：add
		 * 核心还是offer队列满了，抛异常java.lang.IllegalStateException: Queue full
		 * offer返回添加成功、失败 put放不进去，阻塞等待 取出元素(ReentrantLock)： take\poll取元素，并从队列删除
		 * peek取元素，不从队列删除
		 */

		// 阻塞队列
		/*LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.add("f");
		q.put("g");
		
		System.out.println(q.size());
		for (Iterator<String> iterator = q.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		List<String> list = new ArrayList<String>();
		System.out.println(q.drainTo(list, 3));//把元素移除放到集合类
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string);
		}
		q.poll();//空的集合返回null
		q.take();//空的会等待
		q.peek();//空的集合返回null
		System.out.println(q.toString());*/
		
		 final SynchronousQueue<String> q = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while(true){
						System.out.println(q.take());
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		 t1.start();
		 System.out.println("等待3秒");
		 TimeUnit.SECONDS.sleep(3);
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					 try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					q.add("asdasd");
				}
				
			}
		});
		t2.start();
		 
	}
}

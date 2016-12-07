package cn.com.liu.threadconn05;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 * @author alienware
 */
public class Tickets {

	public static void main(String[] args) {
		//初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap
		
		final Vector<String> tickets = new Vector<String>();
		
		//Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

		for(int i = 1; i<= 1000; i++){
			tickets.add("火车票"+i);
		}
		
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					tickets.add(new Random(5000).nextInt()+"");
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Iterator iterator =tickets.iterator();
				while(iterator.hasNext()){
					System.out.println(iterator.next());
				}
			}
		}, "t2");
		t1.start();
		t2.start();
		
//多线程同时取没问题		
//		for(int i = 1; i <=10; i ++){
//			new Thread("线程"+i){
//				public void run(){
//					while(true){
//						if(tickets.isEmpty()) break;
//						System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
//					}
//				}
//			}.start();
//		}
	}
}
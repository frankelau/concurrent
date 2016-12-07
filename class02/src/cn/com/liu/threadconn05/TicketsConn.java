package cn.com.liu.threadconn05;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 * @author alienware
 */
public class TicketsConn {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, String> tickets = new ConcurrentHashMap<>();
		

		for(int i = 1; i<= 1000; i++){
			tickets.put(i, "火车票"+i);
		}
		
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 1001; i<= 2000; i++){
					tickets.put(i, "火车票"+i);
				}
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Iterator iterator =tickets.keySet().iterator();
				while(iterator.hasNext()){
					System.out.println(tickets.get(iterator.next()));
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
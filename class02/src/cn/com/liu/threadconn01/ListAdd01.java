package cn.com.liu.threadconn01;

import java.util.ArrayList;
import java.util.List;

public class ListAdd01 {

	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final ListAdd01 list1 = new ListAdd01();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i = 0;i<10;i++){
						list1.add();
						System.out.println("current Thread :"+Thread.currentThread().getName()+"have added an element...");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					if(list1.size()==5){
						System.out.println("current Thread recieved Notify:"+Thread.currentThread().getName()+"list size = 5");
						throw new RuntimeException();	
					}
				}
			}
			
		},"t2");
		t1.start();
		t2.start();
	}

}

package cn.com.liu.queue02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {

	
	public static void main(String[] args) throws Exception{
		
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("id为3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("id为4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("id为1");
		
		Task t4 = new Task();
		t4.setId(0);
		t4.setName("id为0");
		//return this.id > task.id ? 1 : 0;
		q.add(t1);	//3
		System.out.println("容器：" + q);
		q.add(t2);	//4
		System.out.println("容器：" + q);
		q.add(t3);  //1
		System.out.println("容器：" + q);
		q.add(t4);
		// 1 3 4
		System.out.println("容器：" + q);
		System.out.println(q.peek().getId());
		System.out.println("容器：" + q);
		System.out.println(q.peek().getId());
		System.out.println(q.peek().getId());
		

		
	}
}

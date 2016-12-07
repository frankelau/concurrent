package cn.com.liu.threadconn06;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UseCopyOnWrite {

	public static void main(String[] args) {
		
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
		
		cwal.add("liu");
		System.out.println(cwal.get(0));
		
		cwas.add("xian");
		Iterator iterator = cwas.iterator();
		while(null != iterator && iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}

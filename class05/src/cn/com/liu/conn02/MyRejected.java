package cn.com.liu.conn02;

import java.net.HttpURLConnection;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString()+"重新提交");
		while(true){
			if(executor.getActiveCount()<executor.getMaximumPoolSize()){
				executor.submit(r);
				break;
			}
		}
	}

}

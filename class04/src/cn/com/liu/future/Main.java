package cn.com.liu.future;

public class Main {
	
	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		System.out.println("请求发送成功");
		try {
			Thread.sleep(5000);//模拟做其他事情
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("做完其他事情，开始取数据");
		String result = data.getRequest();
		System.out.println(result);
	}
}

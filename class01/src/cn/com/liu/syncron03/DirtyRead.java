package cn.com.liu.syncron03;

/**
 * 脏读
 * @author liuzhao
 *
 */
public class DirtyRead {
	private String username = "liuzhao";
	private String passwd = "lxz123456";

	public synchronized void setValue(String username, String passwd) {
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.passwd = passwd;

		System.out.println("setValue final Result: username=" + this.username + " , passwd=" + this.passwd);
	}
	
	/** 
	 * 在我们对一个对象的方法加锁的时候，需要考虑业务的整体性，即为setValue/getValue方法
	 * 同时加锁synchronized同步关键字，保证业务（service）的原子性,不然会出现业务错误（也
	 * 从侧面保证业务的一致性）。
	 */
	public synchronized void getValue(){
		System.out.println("getValue Result: username=" + this.username + " , passwd=" + this.passwd);
	}
	
	public static void main(String[] args) {
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("hongwei", "qhw123456");
			}
		});
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dr.getValue();
	}
}

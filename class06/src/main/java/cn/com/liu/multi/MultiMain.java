package cn.com.liu.multi;

import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class MultiMain {
	
	public static void main(String[] args) throws InterruptedException {
		int bufferSize = 1024*1024;
		//创建ringBuffer
		RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI, new EventFactory<Order>() {
			@Override
			public Order newInstance() {
				return new Order();
			}
		}, bufferSize, new YieldingWaitStrategy());
		//
		SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
		
		Consumer[] consumers = new Consumer[3];
		for(int i = 0; i < consumers.length; i++){
			consumers[i] = new Consumer("c" + i);
		}
		
		WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IntEventExceptionHandler(), consumers);
		ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		workerPool.start(executor);
		
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
		final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {  
        	final Producer p = new Producer(ringBuffer);
        	new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cyclicBarrier.await();//控制同时生产
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					for(int j = 0; j < 100; j ++){
						p.onData(UUID.randomUUID().toString());
					}
					System.out.println("我好");
					countDownLatch.countDown();
					System.out.println("你好");
				}
			}).start();
        } 
        System.out.println("---------------开始生产-----------------");
        countDownLatch.await();//控制都生产完，接着往下执行
       
        TimeUnit.SECONDS.sleep(15);//保证所有的消息消费完毕
        System.out.println("总数:" + consumers[0].getCount());
        workerPool.halt();
        executor.shutdown();
	}
	
	static class IntEventExceptionHandler implements ExceptionHandler {  
	    public void handleEventException(Throwable ex, long sequence, Object event) {}  
	    public void handleOnStartException(Throwable ex) {}  
	    public void handleOnShutdownException(Throwable ex) {}  
	} 
}

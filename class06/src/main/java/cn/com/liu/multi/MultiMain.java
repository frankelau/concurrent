package cn.com.liu.multi;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

public class MultiMain {
	
	public static void main(String[] args) {
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
		WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), new Consumer(null));
		
		
	}
	
}

package com.casit.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorConsumerByBlockingQueue {
	 private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);

	    public static void main(String[] args) {
	        ExecutorService service = Executors.newFixedThreadPool(15);
	        for (int i = 0; i < 5; i++) {
	            service.submit(new Productor(queue));
	        }
	        for (int i = 0; i < 10; i++) {
	            service.submit(new Consumer(queue));
	        }
	    }


	    static class Productor implements Runnable {

	        private LinkedBlockingQueue<Integer> queue;

	        public Productor(LinkedBlockingQueue<Integer> queue) {
	            this.queue = queue;
	        }

	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    Random random = new Random();
	                    int i = random.nextInt();
	                    System.out.println("生产者" + Thread.currentThread().getName() + "生产数据" + i);
	                    queue.put(i);
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    static class Consumer implements Runnable {
	        private LinkedBlockingQueue<Integer> queue;

	        public Consumer(LinkedBlockingQueue<Integer> queue) {
	            this.queue = queue;
	        }

	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    Integer element = (Integer) queue.take();
	                    System.out.println("消费者" + Thread.currentThread().getName() + "正在消费数据" + element);
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }


}

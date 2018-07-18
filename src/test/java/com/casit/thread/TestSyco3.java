package com.casit.thread;

public class TestSyco3  implements Runnable {
	
    private static int count = 0;

    public static void main(String[] args) {
    	TestSyco3 runnableItem = new TestSyco3();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread( new TestSyco3());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
        	synchronized(TestSyco3.class) {
        		count++;
        	}
        }
    }
}
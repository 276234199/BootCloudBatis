package com.casit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootCloudBatisApplicationTests {

	@Test
	public static void contextLoads() {
		
	}
	private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
    	

    	LinkedList<Thread> list = new LinkedList<>();
    	list.get(0);
    	
    	ArrayList<Thread> list2 = new ArrayList<>();
    	list2.get(0);
    	
        for (int i = 0; i < 5; i++) {
        	
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
        System.out.println("");
    }


}

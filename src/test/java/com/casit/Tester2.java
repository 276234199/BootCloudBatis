package com.casit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.casit.tools.SleepTools;

public class Tester2 {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		ArrayBlockingQueue<String> list2 = new ArrayBlockingQueue<String>(12345);
		for(int i =0 ;i<100;i++) {
			Thread t  = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j =0 ;j<100;j++) {
						list.add("qqq");
						list2.offer("qqq");
					}
				}
			});
			t.start();
		}
        
        
        SleepTools.second(1);

        System.out.println(list.size());
        System.out.println(list2.size());

	}

}

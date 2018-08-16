package com.casit.thread;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefTest {
	
	public static void main(String[] args) {
		AtomicReference<User> userRef = new AtomicReference<User>();
		
		User u1 = new User("u1");
		
		userRef.set(u1);
		
		u1.setName("uu");
		
		System.out.println(userRef.get().getName());
		
		User u2 = new User("u2");
		
		userRef.compareAndSet(u1, u2);
		
		System.out.println(userRef.get().getName());
		
		System.out.println(5555*12+3800*4+20000+500*4);
		System.out.println(4600*12+3800*4+20000+500*4);
	}
	
	
	static class User{
		private String name;

		public User(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	

}

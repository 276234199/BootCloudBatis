package com.casit;


public class Tester3 {

	public static void main(String[] args) {
		Ti t = new Ti(1);
		int i = t.i;
		i = 2;
		System.out.println(i);
		System.out.println(t.i);
		System.out.println("----------------");
		Ti2 ti2 = t.ti2;
		ti2 = new Ti2(777);
		System.out.println(t.ti2.i);
	}
	
	static class Ti{
		public int i;
		public  Ti2 ti2 = new Ti2(999);
		
		public Ti(int i) {
			super();
			this.i = i;
		}
		
	}
	
	static class Ti2{
		public int i;
		
		public Ti2(int i) {
			this.i = i;
		}
	}
}

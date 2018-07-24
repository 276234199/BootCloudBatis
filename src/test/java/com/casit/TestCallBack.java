package com.casit;


public class TestCallBack implements CallBack{

	public B b;
	
	public TestCallBack(B b) {
		super();
		this.b = b;
	}
	
	@Override
	public void call(String question) {
		b.answer(this, question); 
	}
	

	@Override
	public void callBack(String result) {
		System.out.println("B调用A定义的回调函数：回答者B告诉提问者A，问题的答案是："+ result);
	}
	
	static class B {
		public void answer(CallBack callBack, String question) {
			System.out.println("A调用B的接电话方法：我是回答者B，提问者A问的问题是：" + question);
			System.out.println("我是回答者B，我接完电话先去忙我自己的事！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result = "2";
			System.out.println("我是回答者B，我知道了答案是：" + result);
			callBack.callBack(result);
		}
	}


	public static void main(String[] args) {
		new TestCallBack(new B()).call("1+1=?");
	}
	

}

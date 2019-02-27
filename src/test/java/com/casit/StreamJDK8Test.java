package com.casit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* 类说明:
* @author zhouhai
* @version 创建时间：2019年2月27日 上午9:16:55
*/
public class StreamJDK8Test {
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1234,2345,3455,45,6,7,678,978,789,890,546,546,34,567,21);
		list = list.stream().filter(element -> element>666).collect(Collectors.toList());
		System.out.println(list);
		
		List<StreamTestUser> list2 = Arrays.asList(new StreamTestUser("a"),new StreamTestUser("b"),new StreamTestUser("c"),new StreamTestUser("d"),new StreamTestUser("e"));
		list2.stream().filter(element -> element.getName() == "a").forEach(element -> element.setName("dahubi"));
		System.out.println(list2);
	}

}

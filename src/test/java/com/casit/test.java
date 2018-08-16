package com.casit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class test {
	
	
	public static Integer getCRC16(Integer[] data)
    {
		Integer crc16 = 0xffff;
        for (int i = 0; i < data.length; i++)
        {
            crc16 ^= data[i];
            // ushort poly = 0xa001;
            for (int j = 0; j < 8; j++)
            {
                if ((crc16 & 0x01) == 1)
                    crc16 = (Integer)((crc16 >> 1) ^ 0xa001);
                else
                    crc16 >>= 1;
            }
        }

        return crc16;
    }
	
	public static void main(String[] args) throws Exception{
		
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 800; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        System.out.println(sb.toString());
        
        System.out.println("----------------------");
		
		Map<String, String> map = new HashMap<>();
		//new ConcurrentHashMap<>()
		
		map.put("1", "2");
		map.put("2", "2");
		
		System.out.println(map.put("1", "3"));
		System.out.println(map.get("1"));
		System.out.println(map.get("2"));
		
		
		System.out.println("----------------------");
		
		System.out.println(1 << 30);
		System.out.println(Integer.MAX_VALUE);
		
		System.out.println("----------------------");

		
	
		try {
			System.out.println("----------------------");
			return;
		}finally {
			System.out.println("````````");
		}
		
//		Integer[] data = new Integer[] { 0xA5, 0x1E, 0xD1, 0x00, 0x07, 0x01, 0x00, 0x00, 0x96, 0x00, 0x00, 0x96 };
//		Integer res = getCRC16(data);
//		
//		System.out.println(res);
//		
//		System.out.println(System.nanoTime());
//		System.out.println(new Date().getTime());
		
	}

}

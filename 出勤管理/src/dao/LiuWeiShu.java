package dao;

import java.util.Random;

import org.junit.Test;
/**
 * 随机六位口令
 * @author 王少彬。。。。
 *
 */
public class LiuWeiShu {
	public static String randomWord() {
		// TODO 自动生成的方法存根
		   Random random = new Random();
		   String result="";
		   for (int i=0;i<6;i++){
			   result+=random.nextInt(10);
		   }
		return result;
	}
	
	
	@Test
	public void fun2() throws Exception{
		
		String a=randomWord();
		System.out.println(a);
	}
}

package javacore.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SangShanChun
 * @date 2020年8月8日
 * @brief lambda表达式
 */
public class LambdaTest {

	public static void main(String[] args) {
		method();
		method2();
	}

	public static void method() {
		String[] arr = {"a", "aaa", "aa", "aaaa"};
		Arrays.sort(arr, (first, second) -> first.length() - second.length()); 
		List<String> list = Arrays.asList(arr);
		list.forEach(str -> {
			System.out.println(str);
		});
	}
	
	public static void method2() {
		Map<String, String> map = new HashMap<>();
		//lambda表达式内部不能赋值，因为传入的是变量的一个副本，而非变量本身
		map.forEach((k, v) -> {
			System.out.println("key:" + k + "  value:" + v);
		});
	}

}

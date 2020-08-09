package javacore.chapter3;

import java.time.LocalDate;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.reverse;

/**
 * @author SangShanChun
 * @date 2020年8月5日
 * @brief 数组
 */
public class ArrayTest {
	/**
	 * Java没有多维数组，被称为数组的数组，数组中出现的异常常为数组越界异常。
	 */

	public static void main(String[] args) {
		Random r = new Random();
		// 默认值为0
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(1000);
		}
		Arrays.sort(arr);
		reverse(arr);
		/**
		 * forEach循环中不能赋值当使用forEach来迭代访问数组元素时，forEach中的循环变量只是相当于一个临时变量，
		 * 系统会把数组元素依次赋给这个临时变量，而这个临时变量并不是数组元素，它只保存了数组元素的值。相当于只读迭代输出
		 */
		for (int current : arr) {
			System.out.println(current);
		}

		// 二维数组初始化时必须初始化行
		int[][] arrt = new int[10][10];
		for (int[] arr1 : arrt) {
			for (int temp : arr1) {
				// 当二维数组不初始化时会通过编译，但是运行时空指针异常
				System.out.println(temp);
			}
		}
		Date birthday = new Date();
//		birthday = null;
		// 赋值null后会产生运行时错误
		System.out.println(birthday.toString());

		LocalDate localDate = LocalDate.now();
		System.out.println("localDate = " + localDate);

		// Java中必须使用clone才能获得完整的对象拷贝

	}

	/**
	 * 对象的克隆
	 */
	@Override
	public ArrayTest clone() {
		ArrayTest t = null;
		try {
			// 如果实例变量是引用类型的，也需要clone
			t = (ArrayTest) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}

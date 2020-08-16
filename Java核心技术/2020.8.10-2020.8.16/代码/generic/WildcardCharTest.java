package javacore.chapter4.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sangshanchun
 * @brief 通配符
 * @date 2020/8/12 19:35
 */
public class WildcardCharTest {

	public static void main(String[] args) {
		List<? extends Number> list = new ArrayList<>();
		// list.add(new Integer(1));
		Number number = list.get(0);

		List<? super Integer> list1 = new ArrayList<>();
		list1.add(new Integer(1));
		// Number number1 = list1.get(0);

		/**
		 * 上界类型通配符：add方法受限，因为无法确定add的类型
		 * 下界类型通配符：get方法受限，因为无法确定get的对象的具体类型
		 */
	}
}

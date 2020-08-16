package javacore.chapter4.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sangshanchun
 * @brief 泛型
 * @date 2020/8/11 21:06
 */
public class GenericTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("str");
		try {
			// 通过反射可以添加Integer类型的元素，说明泛型被擦除。在不指定泛型的时候，泛型为Object。
			list.getClass().getMethod("add", Object.class).invoke(list, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 输出[str, 1]
		System.out.println(list);

		Fruit fruit = new Fruit();
		// 指定泛型时，类型是该实例类型或子类
		try {
			fruit.getClass().getMethod("setFirst", Number.class).invoke(fruit, Integer.MIN_VALUE);
			// 正常输出
			System.out.println(fruit.getFirst());
			fruit.getClass().getMethod("setFirst", Number.class).invoke(fruit, Double.MAX_VALUE);
			// 正常输出
			System.out.println(fruit.getFirst());

			// IllegalArgumentException
			fruit.getClass().getMethod("setSecond", Number.class).invoke(fruit, "str");
			System.out.println(fruit.getSecond());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List list1 = new ArrayList<String>();
		list1.add(1);
		list1.add("str");
		// 输出[1, str]，说明类型检查是针对引用的
		System.out.println(list1);


	}
}

class Fruit<T extends Number> {
	private T first;
	private T second;

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}
}

/**
 * 在定义对象时指定T，静态域在类加载时就加载，所以报错
 * @param <T>
 */
class Test1<T> {
	// 编译错误
	// public static T one;
	// 编译错误
    // public static  T show(T one){
    //	  return null;
    // }
}

class Test2<T> {
	// 在泛型方法中使用的T是自己在方法中定义的T，而不是泛型类中的T
	public static <T> T show(T one){
		return one;
	}

	public static void main(String[] args) {
		System.out.println(show("str"));
	}
}

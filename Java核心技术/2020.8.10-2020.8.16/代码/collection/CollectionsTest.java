package javacore.chapter4.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sangshanchun
 * @brief Collections
 * @date 2020/8/15 11:19
 */
public class CollectionsTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		// 返回一个不可修改的集合，除此之外，Collections工具类提供了多种对集合操作的方法
		List<String> list1 = Collections.unmodifiableList(list);
		// 运行时异常java.lang.UnsupportedOperationException
		list1.add("4");
		System.out.println(list1);
	}
}

package javacore.chapter4.collection;

import java.util.*;

/**
 * @author sangshanchun
 * @brief list
 * @date 2020/8/13 20:44
 */
public class ListTest {
	public static void main(String[] args) {
		// 顺序访问快，删除慢
		List<String> list1 = new ArrayList<>();
		// 顺序访问慢，删除快
		List<String> list2 = new LinkedList<>();
		// 根据是否实现RandomAccess接口来决定是哪种集合，采用哪种遍历方式
		if(list1 instanceof RandomAccess) {
			// for循环遍历快
		}
		if(!(list2 instanceof RandomAccess)) {
			// Iterator遍历快
		}
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		List<String> subList = list.subList(1, 3);
		subList.removeAll(subList);
		subList.add("100");
		subList.add("100");
		// 输出[1, 100, 100, 4, 5]，说明subList会修改原集合元素
		System.out.println(list);
	}
}

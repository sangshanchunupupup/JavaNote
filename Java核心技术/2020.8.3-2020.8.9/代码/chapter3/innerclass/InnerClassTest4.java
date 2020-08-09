package javacore.chapter3.innerclass;

/**
 * @author SangShanChun
 * @date 2020年8月9日
 * @brief 静态内部类
 */
public class InnerClassTest4 {
	public static void main(String[] args) {
		new OuterClass4();
	}
}

class OuterClass4 {
	int a = 10;
	static int b = 1;
	static {
		System.out.println("OuterClass");
	}

	static class InnerClass {
		static {
			System.out.println("StaticInnerClass");
		}
		public InnerClass() {
			//静态内部类只能访问外部类的静态属性
//			System.out.println(a);
			System.out.println(b);
		}
	}
}

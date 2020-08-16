package javacore.chapter4.generic;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/8/15 11:56
 */
public class BridgeMethod {

}
class A<T>{
	public T get(T a){
		return a;
	}
}
class B extends A<String>{
	@Override
	public String get(String a){
		return a;
	}
}

/**
 * 类型擦除后加入桥接方法，否则仅实现重载而不是重写
 */
/**
 * class A{
 *     public Object get(Object a){
 *         return a;
 *     }
 * }
 * class B extends A{
 *     @override
 *     public String get(String a){
 *         return a;
 *     }
 *     // 桥接方法
 *     public Object get(Object a){
 *         return get((String)a)
 *     }
 * }
 */
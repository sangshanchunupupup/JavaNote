package javacore.chapter3;

/**
 * @author SangShanChun
 * @date 2020年8月6日
 * @brief 继承、多态
 */
public class Manager extends Employee {
	/*
	 * 子类在继承父类时，如果没有相同的带参构造方法，那么他就 需要在其构造方法中明确的通过super()调用父类的带参构造方
	 * 法，否则构造不出父类，也无法构造子类。
	 */
	/**
	 * 在方法覆盖的时候，子类方法的可见性不可小于父类方法的可见性
	 */
	public Manager(String name, double salary) {
		super(name, salary);
	}

	public static void main(String[] args) {
		Employee e = new Employee("a", 100);
		e.setBonus(50);
		Employee m = new Manager("b", 200);
		m.setBonus(100);
		System.out.println("a = " + e.getSalary());
		System.out.println("b = " + m.getSalary());
		e.method();
		m.method();

		// 向上转型报ClassCastException
//		Manager m1 = (Manager) new Employee();
		System.out.println(m instanceof Employee);
		System.out.println(e instanceof Manager);

		// 多态中，父类中调用被子类重写的方法如果不加super同样是调用的子类的方法。
		m.method1();

	}

	@Override
	public void method() {
		System.out.println("子类");
	}

	@Override
	public void method1() {
		super.method1();
		method2();
	}

	@Override
	public void method2() {
		System.out.println("子类method2");
	}

}

class Employee {
	private String name;
	private double salary;
	private double bonus;

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary + bonus;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee() {

	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public void method() {
		System.out.println("父类");
	}

	public void method1() {
		System.out.println("父类method1");
		method2();
	}

	public void method2() {
		System.out.println("父类method2");
	}

}
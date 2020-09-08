package day2.com.atguigu.java8;

import day1.com.atguigu.java8.MyFun;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}

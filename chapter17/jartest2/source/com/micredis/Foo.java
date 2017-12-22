package com.micredis;

public class Foo {
	private String name;
	private int num;

	public Foo() {
		name = "foo";
		num = 1;
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public void setName(String n) {
		name = n;
	}

	public void setNum(int nmbr) {
		num = nmbr;
	}

	public void say() {
		System.out.println(this.getName() + " " + this.getNum());
	}
}
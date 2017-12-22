package com.micredis;

public class Bar {
	private String name;

	public Bar() {
		name = "bar";
	}

	public Bar(Foo foo) {
		name = foo.getName() + "bar";
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public void say() {
		System.out.println(this.getName());
	}
}
package com.wk.composite;

public class Leaf implements ICrop{

	private String name = "";
	private String position = "";
	private int salary = 0;
	
	public Leaf(String name,String postion,int salary){
		super();
		this.name = name;
		this.salary = salary;
		this.position = position;
	}
	
	@Override
	public String getInfo() {
/*		StringBuilder info = null;
		info.append("姓名：").append(name).append("\t薪水：").append(salary).append("\t职位：").append(position);
		return info.toString();*/
		String info = "";
		info = "姓名:" + this.name;
		info = info + "\t职位:" + this.position;
		info = info + "\t薪水:" + this.salary;
		return info;
	}

}

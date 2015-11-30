package com.wk.composite;

import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		Branch ceo = compositeTree();
		System.out.println(ceo.getInfo());
		System.out.println(getTreeInfo(ceo));
	}

	public static Branch compositeTree(){
		Branch ceo = new Branch("big boss","ceo",1000000000);
		Branch developDep = new Branch("god coder","development manager",10000000);
		Branch salesDep = new Branch("big mouth","salement manager",10000000);
		Branch fianceDep = new Branch("money ghost","finace manager",1000000);		
		Leaf employee1 = new Leaf("coder a","coder",500000);
		Leaf employee2 = new Leaf("coder b","coder",500000);
		Leaf employee3 = new Leaf("coder c","coder",500000);
		Leaf employee4 = new Leaf("coder d","coder",500000);		
		Leaf employee5 = new Leaf("saler 1","saler",400000);
		Leaf employee6 = new Leaf("saler 2","saler",400000);
		Leaf employee7 = new Leaf("saler 3","saler",400000);
		Leaf employee8 = new Leaf("saler 4","saler",400000);		
		Leaf employee9 = new Leaf("accountant a1","accountant",400000);
		Leaf employee10 = new Leaf("accountant a2","accountant",400000);		
		Leaf secretary = new Leaf("secretary miss","secretary",700000);
		developDep.addSubordinate(employee1);
		developDep.addSubordinate(employee2);
		developDep.addSubordinate(employee3);
		developDep.addSubordinate(employee4);
		salesDep.addSubordinate(employee5);
		salesDep.addSubordinate(employee6);
		salesDep.addSubordinate(employee7);
		salesDep.addSubordinate(employee8);
		fianceDep.addSubordinate(employee9);
		fianceDep.addSubordinate(employee10);
		ceo.addSubordinate(secretary);
		ceo.addSubordinate(fianceDep);
		ceo.addSubordinate(salesDep);
		ceo.addSubordinate(developDep);
		return ceo;	
	}
	
	public static String getTreeInfo(Branch root){
		List<ICrop> cropList = root.getSubordinate();
		String info = "";
		for(ICrop crop : cropList){
			if(crop instanceof Leaf){
				info = info + crop.getInfo() + "\n";
			}else{
				info = info + crop.getInfo() + "\n" + getTreeInfo((Branch) crop);
			}			
		}
		return info;
	}
}

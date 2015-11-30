package com.wk.composite;

import java.util.List;

public interface IBranch {
	
	public void addSubordinate(ICrop crop);
	
	public List<ICrop> getSubordinate();
}

package com.wg.salescount.basedao;

import java.util.List;

import com.wg.salescount.util.Pager;

public interface BaseDAO<T> {
	public Pager query(Pager pager, T po);

	public List<T> queryNoPager(T po);

	public int delete(T po);

	public T save(T po);

	public T update(T po);

	public T detail(T po);	
	
	public T queryById(String id);
}

package com.hw.hwsafe.platform.paging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：framework
 * 类名称：PagerData
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-6-18 下午6:36:22
 * 修改人：孟繁波
 * 修改时间：2012-6-18 下午6:36:22
 * 修改备注：
 * @version 
 * 
 */
public class PagerData {
	private int page;	// 页码
	private int pageSize; 	// 每页条数
	private String sidx;	// 排序字段
	private String sord;	// 排序方式
	private String cellIndex;
	private String pk;
	
	private int startRowNum;	// 开始行
	private int endRowNum;		// 结束行
	
	private int total;	// 总页数
	private int records;	// 总条数
	private List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();	// 页数据
	
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	public int getEndRowNum() {
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(List<Map<String,Object>> list) {
		if (list.size() > 0 && list.get(0) != null && list.get(0).get("COUNT") != null) {
			try {
				int count = Integer.parseInt(list.get(0).get("COUNT").toString());
				// 设置总条数
				this.records = count;
				// 设置总页数
				this.total = (int) Math.ceil((double) count / pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
/*	
	public void setRecords(int records) {
		// 设置总条数
		this.records = records;
		// 设置总页数
		this.total = (int) Math.ceil((double) records / pageSize);
	}
*/	
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	
	public void setRows(List<Map<String,Object>> list) {
		if(getCellIndex() == null){
			return;
		}
		try {
			String[] fields = getCellIndex().toUpperCase().split(",");
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> po = list.get(i);
				List<Object> cellList = new ArrayList<Object>();
				// 获取指定列的值
				for (int j = 0; j < fields.length; j++) {
					Object value = po.get(fields[j].toUpperCase());
					cellList.add(value);
				}
				
				// jqGrid 数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", po.get(pk.toUpperCase()));
				map.put("cell", cellList);
				
				// add
				this.rows.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	
	public void setRows(List list, Class<?> clazz) {
		try {
			String[] fields = getCellIndex().split(",");
			for (int i = 0; i < list.size(); i++) {
				Object po = list.get(i);
				List<Object> cellList = new ArrayList<Object>();
				// 获取指定列的值
				for (int j = 0; j < fields.length; j++) {
					String getMethodName = "get" + fields[j].substring(0,1).toUpperCase() + fields[j].substring(1);
					Method method = clazz.getDeclaredMethod(getMethodName);
					Object value = method.invoke(po);
					cellList.add(value);
				}
				
				// jqGrid 数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", String.valueOf(i + 1));
				map.put("cell", cellList);
				
				// add
				this.rows.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/	
	public String getCellIndex() {
		return cellIndex;
	}
	public void setCellIndex(String cellIndex) {
		this.cellIndex = cellIndex;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
}

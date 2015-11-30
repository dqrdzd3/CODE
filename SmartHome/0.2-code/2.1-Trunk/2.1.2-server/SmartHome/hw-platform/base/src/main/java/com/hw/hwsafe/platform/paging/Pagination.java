package com.hw.hwsafe.platform.paging;


/**
 * 列表分页
 * 
 * 项目名称：framework
 * 类名称：Pagination
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-9-6 下午8:42:28
 * 修改人：Administrator
 * 修改时间：2012-9-6 下午8:42:28
 * 修改备注：
 * @version 
 *
 */
public class Pagination {

	/**
	 * 页面容量
	 */
	private int pageSize=10;
	/**
	 * 当前页号
	 */
	private int pageNO;
	/**
	 * 总页数
	 */
	private int pageNum;
	/**
	 * 起始位置
	 */
	private int startRow=1;
	/**
	 * 结束位置
	 */
	private int endRow=10;
	/**
	 * 总记录数
	 */
	private int totalRow=0;
	
	/**
	 * 分页工具条
	 */
	@SuppressWarnings("unused")
	private String pageBar;


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNO() {
		return pageNO;
	}

	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}

	public int getPageNum() {
		return pageNum = totalRow%pageSize == 0 ? totalRow/pageSize : totalRow/pageSize+1 ;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		startRow = (pageNO-1)*pageSize+1;
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		endRow = pageNO*pageSize;
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public String getPageBar(){
		StringBuffer sb = new StringBuffer();
		//总条数
		sb.append("总记录数")
			.append(totalRow)
			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		//当前页
		sb.append("当前页")
			.append(pageNO)
			.append("/");
		//共几页
		sb.append("共")
		.append(getPageNum())
		.append("页")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		//首页
		sb.append("<a href=\"javascript:;\" onclick=")
			.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':1}}));\" >")
			.append("首页")
			.append("</a>")
			.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		//上一页
		sb.append("<a href=\"javascript:;\" onclick=");
		if(pageNO>1){
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+(pageNO-1)+"}}));\" >");
		}else{
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':1}}));\" >");
		}
		sb.append("上一页");
		sb.append("</a>")
			.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		
		//中间页前后2页
//		int half = pageNum%2==0 ? pageNum/2 : (pageNum/2+1);
//		
//		int half_2 = half-2;
//		if(half_2<1){
//			half_2 = 1;
//		}
//		if(half_2==1){
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half_2+"}));\">")
//			.append(half_2)
//			.append("</a>")
//			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//		}else{
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half_2+"}));\">")
//			.append(half_2)
//			.append("</a>")
//			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//			
//			int half_1 = half-1;
////			if(half_1<1){
////				half_1 = 1;
////			}
//			
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half_1+"}));\">")
//			.append(half_1)
//			.append("</a>")
//			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//		}
//		
//		if(half>pageNum){
//			half = pageNum;
//		}
//		
//		if(half == pageNum){
//			
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half+"}));\">")
//			.append(half)
//			.append("</a>")
//			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//		}else{
//			
//			int half1 = half+1;
//			if(half1>pageNum){
//				half1 = pageNum;
//			}
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half1+"}));\">")
//			.append(half1)
//			.append("</a>")
//			.append("&nbsp;&nbsp;&nbsp;&nbsp;");
//			
//			int half2 = half+2;
//			if(half2>pageNum){
//				half2 = pageNum;
//			}
//			sb.append("<a href=\"javascript:$.loadPage($.extend(pageOption,{'pageNO':"+half2+"}));\">")
//			.append(half2)
//			.append("</a>")
//			.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
//		}
//		
			
		
		//下一页
		sb.append("<a href=\"javascript:;\" onclick=");
		if(pageNO<pageNum){
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+(pageNO+1)+"}}));\" >");
		}else{
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+getPageNum()+"}}));\" >");
		}
		sb.append("下一页");
		sb.append("</a>")
			.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		//尾页
		
		sb.append("<a href=\"javascript:;\" onclick=")
		.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+getPageNum()+"}}));\" >")
		.append("尾页")
		.append("</a>")
		.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		
		//转到第几页
		sb.append("转到第")
		.append("<input type=\"text\" id=\"toPage\" width=\"25\" onkeydown=\"if(event.keyCode==13){$('#pageBtn','#pageDiv').click();}\"/>")
		.append("页")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		
		
		//提交
		sb.append("<input type=\"button\" id=\"pageBtn\" value=\"提交\" onclick=\"javascript:$.pageNOCheck("+getPageNum()+");\" />");
			
		return sb.toString();
	}
	
}

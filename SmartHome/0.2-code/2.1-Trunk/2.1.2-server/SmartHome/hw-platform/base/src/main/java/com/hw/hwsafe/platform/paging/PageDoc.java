package com.hw.hwsafe.platform.paging;

/**
 * 文章分页类
 * 
 * 项目名称：framework
 * 类名称：PageDoc
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-9-5 下午2:04:57
 * 修改人：Administrator
 * 修改时间：2012-9-5 下午2:04:57
 * 修改备注：
 * @version 
 *
 */
public class PageDoc {

	/**
	 * 当前页号
	 */
	private int pageNO;
	/**
	 * 总页数
	 */
	private int pageNum;
	/**
	 * 分页工具条
	 */
	@SuppressWarnings("unused")
	private String pageBar;

	private String pageContent;
	
	public int getPageNO() {
		return pageNO;
	}
	
	public void setPageNO(int pageNO) {
		this.pageNO = pageNO;
	}

	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageBar(){
		StringBuffer sb = new StringBuffer();
		//当前页
		sb.append("当前页")
			.append(pageNO)
			.append("/");
		//共几页
		sb.append("共")
		.append(pageNum)
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
		
			
		
		//下一页
		sb.append("<a href=\"javascript:;\" onclick=");
		if(pageNO<pageNum){
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+(pageNO+1)+"}}));\" >");
		}else{
			sb.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+pageNum+"}}));\" >");
		}
		sb.append("下一页");
		sb.append("</a>")
			.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		//尾页
		
		sb.append("<a href=\"javascript:;\" onclick=")
		.append("\"javascript:$.loadPage($.extend(pageOption,{pageparams:{'pageNO':"+pageNum+"}}));\" >")
		.append("尾页")
		.append("</a>")
		.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		
		//转到第几页
		sb.append("转到第")
		.append("<input type=\"text\" id=\"toPage\" width=\"25\" onkeydown=\"if(event.keyCode==13){$('#pageBtn','#pageDiv').click()}\"/>")
		.append("页")
		.append("&nbsp;&nbsp;");
		
		
		//提交
		sb.append("<input type=\"button\" id=\"pageBtn\" value=\"提交\" onclick=\"javascript:$.pageNOCheck("+getPageNum()+");\" />");
			
		return sb.toString();
	}
	
}

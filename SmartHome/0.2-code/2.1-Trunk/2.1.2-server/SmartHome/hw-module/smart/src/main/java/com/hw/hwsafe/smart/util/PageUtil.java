package com.hw.hwsafe.smart.util;
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.Vector;  
  
public class PageUtil {  
  
    /** 
     * 分页 
     * @author Etwo 
     */  
      
    /* 
     * 分页要素： 
     * 1、首页             first 
     * 2、最后一页           last 
     * 3、页总数                pageCount 
     * 4、总共显示的数据条数  rowCount 
     * 5、每页显示的数据条数  pageRowCount 
     * 6、当前页                curPage  
     * 7、得到的数据          data 
     */  
      
    /** 
     * 1、首页             first 
     */  
    private int first;  
      
    /** 
     * 2、最后一页           last 
     */  
    private int last;  
      
    /** 
     * 3、页总数                pageCount 
     */  
    private int pageCount;  
      
    /** 
     * 4、总共显示的数据条数  rowCount 
     */  
    private int rowCount;  
      
    /** 
     * 5、每页显示的数据条数  pageRowCount 
     */  
    private int pageRowCount;  
      
    /** 
     * 6、当前页                curPage  
     */  
    private int curPage;  
      
    /** 
     * 7、得到的数据          data 
     */  
    private Collection data;  
      
    /** 
     * 默认显示10条数据 
     */  
    public PageUtil(Collection data, int curPage) {  
        this.data = data;  
        this.curPage = curPage;  
        this.pageRowCount = 10;  
        this.rowCount = data.size();  
        this.pageCount = (int)Math.ceil((double)this.rowCount / (double)this.pageRowCount);  
    }  
      
    /** 
     * 自行设置每页显示的条数 
     */  
    public PageUtil(Collection data, int curPage, int pageRowCount) {  
        this.data = data;  
        this.curPage = curPage;  
        this.pageRowCount = pageRowCount;  
        this.rowCount = data.size();  
        this.pageCount = (int)Math.ceil((double)this.rowCount / (double)this.pageRowCount);  
    }  
      
    /** 
     * 首页取得 
     * @return 
     */  
    public int getFirst() {  
        return this.first = 1;  
    }  
      
    /** 
     * 最后一页取得 
     * @return 
     */  
    public int getLast() {  
        return this.last = this.pageCount;  
    }  
      
    /** 
     * 上一页 
     * @return 
     */  
    public int previous() {  
        return (this.curPage > 1) ? this.curPage - 1 : 1;  
    }  
      
    /** 
     * 下一页 
     * @return 
     */  
    public int next() {  
        return (this.curPage < this.pageCount) ? this.curPage + 1 : this.pageCount;  
    }  
      
    /** 
     * 判断是否是首页 
     * @return 
     */  
    public boolean isFirst() {  
        return (this.curPage == 1) ? true : false;  
    }  
      
    /** 
     * 判断是否是最后一页 
     * @return 
     */  
    public boolean isLast() {  
        return (this.curPage == this.pageCount) ? true : false;  
    }  
      
    /** 
     * 当前要显示的数据取得 
     * @return 
     */  
    public Collection getData() {  
        Collection curData = null;  
          
        if(data != null) {  
            //定义每一页开始到最后的显示的行数  
            int start, end;  
            start = (this.curPage - 1) * this.pageRowCount;  
            //判断当前显示的数据是否足以显示一整页  
            if(start + this.pageRowCount > this.rowCount) {  
                end = this.rowCount;  
            } else {  
                end = start + this.pageRowCount;  
            }  
              
            ArrayList arrayData = null;  
            Vector vectorData = null;  
            ArrayList arrayCurData = new ArrayList();  
            Vector vectorCurData = new Vector();  
            boolean isArrayData = true;  
            //判断是属于哪一种集合  
            if(data instanceof ArrayList) {  
                arrayData = (ArrayList)data;  
                isArrayData = true;  
            } else if(data instanceof Vector) {  
                vectorData = (Vector)data;  
                isArrayData = false;  
            }  
            //循环得到每一页的数据  
            for(int i = start; i < end; i++) {  
                if(isArrayData) {  
                    arrayCurData.add(arrayData.get(i));  
                } else {  
                    vectorCurData.add(vectorData.get(i));  
                }  
            }  
              
            if(isArrayData) {  
                curData = arrayCurData;  
            } else {  
                curData = vectorCurData;  
            }  
        }  
          
        return curData;  
    }  
      
    /** 
     * 客户端显示的工具条 
     */  
    public String getToolBar(String url) {  
        String str, temp;  
          
        //用于判断url中是否存在？  
        if(url.indexOf("?") == -1) {  
            temp = "?";  
        } else {  
            temp = "&";  
        }  
          
        str = "<form method='post' name='frmPage' action='" + url + "'> ";  
        str += "";  
        str += "<span style='font-size: 12px; font-weight: bold;'>";  
        //判断是否是首页  
        if(isFirst()) {  
            str += "首页 上一页 ";  
        } else {  
            str += "<a href='" + url + temp + this.getFirst() + "'>首页</a> ";  
            str += "<a href='" + url + temp + "cur_page=" + this.previous() + "'>上一页</a> ";  
        }  
        //判断是否是最后一页  
        if(isLast()) {  
            str += "下一页 尾页 ";  
        } else {  
            str += "<a href='" + url + temp + "cur_page=" + this.next() + "'>下一页</a> ";  
            str += "<a href='" + url + temp + "cur_page=" + this.getLast() + "'>尾页</a> ";  
        }  
        str += " 共<b>" + this.rowCount + "</b>条记录 ";  
        str += " 转到<select name='page' onChange=\"location='" + url + temp + "cur_page='+this.options[this.selectedIndex].value\">";  
          
        for(int i = 1; i <= this.pageCount; i++) {  
            //判断是否是当前页，若是，则默认为选中当前页  
            if(i == curPage)  
                str += "<option value='" + i + "' selected>第" + i + "页</option>";  
            else  
                str += "<option value='" + i + "'>第" + i + "页</option>";  
        }  
        str += "</select></span></form>";  
        return str;  
    }  

	public String getToolBarForBootStrap(String url) {
		 String str, temp;  
         
	        //用于判断url中是否存在？  
	        if(url.indexOf("?") == -1) {  
	            temp = "?";  
	        } else {  
	            temp = "&";  
	        }  
	          
	        str = "<form method='post' name='frmPage' action='" + url + "'> ";  
	        str += "";  
	        str += "<span style='font-size: 12px; font-weight: bold;'>";  
	        //判断是否是首页  
	        if(isFirst()) {  
	            str += "首页 上一页 ";  
	        } else {  
	        	str += "<a class='btn' onclick=\"location='"+url + temp + "cur_page=" + this.getFirst()+"'\">首页</a> ";  
	        	str += "<a class='btn' onclick=\"location='"+url + temp + "cur_page=" + this.previous()+"'\">上一页</a> ";  
	        }  
	        //判断是否是最后一页  
	        if(isLast()) {  
	            str += "下一页 尾页 ";  
	        } else {  
	        	str += "<a class='btn' onclick=\"location='" + url + temp + "cur_page=" + this.next() + "'\">下一页</a> ";  
	        	str += "<a class='btn' onclick=\"location='" + url + temp + "cur_page=" + this.getLast() + "'\">尾页</a> ";  
	        }  
	        str += " 共<b>" + this.rowCount + "</b>条记录 ";  
	        str += " 转到<select style='width:80px' name='page' onChange=\"location='" + url + temp + "cur_page='+this.options[this.selectedIndex].value\">";  
	          
	        for(int i = 1; i <= this.pageCount; i++) {  
	            //判断是否是当前页，若是，则默认为选中当前页  
	            if(i == curPage)  
	                str += "<option value='" + i + "' selected>第" + i + "页</option>";  
	            else  
	                str += "<option value='" + i + "'>第" + i + "页</option>";  
	        }  
	        str += "</select></span></form>";  
	        return str; 
	}  
}  
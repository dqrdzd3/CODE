package com.wg.salescount.util;

import java.io.File;  
import java.io.IOException;  
  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  


public class Excel2Oracle {

/*	public static void main(String[] args) throws Exception {    
	    
        Excel2Oracle in = new Excel2Oracle();    
        in.insert("D:/weekly_test.xls","WEEKLY_SALES_TEST");    
    
    }    */
    
	
    /**  
     *   
     * @param path  
     *            要解析的excel文件路径  
      * @param dataTable  
     *            要写入到数据库中的表名            
     * @throws BiffException  
     * @throws IOException  
     */    
    public static void insert(String path,String dataTable) throws BiffException, IOException {    
            
        File file = new File(path);             
        // 创建新的Excel 工作簿    
        Workbook rwb = null;    
        rwb = Workbook.getWorkbook(file);    
            
        // 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...    
        Sheet sheet = rwb.getSheets()[0];    
        int rsColumns = sheet.getColumns();// 列数    
        int rsRows = sheet.getRows();// 行数    
        String simNumber = "" ;//每个单元格中的数据    
            
        DBUtils jdbc=new DBUtils();    
            
        String str="";//拼接要插入的列    
            for (int j = 0; j <rsColumns; j++) {    
                Cell cell = sheet.getCell(j, 0);    
                //特殊字段处理
                String simNumberBefore = cell.getContents();
                if(simNumberBefore.equals("销售主键")){
                	simNumber = "SALES_ID";
                }else if(simNumberBefore.equals("客户名称")){
                	simNumber = "CUSTOMER_NAME";
                }else if(simNumberBefore.equals("收货人姓名")){
                	simNumber = "CONSIGNEE_NAME";
                }else if(simNumberBefore.equals("收货人地址")){
                	simNumber = "RECEIVER_ADDR";
                }else if(simNumberBefore.equals("联系电话")){
                	simNumber = "PHONE_NO";
                }else if(simNumberBefore.equals("发货时间")){
                	simNumber = "DELIVERY_TIME";
                }else if(simNumberBefore.equals("买家旺旺昵称")){
                	simNumber = "BUYERS_NICKNAME";
                }else if(simNumberBefore.equals("产品名称")){
                	simNumber = "PRODUCT_NAME";
                }else if(simNumberBefore.equals("产品编号")){
                	simNumber = "PRODUCT_ID";
                }else if(simNumberBefore.equals("型号")){
                	simNumber = "MODEL_TYPE";
                }else if(simNumberBefore.equals("颜色")){
                	simNumber = "COLOR";
                }else if(simNumberBefore.equals("单价")){
                	simNumber = "UNIT_PRICE";
                }else if(simNumberBefore.equals("数量")){
                	simNumber = "QUANTITY";
                }else if(simNumberBefore.equals("金额")){
                	simNumber = "TOTAL_PRICE";
                }else if(simNumberBefore.equals("到款情况")){
                	simNumber = "MONEY_STATUS";
                }else if(simNumberBefore.equals("是否开票")){
                	simNumber = "INVOICE";
                }else if(simNumberBefore.equals("发票号")){
                	simNumber = "INVOICE_NO";
                }else if(simNumberBefore.equals("销售平台")){
                	simNumber = "SALES_PLATFORM";
                }else if(simNumberBefore.equals("快递公司")){
                	simNumber = "COURIER_COMPANY";
                }else if(simNumberBefore.equals("快递单号")){
                	simNumber = "COURIER_NO";
                }else if(simNumberBefore.equals("签收时间")){
                	simNumber = "SIGN_TIME";
                }else if(simNumberBefore.equals("快递费")){
                	simNumber = "COURIER_COST";
                }else if(simNumberBefore.equals("备注")){
                	simNumber = "REMARK";
                }else{
                	simNumber = simNumberBefore;
                }
                //simNumber = cell.getContents();    
                if(j==rsColumns-1){    
                    str +=  simNumber  ;    
                }else{    
                    str +=  simNumber+",";    
                }    
                    
            }    
        for (int i = 1; i < rsRows; i++) {    
                
            String sql = "insert into "+dataTable+"("+str+") values(";//拼接sql    
            System.out.println(str);    
            for (int j = 0; j < rsColumns; j++) {    
                Cell cell = sheet.getCell(j, i);  
                //处理特殊字段
                String simNumberBefore = cell.getContents();
                if(simNumberBefore.equals("全部") || simNumberBefore.equals("空")){
                	simNumber = "0";
                }else if(simNumberBefore.equals("已支付") || simNumberBefore.equals("是") || simNumberBefore.equals("微店")){
                	simNumber = "1";
                }else if(simNumberBefore.equals("未支付") || simNumberBefore.equals("否") || simNumberBefore.equals("微信小店")){
                	simNumber = "2";
                }else if(simNumberBefore.equals("威果诚品")){
                	simNumber = "3";
                }else if(simNumberBefore.equals("企业淘宝")){
                	simNumber = "4";
                }else if(simNumberBefore.equals("线下")){
                	simNumber = "5";
                }else{
                	simNumber = simNumberBefore;
                } 
                if(simNumberBefore.contains("/")){
                	simNumber = "to_date('"+simNumberBefore+"'"+",'mm-dd-yyyy')";
                }
                if(j==rsColumns-1){ 
                	//最后一列
                	sql += "'"+ simNumber+"'" ;                    	                  
                }
                else{  
                	//日期sql不带''
                	if(simNumber.contains("to_date")){
                		sql += simNumber+",";
                	}else{
                    sql +="'"+ simNumber+"',";    
                	}
                }    
                    
            }    
           sql += " )";    
           System.out.println("excel sql="+sql);
            jdbc.executeUpdate(sql);//执行sql    
                
        }    
        jdbc.closeStmt();    
       jdbc.closeConnection();    
    }    
}

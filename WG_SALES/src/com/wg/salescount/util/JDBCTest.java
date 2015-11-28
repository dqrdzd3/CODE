package com.wg.salescount.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
	
	static Connection oracle_conn = null;  
	static PreparedStatement oracle_pstmt = null; 
	static ResultSet oracle_rs = null;  
	static String username = "scott";
	static String password = "tiger";
	
	public static void main(String[] args){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("start connect database!");
			oracle_conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.231:1521:HWAJ", username, password);
			String sql = "select * from SC_WEEKLY_SALES where PRODUCT_NAME=?";
			oracle_pstmt = oracle_conn.prepareStatement(sql);
			oracle_pstmt.setString(1, "空气电台");
			oracle_rs = oracle_pstmt.executeQuery();
		        while (oracle_rs.next())
		            System.out.println("" + oracle_rs.getString("WEEKLY_SALES_ID") + "product_name:"
		                    + oracle_rs.getString("PRODUCT_NAME"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(oracle_rs != null){
					oracle_rs.close();
				}
				if(oracle_pstmt != null){
					oracle_pstmt.close();
				}
				if(oracle_conn != null){
					oracle_conn.close();
				}
System.out.println("");				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

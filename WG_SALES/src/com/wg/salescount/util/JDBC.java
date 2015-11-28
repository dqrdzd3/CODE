package com.wg.salescount.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
/**
 * ������ݿ�
 * */
public class JDBC {
	private static String username;
	private static String password;
	private static String url;	   
	private static String driver; 
	private static Properties prop;
	private static DataSource ds; 
	
	static{
		prop=new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("DB.properties"));
			username=prop.getProperty("username");
			password=prop.getProperty("password");
			url=prop.getProperty("url");
			driver=prop.getProperty("driverClassName");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ������ݿ�
	 * */
	public static Connection getConn(){
		try {
		    ds=BasicDataSourceFactory.createDataSource(prop);
		    return  ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * �Ͽ����ӣ��ͷ���Դ
	 * */
	public  static  void  realse(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try{
		if(conn!=null)
			conn.close();
		if(pstmt!=null)
			pstmt.close();
		if(rs!=null)
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * �Ͽ����ӣ��ͷ���Դ
	 * */
	public  static  void  realse(Connection conn,PreparedStatement pstmt){
		try{
		if(conn!=null)
			conn.close();
		if(pstmt!=null)
			pstmt.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
	 * */
	public static void commit(Connection conn){
		if(conn!=null){
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * �ع�����
	 * */
	public static void rollback(Connection conn){
		if(conn!=null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void beginTx(Connection conn){

		if(conn!=null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String DateToString(Date d){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	     return	sdf.format(d);
	
	}

	public static void main(String[] args){
		Connection conn;
		conn = JDBC.getConn();
		System.out.println("success=="+conn.getClass().getName());
	}
	
	
	
	private Statement stmt = null;
	private Connection conn = null; 
	/**
	 * 测试excel用
	 * @author 王珂
	 * @param sqlStr
	 * @return
	 * @time 2015年8月10日 上午11:24:04
	 */
    public boolean executeUpdate(String sqlStr) {    
        
        if (sqlStr == null || sqlStr.length() == 0)    
            return false;    
        try {    
            this.getConn();    
            stmt.executeUpdate(sqlStr);    
            return true;    
        } catch (SQLException ex) {    
            ex.printStackTrace();    
            return false;    
        } finally {    
            try {    
                if (stmt != null) {    
                    stmt.close();    
                }    
            } catch (SQLException e) {    
                e.printStackTrace();    
            }    
            try {    
                if (conn != null) {    
                    conn.close();    
                }    
            } catch (SQLException e) {    
                e.printStackTrace();    
            }    
    
        }        
    }  
    
    public void closeStmt() {    
        try {    
            if (stmt != null) {    
                stmt.close();    
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }  
    
    public void closeConnection() {    
        try {    
            if (conn != null) {    
                /** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */    
                if (!conn.isClosed()) {    
                    conn.close();    
                }    
            }    
        } catch (Exception ex) {    
            ex.printStackTrace();    
        }    
    } 
}

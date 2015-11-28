package com.wg.salescount.util;

import java.sql.Connection;    
import java.sql.DriverManager;    
import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.sql.Statement;    
    
/**  
 * Oracle数据库连接  
 *   
 * @author  
 */    
public class DBUtils {    
    
    private Connection conn = null;    
    private Statement stmt = null;    
    private ResultSet rs = null;    
    
    /** Oracle数据库连接 URL */    
    private final static String DB_URL = "jdbc:oracle:thin:@192.168.111.231:1521:HWAJ";    
    
    /** Oracle数据库连接驱动 */    
    private final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";    
    
    /** 数据库用户名 */    
    private final static String DB_USERNAME = "scott";    
    
    /** 数据库密码 */    
    private final static String DB_PASSWORD = "tiger";    
    
    /**  
     * 获取数据库连接  
     *   
     * @return  
     */    
    public Connection getConnection() {    
        /** 声明Connection连接对象 */    
        Connection conn = null;    
        try {    
            /** 使用 Class.forName()方法自动创建这个驱动程序的实例且自动调用DriverManager来注册它 */    
            Class.forName(DB_DRIVER);    
            /** 通过 DriverManager的getConnection()方法获取数据库连接 */    
            conn = DriverManager    
                    .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);    
            stmt = conn.createStatement();    
        } catch (Exception ex) {    
            ex.printStackTrace();    
        }    
        return conn;    
    }    
    
    /**  
     * 查询数据部分  
     *   
     * @return ResultSet  
     */    
    public ResultSet executeQuery(String sqlStr) {    
        if (sqlStr == null || sqlStr.length() == 0)    
            return null;    
        try {    
            this.getConnection();    
            rs = stmt.executeQuery(sqlStr);    
            return rs;    
        } catch (SQLException ex) {    
            ex.printStackTrace();    
            return null;    
        }    
    
    }    
    
    /**  
     * 更新数据部分  
     *   
     * @return 更新是否成功  
     */    
    public boolean executeUpdate(String sqlStr) {    
    
        if (sqlStr == null || sqlStr.length() == 0)    
            return false;    
        try {    
            this.getConnection();    
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
    
    /**  
     * 关闭数据库连接  
     *   
     * @param connect  
     */    
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
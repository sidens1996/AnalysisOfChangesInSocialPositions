package com.analyse.analysejob.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	 // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/myjob2?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    private String USER;
    private String PASS;
    private Connection conn = null;
	public DAO(String USER,String PASS) {
	  this.USER=USER;
	  this.PASS=PASS;
	  System.out.println("连接数据库...");
	  try {
		// 注册 JDBC 驱动
	      Class.forName("com.mysql.jdbc.Driver");
		  conn = DriverManager.getConnection(DB_URL,USER,PASS);
	  }catch(SQLException e) {
		 System.out.println("connect database failed.");
		  e.printStackTrace();
		  if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  } catch (ClassNotFoundException e) {
		  e.printStackTrace();
	  }
	}
	 
	public ResultSet execute(String sql) {
		Statement stmt = null;
		ResultSet rs=null;
		try{
//	         System.out.println(" 实例化Statement对象...");
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql);
	         // 展开结果集数据库
	         /*
	         while(rs.next()){
	             // 通过字段检索
	             int id  = rs.getInt("id");
	             String name = rs.getString("name");
	             // 输出数据
	             System.out.print("ID: " + id);
	             System.out.print(", 站点名称: " + name);
	             System.out.print("\n");
	         }
	         */
		}catch(SQLException e ) {
       	 System.out.println("query failed.");
       	 e.printStackTrace();
		}finally {
			/*
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
		}
		return rs;
	}
}
     
     

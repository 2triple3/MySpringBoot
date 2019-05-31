package com.springboot.user.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MySQLConnectionOld {
	
	public static void usageOfPropertiesLoaderUtils() throws Exception {
		Properties prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
		String url = prop.getProperty("spring.datasource.url");
		String user = prop.getProperty("spring.datasource.username");
		String password = prop.getProperty("spring.datasource.password");
		System.out.println("url is==="+url);
	}
	
	public static void testMySQLConnectionOld() {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://192.168.56.100:3306/db_springboot";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "root";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            //String sql = "select * from tb_user";
            String sql = "insert into tb_user values('003','sdada')";
            //3.ResultSet类，用来存放获取的结果集！！
            //ResultSet rs = statement.executeQuery(sql);
            boolean rs = statement.execute(sql);
            
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
             
            String userid = null;
            String username = null;
//            while(rs.next()){
//                userid = rs.getString("userid");
//                username = rs.getString("username");
//                //输出结果
//                System.out.println(userid + "\t" + username);
//            }
//            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("finally！！");
        }
	}

	public static void main(String[] args) throws Exception {
		usageOfPropertiesLoaderUtils();
		//testMySQLConnectionOld();
	}
	
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ketnoidao {
	public Connection cn;
	public void ketnoi() throws Exception{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	
			cn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-GKL23FF\\SQLEXPRESS:1433;databaseName=BaiTap; user=sa; password=123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

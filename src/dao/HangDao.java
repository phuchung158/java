package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.HangBean;

public class HangDao {
	public ArrayList<HangBean> getnv()throws Exception{
		ArrayList<HangBean> ds = new ArrayList<HangBean>();
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "select * from hang";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			String mahang = rs.getString(1);
			String tenhang = rs.getString(2);
			Date ngaynhaphang = rs.getDate(3);
			int soluong = rs.getInt(4);
			double gia = rs.getDouble(5);
			HangBean a = new HangBean(mahang, tenhang, ngaynhaphang, soluong, gia);
			ds.add(a);
		}
		return ds;
	}
	public int Them(String mahang, String tenhang, Date ngaynhaphang, int soluong, double gia) throws Exception{
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "insert into Hang(mahang,tenhang,ngaynhaphang,soluong,gia) values(?,?,?,?,?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, mahang);
		cmd.setString(2,tenhang);
		cmd.setDate(3, new java.sql.Date(ngaynhaphang.getTime()));
		cmd.setInt(4,soluong);
		cmd.setDouble(5, gia);
		int a  = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return a;
	}
	public int XoaTheoMaHang(String mahang) throws Exception{
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "delete from hang where mahang=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, mahang);
		int kq = cmd.executeUpdate();
		return kq;
	}
	public int XoaTheoTenHang(String tenhang) throws Exception{
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "delete from hang where tenhang=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, tenhang);
		int a = cmd.executeUpdate();
		return a;
	}
	public int XoaTheoSoLuong(int soluong,String mahang) throws Exception{
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "Update hang set soluong = soluong - ? where mahang = ? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, soluong);
		cmd.setString(2, mahang);
		int kq = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return kq;
	}
	public int Sua(String mahang, String tenhang, Date ngaynhaphang, int soluong, double gia) throws Exception {
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "update hang set tenhang = ?, ngaynhaphang = ?, soluong = ? , gia = ? where mahang = ? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, tenhang);
		cmd.setDate(2, new java.sql.Date(ngaynhaphang.getTime()));
		cmd.setInt(3, soluong);
		cmd.setDouble(4, gia);
		cmd.setString(5, mahang);
		int kq = cmd.executeUpdate();
		kn.cn.close();
		cmd.close();
		return kq;
	}
	public ArrayList<HangBean> TimKiem(String tenhang) throws Exception{
		ArrayList<HangBean> ds = new ArrayList<HangBean>();
		ketnoidao kn = new ketnoidao();
		kn.ketnoi();
		String sql = "select * from hang where tenhang like ? ";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, "%"+tenhang+"%");
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			String mh = rs.getString(1);
			String th = rs.getString(2);
			Date ngaynhaphang = rs.getDate(3);
			int soluong = rs.getInt(4);
			Double gia = rs.getDouble(5);
			HangBean x = new HangBean(mh, th, ngaynhaphang, soluong, gia);
			ds.add(x);
		}
		return ds;
	}
}

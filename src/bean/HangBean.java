package bean;

import java.util.Date;

public class HangBean {
	String mahang;
	String tenhang;
	Date ngaynhaphang;
	int soluong;
	double gia;
	
	public HangBean(String mahang, String tenhang, Date ngaynhaphang, int soluong, double gia) {
		super();
		this.mahang = mahang;
		this.tenhang = tenhang;
		this.ngaynhaphang = ngaynhaphang;
		this.soluong = soluong;
		this.gia = gia;
	}
	
	public HangBean() {
		super();
	}

	public String getMahang() {
		return mahang;
	}
	public void setMahang(String mahang) {
		this.mahang = mahang;
	}
	public String getTenhang() {
		return tenhang;
	}
	public void setTenhang(String tenhang) {
		this.tenhang = tenhang;
	}
	public Date getNgaynhaphang() {
		return ngaynhaphang;
	}
	public void setNgaynhaphang(Date ngaynhaphang) {
		this.ngaynhaphang = ngaynhaphang;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	@Override
	public String toString() {
		return mahang + ";"+ tenhang + ";"+ ngaynhaphang + ";"+ soluong + ";"+ gia;
	}
	
}


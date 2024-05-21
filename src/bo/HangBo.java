package bo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.HangBean;
import dao.HangDao;

public class HangBo {
	private boolean ktra = false;
	HangDao kn = new HangDao();
	ArrayList<HangBean> ds = new ArrayList<HangBean>();
	public ArrayList<HangBean> getnv() throws Exception {
		ds = kn.getnv();
		return ds;
	}
	public int Them(String mahang, String tenhang, Date ngaynhaphang, int soluong, double gia) throws Exception {
		if(!Kiemtrarong()) {
			for(HangBean nv: ds)
				if(nv.getMahang().equals(mahang)) return 0;
		}
		return kn.Them(mahang, tenhang, ngaynhaphang, soluong, gia);
	}
	public int XoaTheoMaHang(String mahang) throws Exception{
		return kn.XoaTheoMaHang(mahang);
	}
	public int XoaTheoTenHang(String tenhang) throws Exception{
		return kn.XoaTheoTenHang(tenhang);
	}
	public int Sua(String mahang, String tenhang, Date ngaynhaphang, int soluong, double gia) throws Exception{
		return kn.Sua(mahang, tenhang, ngaynhaphang, soluong, gia);
	}
	public ArrayList<HangBean> Timkiem(String tenhang) throws Exception{
		return kn.TimKiem(tenhang);
	}
	public int XoaTheoSoLuong(int soluong, String mahang) throws Exception{
		return kn.XoaTheoSoLuong(soluong, mahang);
	}
	public void Luu(String st) throws Exception{
		if(!Kiemtrarong()) {
			FileReader f = new FileReader(st);
			BufferedReader r = new BufferedReader(f);
			while(true) {
				String st1 = r.readLine();
				if(st1==null || st1=="") break;
				String[] tt = st1.split("[;]");
				String mahang = tt[0];
				String tenhang = tt[1];
				SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
				Date ngaynhaphang = d.parse(tt[2]);
				int soluong = Integer.parseInt(tt[3]);
				Double gia = Double.parseDouble(tt[4]);
				Them(mahang, tenhang, ngaynhaphang, soluong, gia);
			}
			r.close();
		}
	}
	public boolean Kiemtrarong() throws Exception{
		ArrayList<HangBean> ds = getnv();
		return !ds.isEmpty();
	}
}


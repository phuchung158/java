package view;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bo.HangBo;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MenuView extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;

	/**
	 * Launch the application.
	 */
	private double sdtk = 1000000;
	ArrayList<HangBean> ds = new ArrayList<HangBean>();
	ArrayList<HangBean> lsdd = new ArrayList<HangBean>();
	private JTable table;
	void NapBang(ArrayList<HangBean> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		mh.addColumn("Mã Hàng");
		mh.addColumn("Tên Hàng");
		mh.addColumn("Ngày Nhập Hàng");
		mh.addColumn("Số Lượng");
		mh.addColumn("Giá");
		for(HangBean a:ds) {
			String mahang = a.getMahang();
			String tenhang = a.getTenhang();
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			String ngaynhaphang = d.format(a.getNgaynhaphang());
			String soluong = Integer.toString(a.getSoluong());
			String gia = Double.toString(a.getGia());
			String tt = mahang + ";" + tenhang + ";" + ngaynhaphang + ";" + soluong + ";" + gia;
			String[] t = tt.split("[;]");
			mh.addRow(t);
		}
		table.setModel(mh);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					HangBo a= new HangBo();
					ds = a.getnv();
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 445);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("QTV");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Hệ Thống");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pass = JOptionPane.showInputDialog("Vui Lòng Nhập Mật Khẩu");
					if(pass == null ) {
						
					}
					if(pass.equals("123")) {
						HangView a = new HangView();
						a.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Đăng Nhập Thắt Bại, Vui Lòng Nhập Lại Mật Khẩu!");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Số Dư TK");
		lblNewLabel.setBounds(10, 11, 65, 20);
		contentPane.add(lblNewLabel);
		
		tf1 = new JTextField();
		tf1.setBounds(67, 11, 357, 20);
		contentPane.add(tf1);
		tf1.setColumns(10);
		tf1.setText(Double.toString(sdtk));
		
		JButton btnNewButton = new JButton("Mua");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mh = JOptionPane.showInputDialog("Nhập Mã Hàng");
				String sl = JOptionPane.showInputDialog("Nhập Số Lượng Muốn Mua");
				HangBean x = new HangBean();
				boolean ktra = false;
				for(HangBean a : ds) {
					if(mh.equals(a.getMahang())) {
						x.setMahang(a.getMahang());
						x.setTenhang(a.getTenhang());
						x.setNgaynhaphang(a.getNgaynhaphang());
						x.setSoluong(a.getSoluong());
						x.setGia(a.getGia());
						ktra = true;
						
					}
				}
				if(ktra){
					int sl1 = Integer.parseInt(sl);
					if(sl1 >= 0 && sl1 <= x.getSoluong()){
						Double tien = Double.parseDouble(tf1.getText());
						Double tt = sl1 * x.getGia();
						Double Tong = tien - tt;
						HangBo d = new HangBo();
						if(x.getSoluong() - sl1 > 0) {
							try {
								d.XoaTheoSoLuong(sl1, x.getMahang());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							try {
								d.XoaTheoMaHang(x.getMahang());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						if(Tong >= 0) {
							JOptionPane.showMessageDialog(null, "Mua Hàng Thành Công, Tuyệt Zời Quá !!!!!!");
							tf1.setText(Double.toString(Tong));
							x.setSoluong(sl1);
							lsdd.add(x);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Thiếu Tiền Mất Rồi :((");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Mua Nhiều Quá Rồi Bạn Ơi Mua Bớt Lại Điiiii");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Không Có Mã Hàng Muốn Mua!");
				}
			}
			});
		btnNewButton.setBounds(10, 97, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Bán");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(303, 97, 106, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Lịch Sử Giao Dịch");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable tb = new JTable();
				DefaultTableModel x = new DefaultTableModel();
				x.addColumn("Mã hàng");
				x.addColumn("Tên Hàng");
				x.addColumn("Ngày Nhập Hàng");
				x.addColumn("Số Lượng");
				x.addColumn("Giá");
				x.addColumn("Tổng");
				tb.setModel(x);
				for(HangBean a : lsdd) {
					String mh = a.getMahang();
					String th = a.getTenhang();
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					String nnh = d.format(a.getNgaynhaphang());
					String sl = Integer.toString(a.getSoluong());
					String gia = Double.toString(a.getGia());
					String Tong = Double.toString(a.getSoluong() * a.getGia());
					String tt  = mh + ";" + th + ";" + nnh + ";" + sl + ";" + gia + ";" + Tong;
					String[] t = tt.split("[;]");
					x.addRow(t);
				}
				JScrollPane test = new JScrollPane(tb);
				JOptionPane.showMessageDialog(null, test,"Lịch Sử Mua Hàng",JOptionPane.DEFAULT_OPTION);
				
			}
		});
		btnNewButton_2.setBounds(67, 131, 321, 28);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cập Nhật");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangBo a = new HangBo();
				try {
					ds = a.getnv();
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(157, 97, 106, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Thêm Tiền");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double a = Double.parseDouble(tf1.getText());
				Double T = a + 1000000;
				tf1.setText(T.toString());
				JOptionPane.showMessageDialog(null,"Thần Tài Đến, thần Tài Đến!");
			}
		});
		btnNewButton_4.setBounds(157, 42, 106, 28);
		contentPane.add(btnNewButton_4);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 193, 414, 180);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("DSMH", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}

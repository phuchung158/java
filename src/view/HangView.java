package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bo.HangBo;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HangView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	ArrayList<HangBean> ds = new ArrayList<HangBean>();
	private JTable table;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
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
					HangView frame = new HangView();
					frame.setVisible(true);
					frame.setTitle("Giao Diện Bán Hàng");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					HangBo a = new HangBo();
					a.Luu("hang.txt");
					ds = a.getnv();
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 205, 414, 177);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("DSMH", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
				String mahang = table.getValueAt(a,0).toString();
				tf1.setText(mahang);
				String tenhang = table.getValueAt(a,1).toString();
				tf2.setText(tenhang);
				String ngaynhaphang = table.getValueAt(a,2).toString();
				tf3.setText(ngaynhaphang);
				String soluong = table.getValueAt(a, 3).toString();
				tf4.setText(soluong);
				String gia = table.getValueAt(a, 4).toString();
				tf5.setText(gia);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String mahang = tf1.getText();
					String tenhang = tf2.getText();
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					Date ngaynhaphang = d.parse(tf3.getText());
					int soluong = Integer.parseInt(tf4.getText());
					Double gia = Double.parseDouble(tf5.getText());
					HangBo a = new HangBo();
					int rs = a.Them(mahang, tenhang, ngaynhaphang, soluong, gia);
					if(rs !=0) {
						ds = a.getnv();
						NapBang(ds);
					}
				} catch (Exception e2) {
//					e2.printStackTrace();
					JOptionPane.showMessageDialog(null,"Bạn Đã Nhập Sai Thông Tin Vui Lòng Kiểm Tra Lại!");
				}
			}
		});
		btnNewButton.setBounds(10, 163, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HangBo a = new HangBo();
					String mahang = tf1.getText();
					String tenhang = tf2.getText();
					SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
					Date ngaynhaphang = d.parse(tf3.getText());
					int soluong = Integer.parseInt(tf4.getText());
					Double gia = Double.parseDouble(tf5.getText());
					a.Sua(mahang, tenhang, ngaynhaphang, soluong, gia);
					ds = a.getnv();
					JOptionPane.showMessageDialog(null,"Sửa Thành Công");
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(114, 163, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangBo x = new HangBo();
				int a = JOptionPane.showConfirmDialog(null,"bạn muốn xóa mặt hàng?");
				if(a==0) {
					int b = JOptionPane.showOptionDialog(null, "Chọn Cách Xóa","Xóa Hàng",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new String[]{"Xóa Theo Mã Hàng", "Xóa Theo Tên Hàng"}, "Xóa Theo Mã Hàng");
					try {
						if(b==0) {
							String mahang = JOptionPane.showInputDialog("Nhập Mã Hàng");
							int x1 = x.XoaTheoMaHang(mahang);
							if(x1 == 0) {
								JOptionPane.showMessageDialog(null, "Mã Hàng Không Tồn Tại!");
							}else {
								JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
							}
						}else {
							String tenhang = JOptionPane.showInputDialog("Nhập Tên Hàng");
							int x1 = x.XoaTheoTenHang(tenhang);
							if(x1==0) {
								JOptionPane.showMessageDialog(null, "Tên Hàng Không Tồn Tại!");
							}else {
								JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
							}
						}
						ds = x.getnv();
						NapBang(ds);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btnNewButton_2.setBounds(218, 163, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Tìm kiếm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HangBo x = new HangBo();
					String tenhang = JOptionPane.showInputDialog("Nhập tên hàng cần tìm kiếm!");
					ArrayList<HangBean> ds = x.Timkiem(tenhang);
					// tạo bảng
					JTable rs = new JTable();
					DefaultTableModel mh = new DefaultTableModel();
					 mh.addColumn("Mã Hàng");
			         mh.addColumn("Tên Hàng");
			         mh.addColumn("Ngày Nhập Hàng");
			         mh.addColumn("Số Lượng");
			         mh.addColumn("Giá");
			         // đưa dữ liệu vào bảng
			         for(HangBean a :ds) {
			        	String mahang = a.getMahang();
			 			String th = a.getTenhang();
			 			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			 			String ngaynhaphang = d.format(a.getNgaynhaphang());
			 			String soluong = Integer.toString(a.getSoluong());
			 			String gia = Double.toString(a.getGia());
			 			String tt = mahang + ";" + tenhang + ";" + ngaynhaphang + ";" + soluong + ";" + gia;
			 			String[] t = tt.split("[;]");
			 			mh.addRow(t);
			         }
			         rs.setModel(mh);
			         JScrollPane test = new JScrollPane(rs);
			         JOptionPane.showConfirmDialog(null, test, "Kết Quả Tìm Kiếm", JOptionPane.DEFAULT_OPTION);
			         
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(317, 163, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Mã Hàng");
		lblNewLabel.setBounds(10, 11, 73, 17);
		contentPane.add(lblNewLabel);
		
		tf1 = new JTextField();
		tf1.setBounds(113, 9, 246, 20);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tền Hàng");
		lblNewLabel_1.setBounds(10, 39, 73, 14);
		contentPane.add(lblNewLabel_1);
		
		tf2 = new JTextField();
		tf2.setBounds(114, 36, 246, 20);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày Nhập Hàng");
		lblNewLabel_2.setBounds(10, 64, 96, 22);
		contentPane.add(lblNewLabel_2);
		
		tf3 = new JTextField();
		tf3.setBounds(113, 64, 246, 22);
		contentPane.add(tf3);
		tf3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Số Lượng");
		lblNewLabel_3.setBounds(10, 96, 73, 17);
		contentPane.add(lblNewLabel_3);
		
		tf4 = new JTextField();
		tf4.setBounds(114, 94, 245, 20);
		contentPane.add(tf4);
		tf4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Giá");
		lblNewLabel_4.setBounds(10, 124, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		tf5 = new JTextField();
		tf5.setBounds(114, 121, 245, 20);
		contentPane.add(tf5);
		tf5.setColumns(10);
	}
}

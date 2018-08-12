package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StudentInformationPanel extends JPanel {
	public static JTextField txtMath, txtPhysical, txtChemistry, txtTotal, txtId, txtName, txtBirthPlace, txtDate;
	public static JRadioButton rdMale, rdFemale;
	public static JButton btnInsert, btnDelete, btnEdit, btnOk, btnCancer;

	public StudentInformationPanel() {
		setPanelStudentInformation();
	}

	private void setPanelStudentInformation() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Border bdTittle = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titledBorder = new TitledBorder("Thông tin thí sinh");
		titledBorder.setTitleColor(new Color(255, 90, 0));
		this.setBorder(titledBorder);
		this.add(new CenterPanel());
		this.add(new ButtonPanel());
	}

	class CenterPanel extends JPanel {
		public CenterPanel() {
			initComponents();
		}

		private void initComponents() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(BorderFactory.createLineBorder(new Color(180, 13, 47)));
			add(new CenterAbovepanel());
			add(new SexPanel());
		}
	}

	class CenterAbovepanel extends JPanel {
		public CenterAbovepanel() {
			initComponents();
		}

		private void initComponents() {
			setLayout(new FlowLayout(FlowLayout.CENTER, 80, 10));
			add(new CenterAboveLeft());
			add(new CenterAboveRight());
		}

	}

	class CenterAboveRight extends JPanel {
		public CenterAboveRight() {
			initComponents();
		}

		private void initComponents() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel lblMath = new JLabel("Toán");
			JLabel lblPhysical = new JLabel("Vật lý");
			JLabel lblChemistry = new JLabel("Hóa học");
			JLabel lblTotal = new JLabel("Tổng điểm");

			lblMath.setPreferredSize(new Dimension(100, 20));
			lblPhysical.setPreferredSize(lblMath.getPreferredSize());
			lblChemistry.setPreferredSize(lblMath.getPreferredSize());
			lblTotal.setPreferredSize(lblMath.getPreferredSize());

			txtMath = new JTextField(20);
			txtPhysical = new JTextField(20);
			txtChemistry = new JTextField(20);
			txtTotal = new JTextField(20);

			JPanel pnMath = new JPanel();
			pnMath.add(lblMath);
			pnMath.add(txtMath);

			JPanel pnPhysical = new JPanel();
			pnPhysical.add(lblPhysical);
			pnPhysical.add(txtPhysical);

			JPanel pnChemistry = new JPanel();
			pnChemistry.add(lblChemistry);
			pnChemistry.add(txtChemistry);

			JPanel pnTotal = new JPanel();
			pnTotal.add(lblTotal);
			pnTotal.add(txtTotal);

			add(pnMath);
			add(pnPhysical);
			add(pnChemistry);
			add(pnTotal);
		}
	}

	class CenterAboveLeft extends JPanel {
		public CenterAboveLeft() {
			initComponents();
		}

		private void initComponents() {

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel lblId = new JLabel("Mã thí sinh");
			JLabel lblName = new JLabel("Họ và tên");
			JLabel lblBirthPlace = new JLabel("Nơi sinh");
			JLabel lblDate = new JLabel("Ngày sinh");

			lblId.setPreferredSize(new Dimension(100, 20));
			lblName.setPreferredSize(lblId.getPreferredSize());
			lblBirthPlace.setPreferredSize(lblId.getPreferredSize());
			lblDate.setPreferredSize(lblId.getPreferredSize());

			txtId = new JTextField(20);
			txtName = new JTextField(20);
			txtBirthPlace = new JTextField(20);
			txtDate = new JTextField(20);

			JPanel pnID = new JPanel();
			pnID.add(lblId);
			pnID.add(txtId);

			JPanel pnName = new JPanel();
			pnName.add(lblName);
			pnName.add(txtName);

			JPanel pnBirth = new JPanel();
			pnBirth.add(lblBirthPlace);
			pnBirth.add(txtBirthPlace);

			JPanel pnDate = new JPanel();
			pnDate.add(lblDate);
			pnDate.add(txtDate);

			add(pnID);
			add(pnName);
			add(pnBirth);
			add(pnDate);

		}
	}

	class SexPanel extends JPanel {
		public SexPanel() {
			initComponents();
		}

		private void initComponents() {

			JLabel lblSex = new JLabel("Giới tính");
			lblSex.setPreferredSize(new Dimension(100, 20));
			rdMale = new JRadioButton("Nam");
			rdFemale = new JRadioButton("Nữ");

			ButtonGroup group = new ButtonGroup();
			group.add(rdMale);
			group.add(rdFemale);
			add(lblSex);
			add(rdMale);
			add(rdFemale);
		}
	}

	class ButtonPanel extends JPanel {
		public ButtonPanel() {
			initComponents();
		}

		private void initComponents() {
			setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
			setBackground(new Color(132, 135, 254));

			btnInsert = new JButton("Thêm", new ImageIcon("Data\\Image\\Add.png"));
			btnDelete = new JButton("Xóa", new ImageIcon("Data\\Image\\Delete.png"));
			btnEdit = new JButton("Sửa", new ImageIcon("Data\\Image\\Edit.png"));
			btnOk = new JButton("Xong", new ImageIcon("Data\\Image\\Ok.png"));
			btnCancer = new JButton("Hủy bỏ", new ImageIcon("Data\\Image\\Cancer.png"));

			btnInsert.setPreferredSize(btnCancer.getPreferredSize());
			btnEdit.setPreferredSize(btnCancer.getPreferredSize());
			btnOk.setPreferredSize(btnCancer.getPreferredSize());
			btnDelete.setPreferredSize(btnCancer.getPreferredSize());

			add(btnInsert);
			add(btnEdit);
			add(btnOk);
			add(btnDelete);
			add(btnCancer);
		}
	}

	public static boolean isMale() {
		if (rdMale.isSelected())
			return true;
		return false;
	}

}

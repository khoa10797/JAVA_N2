package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class StudentFilterPanel extends JPanel {

	private Vector<String> vector;
	public static JButton btnSearch;
	public static JTextField txtStudentID;
	public static JComboBox cbBirthPlace;

	public StudentFilterPanel(Vector<String> vector) {
		this.vector = vector;
		setPanelStudentFilter();
	}

	private void setPanelStudentFilter() {

		setLayout(new FlowLayout(FlowLayout.CENTER, 80, 0));
		setPreferredSize(new Dimension(0, 60));
		Border bdTittle = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titledBorder = new TitledBorder("Lọc và tìm kiếm thí sinh");
		titledBorder.setTitleColor(new Color(255, 90, 0));
		setBorder(titledBorder);

		btnSearch = new JButton("Tìm", new ImageIcon("Data\\Image\\Search.png"));
		btnSearch.setPreferredSize(new Dimension(80, 25));

		add(new PlacePanel());
		add(new StudentPanel());
		add(btnSearch);
	}
	
	class StudentPanel extends JPanel{
		public StudentPanel() {
			initComponets();
		}

		private void initComponets() {
			JLabel lblStudentID = new JLabel("Mã thí sinh");
			txtStudentID = new JTextField(15);
			add(lblStudentID);
			add(txtStudentID);
		}
	}

	class PlacePanel extends JPanel{
		public PlacePanel() {
			initComponets();
		}

		private void initComponets() {
			JLabel lblBirthPlace = new JLabel("Quê quán");
			cbBirthPlace = new JComboBox<>(vector);
			cbBirthPlace.setPreferredSize(new Dimension(180, 20));
			add(lblBirthPlace);
			add(cbBirthPlace);
		}
	}

}

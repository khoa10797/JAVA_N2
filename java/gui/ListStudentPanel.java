package gui;

import model.Student;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ListStudentPanel extends JPanel {

	private Map<Integer, String> map;
	public JTable tbListStudent;
	public StudentTableModel stTableModel;

	public ListStudentPanel(Map<Integer, String> map, List<Student> list) throws IOException {
		this.map = map;
		setPanelListStudent(list);
		clickMouse();
	}

	private void setPanelListStudent(List<Student> list) throws IOException {

		setLayout(new BorderLayout());
		Border bdTittle = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titledBorder = new TitledBorder("Danh sách thí sinh");
		titledBorder.setTitleColor(new Color(255, 90, 0));
		setBorder(titledBorder);
		setPreferredSize(new Dimension(0, 150));

		stTableModel = new StudentTableModel(list, map);
		tbListStudent = new JTable(stTableModel);
		tbListStudent.getColumnModel().getColumn(0).setMaxWidth(50);
		tbListStudent.getColumnModel().getColumn(Student.ID).setMinWidth(50);
		tbListStudent.getColumnModel().getColumn(Student.Name).setMinWidth(200);
		tbListStudent.getColumnModel().getColumn(Student.Place).setMinWidth(100);
		tbListStudent.getColumnModel().getColumn(Student.Date).setMinWidth(100);
		tbListStudent.getColumnModel().getColumn(Student.Sex).setMaxWidth(100);
		tbListStudent.getColumnModel().getColumn(Student.Math).setMinWidth(50);
		tbListStudent.getColumnModel().getColumn(Student.Physical).setMinWidth(50);
		tbListStudent.getColumnModel().getColumn(Student.Chemistry).setMinWidth(50);

		JScrollPane scp = new JScrollPane(tbListStudent);
		add(scp, BorderLayout.CENTER);
	}

	public void update() {
		tbListStudent.revalidate();
		tbListStudent.repaint();
	}

	public int getSelectedRow() {
		return tbListStudent.convertRowIndexToModel(tbListStudent.getSelectedRow());
	}

	private void clickMouse() {
		tbListStudent.getSelectionModel().addListSelectionListener(e -> {
			int selectedRow = getSelectedRow();
			if (selectedRow < 0)
				return;

			String id = String.valueOf(stTableModel.getValueAt(selectedRow, Student.ID));
			String name = String.valueOf(stTableModel.getValueAt(selectedRow, Student.Name));
			String place = (String) stTableModel.getValueAt(selectedRow, Student.Place);
			String date = (String) stTableModel.getValueAt(selectedRow, Student.Date);
			String math = String.valueOf(stTableModel.getValueAt(selectedRow, Student.Math));
			String physical = String.valueOf(stTableModel.getValueAt(selectedRow, Student.Physical));
			String chemistry = String.valueOf(stTableModel.getValueAt(selectedRow, Student.Chemistry));

			double total = Double.parseDouble(math) + Double.parseDouble(physical) + Double.parseDouble(chemistry);

			StudentInformationPanel.txtId.setText(id);
			StudentInformationPanel.txtName.setText(name);
			StudentInformationPanel.txtBirthPlace.setText(place);
			StudentInformationPanel.txtDate.setText(date);
			StudentInformationPanel.txtMath.setText(math);
			StudentInformationPanel.txtPhysical.setText(physical);
			StudentInformationPanel.txtChemistry.setText(chemistry);

			StudentInformationPanel.txtTotal.setText(String.valueOf(total));
			String sex = (String) stTableModel.getValueAt(selectedRow, Student.Sex);
			if ("Nam".equals(sex))
				StudentInformationPanel.rdMale.setSelected(true);
			else
				StudentInformationPanel.rdFemale.setSelected(true);
		});
	}

}

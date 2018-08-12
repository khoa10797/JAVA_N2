package gui;

import model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class StudentTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] header = { "STT", "Mã thí sinh ", "Họ và tên", "Nơi sinh", "Ngày sinh", "Giới tính", "Toán",
			"Vật lý", "Hóa học" };
	private List<Student> listStudent;
	private Map<Integer, String> map;

	public StudentTableModel(List<Student> listStudents, Map<Integer, String> map) {
		this.listStudent = listStudents;
		this.map = map;
	}

	@Override
	public String getColumnName(int column) {
		if (column < header.length)
			return header[column];
		return "";
	}

	@Override
	public int getRowCount() {
		return listStudent.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex >= getRowCount() || columnIndex > getColumnCount())
			return "";
		Student stRow = listStudent.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex;
		case Student.ID:
			return stRow.getId();
		case Student.Name:
			return stRow.getName();
		case Student.Place:
			return map.get(stRow.getIdPlace());
		case Student.Date:
			return stRow.getDate();
		case Student.Sex:
			return stRow.getSexName();
		case Student.Math:
			return stRow.getMath();
		case Student.Physical:
			return stRow.getPhysical();
		case Student.Chemistry:
			return stRow.getChemistry();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}

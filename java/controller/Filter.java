package controller;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import gui.ListStudentPanel;
import gui.StudentFilterPanel;
import gui.StudentTableModel;

public class Filter {

	TableRowSorter<StudentTableModel> sorter;

	public void addfilter(ListStudentPanel listStudentPanel, String query, int index) {
		sorter = new TableRowSorter<StudentTableModel>(listStudentPanel.stTableModel);
		listStudentPanel.tbListStudent.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(query, index));
	}
}

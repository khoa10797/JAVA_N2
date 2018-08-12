package controller;

import controller.EventController;
import gui.ListStudentPanel;
import gui.MenuBar;
import gui.StudentFilterPanel;
import gui.StudentInformationPanel;
import input.Provider;
import input.SQLProvider;
import input.StudentIdException;
import input.TextProvider;
import input.XMLProvider;
import model.Student;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Main extends JFrame implements WindowListener {

	private List<Student> list;
	private Map<Integer, String> map;
	private StudentFilterPanel filterPanel;
	private ListStudentPanel listStudentPanel;
	private StudentInformationPanel informationPanel;
	private EventController eventController;

	public Main() {
		super("Quản lý thí sinh");
		addControlls();
		addEvents();
	}

	private void addControlls() {
		this.setJMenuBar(new gui.MenuBar());
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));

		Object[] options = { "Text", "XML", "SQL" };
		int choice = JOptionPane.showOptionDialog(pnMain, "Bạn muốn sử dụng dữ liệu từ đâu?", "Quản lý thí sinh",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

		loadInformation(getProvider(choice));
		pnMain.add(filterPanel);
		pnMain.add(listStudentPanel);
		pnMain.add(informationPanel);
		con.add(pnMain);
	}

	private void addEvents() {

		MenuBar.open.addActionListener(e -> eventController.eventOpen(this));

		StudentFilterPanel.btnSearch.addActionListener(e -> eventController.eventBtnSearch());

		StudentFilterPanel.cbBirthPlace.addItemListener(e -> {
			try {
				eventController.eventComboBox();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});

		StudentInformationPanel.btnInsert.addActionListener(e -> {
			try {
				eventController.eventBtnInsert();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại thông tin!");
				e1.printStackTrace();
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại ngày sinh!\nĐịnh dạng : dd/mm/yyy");
				e1.printStackTrace();
			} catch (StudentIdException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin! ");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Thêm thí sinh thất bại!\nLỗi " + e2.getMessage());
			}
		});

		StudentInformationPanel.btnEdit.addActionListener(e -> {
			try {
				eventController.eventBtnEdit();
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Kiểm tra lại ngày sinh!\nĐịnh dạng : dd/mm/yyy");
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại thông tin!");
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin! ");
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(null, "Thêm thí sinh thất bại!\nLỗi" + e3.getMessage());
			}
		});

		StudentInformationPanel.btnDelete.addActionListener(e -> {
			try {
				eventController.eventBtnDelete();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Xóa thí sinh thất bại!");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		StudentInformationPanel.btnOk.addActionListener(e -> {
			try {
				eventController.eventBtnOk();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});

		StudentInformationPanel.btnCancer.addActionListener(e -> eventController.eventBtnCancer());

		this.addWindowListener(this);

	}

	public void showWindow() {
		this.setSize(900, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			eventController.eventClose(list);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	private void loadInformation(Provider provider) {
		try {
			list = provider.loadStudent();
			map = provider.loadPlace();
			listStudentPanel = new ListStudentPanel(map, list);
			eventController = new EventController(provider, list, listStudentPanel);
			filterPanel = new StudentFilterPanel(provider.loadPlaceName());
			informationPanel = new StudentInformationPanel();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private Provider getProvider(int choice) {
		if (choice == 0)
			return new TextProvider();
		else if (choice == 1)
			return new XMLProvider();
		return new SQLProvider();

	}

	@Override
	public void dispose() {
		super.dispose();
		try {
			eventController.eventClose(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

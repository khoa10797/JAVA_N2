package gui;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	public static JMenuBar menubar;
	public static JMenu mnuFile, mnuAbout;
	public static JMenuItem open, exit, aboutMe;

	public MenuBar() {
		setMenuBar();
	}

	private void setMenuBar() {

		setBackground(new Color(190, 190, 255));

		mnuFile = new JMenu("Hệ thống");
		open = new JMenuItem("Mở...");
		exit = new JMenuItem("Đóng");

		mnuFile.add(open);
		mnuFile.addSeparator();
		mnuFile.add(exit);

		mnuAbout = new JMenu("Thông tin");
		aboutMe = new JMenuItem("Thông tin ứng dụng");
		mnuAbout.add(aboutMe);

		add(mnuFile);
		add(mnuAbout);
	}
}

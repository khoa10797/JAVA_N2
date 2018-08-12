package controller;

import gui.ListStudentPanel;
import gui.StudentFilterPanel;
import gui.StudentInformationPanel;
import input.Provider;
import input.StudentIdException;
import model.Student;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class EventController {

    private Provider provider;
    private List<Student> list;
    private ListStudentPanel listStudentPanel;
    private Filter filter = new Filter();

    public EventController(Provider provider, List<Student> list, ListStudentPanel listStudentPanel) {
        this.provider = provider;
        this.list = list;
        this.listStudentPanel = listStudentPanel;
    }

    public void eventOpen(Main main) {
        main.setVisible(false);
        main.dispose();
        main = new Main();
        main.showWindow();
    }

    public void eventBtnInsert() throws ParserConfigurationException, SAXException, IOException, SQLException,
            ParseException, StudentIdException, ClassNotFoundException {
        Student student = getStudent();
        provider.insertData(list, student);
        listStudentPanel.update();
        JOptionPane.showMessageDialog(null, "Thêm thí sinh thành công!");
        eventBtnCancer();
    }

    public void eventBtnEdit()
            throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, ClassNotFoundException {
        int id = list.get(listStudentPanel.getSelectedRow()).getId();
        if (Integer.parseInt(StudentInformationPanel.txtId.getText()) != id) {
            JOptionPane.showMessageDialog(null, "Không được đổi mã thí sinh!");
            return;
        }
        list.remove(listStudentPanel.getSelectedRow());
        Student student = getStudent();
        provider.updateData(list, student);
        listStudentPanel.update();
        JOptionPane.showMessageDialog(null, "Sửa thông tin thành công!");
        eventBtnCancer();
    }

    public void eventBtnCancer() {
        StudentInformationPanel.txtId.setText(null);
        StudentInformationPanel.txtName.setText(null);
        StudentInformationPanel.txtBirthPlace.setText(null);
        StudentInformationPanel.txtDate.setText(null);
        StudentInformationPanel.txtMath.setText(null);
        StudentInformationPanel.txtPhysical.setText(null);
        StudentInformationPanel.txtChemistry.setText(null);
        StudentInformationPanel.txtTotal.setText(null);
    }

    public void eventBtnDelete() throws SQLException, ClassNotFoundException {
        int index = listStudentPanel.getSelectedRow();
        int id = list.get(index).getId();
        if (JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa?", "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION) == 0) {
            provider.deleteData(list, index, id);
            eventBtnOk();
            JOptionPane.showMessageDialog(null, "Xóa thí sinh thành công!");
            eventBtnCancer();
        } else
            return;
    }

    public void eventBtnSearch() {
        filter.addfilter(listStudentPanel, StudentFilterPanel.txtStudentID.getText(), Student.ID);
    }

    public void eventComboBox() {
        StudentFilterPanel.txtStudentID.setText(null);
        filter.addfilter(listStudentPanel, (String) StudentFilterPanel.cbBirthPlace.getSelectedItem(), Student.Place);
    }

    public void eventBtnOk() {
        StudentFilterPanel.txtStudentID.setText(null);
        StudentFilterPanel.cbBirthPlace.setSelectedIndex(0);
        filter.addfilter(listStudentPanel, "", Student.ID);
    }

    public void eventClose(List<Student> list)
            throws FileNotFoundException, ParserConfigurationException, TransformerException, UnsupportedEncodingException {
        provider.close(list);
    }

    private Student getStudent() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
        int placeId = getPlaceId();
        double math = Double.parseDouble(StudentInformationPanel.txtMath.getText());
        double physical = Double.parseDouble(StudentInformationPanel.txtPhysical.getText());
        double chemistry = Double.parseDouble(StudentInformationPanel.txtChemistry.getText());
        if (!isPoint(math, physical, chemistry)) {
            JOptionPane.showMessageDialog(null, "Điểm phải nằm trong khoảng 0 -> 9");
            return null;
        }

        Student student = new Student(Integer.parseInt(StudentInformationPanel.txtId.getText()),
                StudentInformationPanel.txtName.getText(), placeId, StudentInformationPanel.txtDate.getText(),
                StudentInformationPanel.isMale(), math, physical, chemistry);
        return student;
    }

    private Integer getPlaceId() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
        int placeId = convertPlaceNameToId(provider.loadPlace());
        if (placeId == 0) {
            JOptionPane.showMessageDialog(null, "Nơi sinh không tồn tại!");
            return null;
        }
        return placeId;
    }

    private boolean isPoint(double math, double physical, double chemistry) {
        return (math >= 0 && math <= 10) && (physical >= 0 && physical <= 10) && (chemistry >= 0 && chemistry <= 10);
    }

    private int convertPlaceNameToId(Map<Integer, String> map) {
        int placeId = 0;
        String s = StudentInformationPanel.txtBirthPlace.getText().toLowerCase();
        for (int i = 2; i <= map.size(); i++) {
            if (s.equals(map.get(i).toLowerCase()))
                placeId = i;
        }
        return placeId;
    }

}

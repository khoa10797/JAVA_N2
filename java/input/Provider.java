package input;

import model.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface Provider {

	List<Student> loadStudent()
			throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException;

	Vector<String> loadPlaceName() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException;

	Map<Integer, String> loadPlace() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException;

	void insertData(List<Student> list, Student student) throws SQLException, ParseException, StudentIdException, ClassNotFoundException;

	void updateData(List<Student> list, Student student) throws SQLException, ParseException, ClassNotFoundException;

	void deleteData(List<Student> list, int index, int id) throws SQLException, ClassNotFoundException;

	void close(List<Student> list) throws FileNotFoundException, ParserConfigurationException,
            TransformerConfigurationException, TransformerException, UnsupportedEncodingException;
}

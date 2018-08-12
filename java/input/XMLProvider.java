package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Student;

public class XMLProvider implements Provider {

	@Override
	public List<Student> loadStudent() throws ParserConfigurationException, SAXException, IOException {
		List<Student> list = new ArrayList<>();
		NodeList nodeList = createNodeList(new File("Data\\XML\\thisinh.xml"), "thisinh");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element student = (Element) nodeList.item(i);

			int id = Integer.parseInt(student.getElementsByTagName("mathisinh").item(0).getTextContent());

			String name = student.getElementsByTagName("ten").item(0).getTextContent();

			int idPlace = Integer.parseInt(student.getElementsByTagName("matinh").item(0).getTextContent());

			String date = student.getElementsByTagName("ngaysinh").item(0).getTextContent();

			boolean sex = Boolean.valueOf(student.getElementsByTagName("gioitinh").item(0).getTextContent());

			double math = Double.parseDouble(student.getElementsByTagName("toan").item(0).getTextContent());

			double physical = Double.parseDouble(student.getElementsByTagName("ly").item(0).getTextContent());

			double chemistry = Double.parseDouble(student.getElementsByTagName("hoa").item(0).getTextContent());

			list.add(new Student(id, name, idPlace, date, sex, math, physical, chemistry));
		}
		return list;
	}

	@Override
	public Vector<String> loadPlaceName() throws ParserConfigurationException, SAXException, IOException {
		Vector<String> vector = new Vector<>();

		NodeList nodeList = createNodeList(new File("Data\\XML\\quequan.xml"), "quequan");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element place = (Element) nodeList.item(i);
			String name = place.getElementsByTagName("tentinh").item(0).getTextContent();
			vector.add(name);
		}
		return vector;
	}

	@Override
	public Map<Integer, String> loadPlace() throws ParserConfigurationException, SAXException, IOException {
		Map<Integer, String> map = new HashMap<>();

		NodeList nodeList = createNodeList(new File("Data\\XML\\quequan.xml"), "quequan");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element place = (Element) nodeList.item(i);
			int id = Integer.parseInt(place.getElementsByTagName("matinh").item(0).getTextContent());
			String name = place.getElementsByTagName("tentinh").item(0).getTextContent();
			map.put(id, name);
		}
		return map;
	}

	@Override
	public void insertData(List<Student> list, Student student) throws ParseException, StudentIdException {
		checkDate(student.getDate());
		if (list.contains(student))
			throw new StudentIdException("Mã thí sinh đã tồn tại!");
		list.add(student);
	}

	@Override
	public void updateData(List<Student> list, Student student) throws SQLException, ParseException {
		checkDate(student.getDate());
		list.add(student);
	}

	@Override
	public void deleteData(List<Student> list, int index, int id) throws SQLException {
		list.remove(index);
	}

	@Override
	public void close(List<Student> list)
			throws FileNotFoundException, ParserConfigurationException, TransformerException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element studentElement = document.createElement("danhsach");
		document.appendChild(studentElement);
		list.forEach(student -> {
			Element stElement = document.createElement("thisinh");

			stElement.appendChild(createElement(document, "mathisinh", String.valueOf(student.getId())));

			stElement.appendChild(createElement(document, "ten", student.getName()));

			stElement.appendChild(createElement(document, "matinh", String.valueOf(student.getIdPlace())));

			stElement.appendChild(createElement(document, "ngaysinh", student.getDate()));

			stElement.appendChild(createElement(document, "gioitinh", Boolean.toString(student.getSex())));

			stElement.appendChild(createElement(document, "toan", String.valueOf(student.getMath())));

			stElement.appendChild(createElement(document, "ly", String.valueOf(student.getPhysical())));

			stElement.appendChild(createElement(document, "hoa", String.valueOf(student.getChemistry())));

			studentElement.appendChild(stElement);
		});

		Transformer transformer = getTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File("Data\\XML\\thisinh.xml"));
		transformer.transform(source, result);

	}

	private NodeList createNodeList(File filename, String tagName)
			throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(filename);
		Element root = document.getDocumentElement();
		return root.getElementsByTagName(tagName);
	}

	private Document getDocument(File fileStudent) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return documentBuilder.parse(fileStudent);
	}

	private Element createElement(Document document, String tagName, String content) {
		Element element = document.createElement(tagName);
		element.setTextContent(content);
		return element;
	}

	private Transformer getTransformer() throws TransformerConfigurationException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		return transformer;
	}

	private void checkDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		java.util.Date utilStartDate = df.parse(date);
	}
}

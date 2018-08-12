package input;

import model.Student;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TextProvider implements Provider {

	@Override
	public List<Student> loadStudent() throws FileNotFoundException {
		List<Student> list = new ArrayList<>();
		Scanner sc = new Scanner(new File("Data\\Text\\thisinh.txt"), "UTF-8");
		String line;
		while (sc.hasNext()) {
			line = sc.nextLine();
			String[] str = line.split("[;,]");
			Student student = new Student(Integer.parseInt(str[Student.ID - 1]), str[Student.Name - 1].trim(),
					Integer.parseInt(str[Student.Place - 1].trim()), str[Student.Date - 1].trim(),
					Boolean.valueOf(str[Student.Sex - 1].trim()), Double.parseDouble(str[Student.Math - 1]),
					Double.parseDouble(str[Student.Physical - 1]), Double.parseDouble(str[Student.Chemistry - 1]));
			list.add(student);
		}
		sc.close();
		return list;
	}

	@Override
	public Vector<String> loadPlaceName() throws FileNotFoundException {
		Vector<String> vector = new Vector<>();
		Scanner sc = new Scanner(new File("Data\\Text\\quequan.txt"), "UTF-8");
		String line;
		while (sc.hasNext()) {
			line = sc.nextLine();
			String[] str = line.split("[;]");
			vector.add(str[0].trim());
		}
		sc.close();
		return vector;
	}

	@Override
	public Map<Integer, String> loadPlace() throws FileNotFoundException {
		Map<Integer, String> map = new HashMap<>();
		Scanner sc = new Scanner(new File("Data\\Text\\quequan.txt"), "UTF-8");
		String line;
		while (sc.hasNext()) {
			line = sc.nextLine();
			String[] str = line.split("[;]");
			map.put(Integer.parseInt(str[1].trim()), str[0].trim());
		}
		sc.close();
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
	public void updateData(List<Student> list, Student student) throws ParseException {
		checkDate(student.getDate());
		list.add(student);
	}

	@Override
	public void deleteData(List<Student> list, int index, int id) {
		list.remove(index);
	}

	@Override
	public void close(List<Student> list) throws FileNotFoundException, UnsupportedEncodingException {

		File file = new File("Data\\Text\\thisinh.txt");
		PrintWriter writer = new PrintWriter((new OutputStreamWriter(new FileOutputStream(file), "UTF-8")),
		false);

		list.forEach(student -> {
			writer.println(student.getId() + "; " + student.getName() + ", " + student.getIdPlace() + ", "
					+ student.getDate() + ", " + Boolean.toString(student.getSex()) + ", " + student.getMath() + ", "
					+ student.getPhysical() + ", " + student.getChemistry());
		});
		writer.close();
	}

	private void checkDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		Date utilStartDate = df.parse(date);
	}
}

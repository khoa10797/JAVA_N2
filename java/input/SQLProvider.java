package input;

import model.Student;

import java.sql.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import connection.JDBCConnection;

public class SQLProvider implements Provider {

	@Override
	public List<Student> loadStudent() throws SQLException, ClassNotFoundException {
		List<Student> list = new ArrayList<>();
		ResultSet rs = getResultSet("SELECT * FROM ThiSinh");
		while (rs.next()) {
			String date = String.valueOf(rs.getDate(Student.Date));
			Student student = new Student(rs.getInt(Student.ID), rs.getString(Student.Name), rs.getInt(Student.Place),
					dateProcessing(date), rs.getBoolean(Student.Sex), rs.getDouble(Student.Math),
					rs.getDouble(Student.Physical), rs.getDouble(Student.Chemistry));
			list.add(student);
		}
		rs.close();
		return list;
	}

	@Override
	public Vector<String> loadPlaceName() throws SQLException, ClassNotFoundException {
		Vector<String> vector = new Vector<>();
		ResultSet rs = getResultSet("SELECT TenQue FROM Que");
		while (rs.next())
			vector.add(rs.getString(1));
		rs.close();
		return vector;
	}

	@Override
	public Map<Integer, String> loadPlace() throws SQLException, ClassNotFoundException, NumberFormatException {
		Map<Integer, String> map = new HashMap<>();
		ResultSet rs = getResultSet("SELECT * FROM Que");
		while (rs.next())
			map.put(Integer.parseInt(rs.getString(1)), rs.getString(2));
		rs.close();
		return map;
	}

	@Override
	public void insertData(List<Student> list, Student student) throws SQLException, ParseException, ClassNotFoundException {
		String sql = "INSERT INTO ThiSinh VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = getPreparedStatement(sql, student, 1, 2);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		list.add(student);
	}

	@Override
	public void updateData(List<Student> list, Student student) throws SQLException, ParseException, ClassNotFoundException {
		String sql = "UPDATE ThiSinh SET  Ten=?, MaQue=?, NgaySinh=?, GioiTinh=?, DiemToan=?, DiemLy=?, DiemHoa=? WHERE MaTS=?";
		PreparedStatement preparedStatement = getPreparedStatement(sql, student, 8, 1);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		list.add(student);
	}

	@Override
	public void deleteData(List<Student> list, int index, int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM ThiSinh WHERE MaTS = ";
		Statement statement = JDBCConnection.getConnection().createStatement();
		statement.executeUpdate(sql + String.valueOf(id));
		statement.close();
		list.remove(index);
	}

	@Override
	public void close(List<Student> list) {

	}

	private ResultSet getResultSet(String sql) throws SQLException, ClassNotFoundException {
		Statement statement = JDBCConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}

	private PreparedStatement getPreparedStatement(String sql, Student student, int indexStudentId,
			int indexStudentName) throws SQLException, ParseException, ClassNotFoundException {
		PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(indexStudentId, student.getId());
		preparedStatement.setString(indexStudentName, student.getName());
		preparedStatement.setInt(indexStudentName + 1, student.getIdPlace());

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		java.util.Date utilStartDate = df.parse(student.getDate());
		Date date = new Date(utilStartDate.getTime());
		preparedStatement.setDate(indexStudentName + 2, date);

		preparedStatement.setBoolean(indexStudentName + 3, student.getSex());
		preparedStatement.setFloat(indexStudentName + 4, (float) student.getMath());
		preparedStatement.setFloat(indexStudentName + 5, (float) student.getPhysical());
		preparedStatement.setFloat(indexStudentName + 6, (float) student.getChemistry());

		return preparedStatement;
	}

	private String dateProcessing(String date) {
		String[] s = date.split("[-]");
		String date1 = s[2] + "/" + s[1] + "/" + s[0];
		return date1;
	}

}

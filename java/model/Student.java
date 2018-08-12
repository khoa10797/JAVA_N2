package model;

public class Student {
	/**
	 * 
	 */

	private int id, idPlace;
	private String name, date;
	private boolean sex;
	private double math, physical, chemistry;
	/**
	 * 
	 */
	public final static int ID = 1;
	public final static int Name = 2;
	public final static int Place = 3;
	public final static int Date = 4;
	public final static int Sex = 5;
	public final static int Math = 6;
	public final static int Physical = 7;
	public final static int Chemistry = 8;

	public Student() {

	}

	public Student(int id, String name, int idPlace, String date, boolean sex, double math, double physical,
			double chemistry) {
		this.id = id;
		this.name = name;
		this.idPlace = idPlace;
		this.date = date;
		this.sex = sex;
		this.math = math;
		this.physical = physical;
		this.chemistry = chemistry;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getIdPlace() {
		return idPlace;
	}

	public String getDate() {
		return date;
	}

	public boolean getSex() {
		return sex;
	}

	public double getMath() {
		return math;
	}

	public double getPhysical() {
		return physical;
	}

	public double getChemistry() {
		return chemistry;
	}

	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}

	public String getSexName() {
		if (getSex())
			return "Nam";
		return "Nữ";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(obj instanceof Student))
			return false;
		Student other = (Student)obj;
		return id == other.id;
	}
	
}

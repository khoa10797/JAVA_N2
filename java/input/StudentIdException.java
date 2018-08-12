package input;

public class StudentIdException extends Exception {
	public StudentIdException(String name) {
		super(name);
	}

	@Override
	public String getMessage() {
		return "Mã thí sinh không được trùng nhau!";
	}

}

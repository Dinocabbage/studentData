package studentData;

public class InputWrongGenderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public InputWrongGenderException(String msg) {
    	super(msg);
    	
    }
}
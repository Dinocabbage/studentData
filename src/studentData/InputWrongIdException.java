package studentData;

public class InputWrongIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public InputWrongIdException(String msg) {
    	super(msg);
    	
    }
}
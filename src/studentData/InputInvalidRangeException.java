package studentData;

public class InputInvalidRangeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public InputInvalidRangeException(String msg) {
    	super(msg);
    	
    }
}
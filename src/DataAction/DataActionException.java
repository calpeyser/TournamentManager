package DataAction;

public class DataActionException extends Exception {
	
	private String message = null;
	
	public DataActionException() {
		super();
	}
	
	public DataActionException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}

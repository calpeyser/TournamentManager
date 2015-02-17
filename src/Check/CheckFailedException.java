package Check;

public class CheckFailedException extends Exception {
	private String message = null;
	
	public CheckFailedException() {
		super();
	}
	
	public CheckFailedException(String message) {
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

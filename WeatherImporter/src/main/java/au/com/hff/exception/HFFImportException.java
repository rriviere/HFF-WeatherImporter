package au.com.hff.exception;

/**
 * @author richard.riviere
 *
 */
public class HFFImportException extends Exception {

	private static final long serialVersionUID = 746418716496508516L;

	/**
	 * Constructor with error message and throwable
	 * @param message Error message
	 * @param cause Wrapping exception
	 */
	public HFFImportException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructor to wrap other exceptions
	 * @param cause Wrapping exception
	 */
	public HFFImportException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructor with message
	 * @param message Error message
	 * @deprecated  Not for public use in the future.
	 * @see #BusinessException(String, String)
	 */
	public HFFImportException(String message)
    {
		super(message);
    }
}

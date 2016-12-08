package net.turnbig.qb;

import java.text.*;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException() {
		super();
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(String message, Object... formatArgs) {
		super(MessageFormat.format(message, formatArgs));
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String toString() {
		return "ParseException [" + getMessage() + "]";
	}

}

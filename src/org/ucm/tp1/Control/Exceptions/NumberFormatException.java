package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class NumberFormatException extends CommandParseException {
	public NumberFormatException(String txt) {
		super(txt);
	}
}

package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends CommandExecuteException {
	public InvalidPositionException(String txt) {
		super(txt);
	}
}

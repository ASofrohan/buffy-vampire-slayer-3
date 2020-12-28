package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class NoMoreVampiresException extends CommandExecuteException {
	public NoMoreVampiresException(String txt) {
		super(txt);
	}
}
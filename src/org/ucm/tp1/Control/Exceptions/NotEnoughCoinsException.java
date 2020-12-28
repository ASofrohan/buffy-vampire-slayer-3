package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class NotEnoughCoinsException extends CommandExecuteException {
	public NotEnoughCoinsException(String txt) {
		super(txt);
	}
}

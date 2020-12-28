package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class DraculaIsAliveException extends CommandExecuteException {
	public DraculaIsAliveException(String txt) {
		super(txt);
	}
}
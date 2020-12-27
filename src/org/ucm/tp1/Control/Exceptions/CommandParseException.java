package org.ucm.tp1.Control.Exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends GameException{
	public CommandParseException(String txt) {
		super(txt);
	}
}

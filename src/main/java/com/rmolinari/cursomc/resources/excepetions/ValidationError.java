package com.rmolinari.cursomc.resources.excepetions;

import java.util.ArrayList;


public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private java.util.List<FieldMessage> errrors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public java.util.List<FieldMessage> getErrrors() {
		return errrors;
	}

	public void addError(String fieldName, String message) {
		errrors.add(new FieldMessage(fieldName, message));
	}



}

package com.blackjack.exception;

public class TransactionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mess;

	public TransactionException(String mess) {
		super();
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	

}

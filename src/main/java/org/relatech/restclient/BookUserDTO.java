package org.relatech.restclient;

public class BookUserDTO {
	
	private String fiscalCode;
	
	public BookUserDTO(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

}

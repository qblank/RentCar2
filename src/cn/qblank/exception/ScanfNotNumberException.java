package cn.qblank.exception;

public class ScanfNotNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//异常返回码
	private String errorCode;
	//异常的详细信息
	private String eMessage;
	
	public ScanfNotNumberException(){
		
	}
	
	
	public ScanfNotNumberException(String errorCode, String eMessage) {
		super();
		this.errorCode = errorCode;
		this.eMessage = eMessage;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String geteMessage() {
		return eMessage;
	}


	public void seteMessage(String eMessage) {
		this.eMessage = eMessage;
	}
	
	

}

package test.message;

public class DefaultMsg {

	private short msgCode;
	private String message;
	
	public short getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(short msgCode) {
		this.msgCode = msgCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "DefaultMsg [msgCode=" + msgCode + ", message=" + message + "]";
	}

}

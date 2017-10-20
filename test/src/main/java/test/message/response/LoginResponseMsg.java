package test.message.response;

import test.message.JsonMsgBody;

/**
 * 登录响应
 * @author GeTOUO
 *
 */
public class LoginResponseMsg extends JsonMsgBody {

	private boolean result;
	private String context;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
}

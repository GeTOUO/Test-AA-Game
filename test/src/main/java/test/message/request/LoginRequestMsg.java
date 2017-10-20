package test.message.request;

import test.message.JsonMsgBody;

/**
 * 登录请求消息结构定义
 * @author GeTOUO
 *
 */
public class LoginRequestMsg extends JsonMsgBody {

	private String name;		//用户名
	private String password;	//用户密码
	
	//Getter And Setter:
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequestMsg [name=" + name + ", password=" + password + "]";
	}
	
//	public static void main(String[] args) {
//		LoginRequestMsg loginRequestMsg = new LoginRequestMsg();
//		loginRequestMsg.setName("zhangsan");
//		loginRequestMsg.setPassword("12345");
//		DefaultMsg defaultMsg = new DefaultMsg();
//		defaultMsg.setMsgCode((short) 1);
//		defaultMsg.setMessage(loginRequestMsg);
//		String jsonString = JSON.toJSONString(defaultMsg);
//		System.err.println(jsonString);
//	}
	
}

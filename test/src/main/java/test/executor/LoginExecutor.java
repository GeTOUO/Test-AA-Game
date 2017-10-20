package test.executor;

import com.alibaba.fastjson.JSON;
import com.angke.common.annotation.MsgToExec;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import test.BaseBusinessLogicExecutor;
import test.message.request.LoginRequestMsg;
import test.message.response.LoginResponseMsg;

@MsgToExec(msgType = 0x01)
public class LoginExecutor extends BaseBusinessLogicExecutor {

	@Override
	public boolean privilegeInterception() {
		return false;
	}

	@Override
	public void businessLogicRun() {

		
		System.err.println("登录请求被调用，登录执行");
		
		TextWebSocketFrame textWebSocketFrame;
		try {
			LoginRequestMsg msg = (LoginRequestMsg)requestMsg;
			msg.getName();
			msg.getPassword();
			
			LoginResponseMsg responseMsg = new LoginResponseMsg();
			responseMsg.setContext(msg.getName() + " 请求登录");
			responseMsg.setResult(true);
			
//			String jsonString = JSON.toJSONString(responseMsg);
//			System.err.println("登录请求执行完毕,结果是:" + jsonString);
//			textWebSocketFrame = new TextWebSocketFrame(jsonString);
			
			channel.writeAndFlush(responseMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

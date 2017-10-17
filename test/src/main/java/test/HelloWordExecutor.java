package test;

import com.angke.common.annotation.MsgToExec;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@MsgToExec(msgType = 0)//这里01   我们最好是写常量 方便维护不然到时候找起来挺麻烦的
public class HelloWordExecutor extends BaseBusinessLogicExecutor {

	@Override
	public boolean privilegeInterception() {
		// 我们在这里helloworld里面就不做什么处理了这里
		// 返回不需要进行特殊的流程
		return false;
	}

	@Override
	public void businessLogicRun() {

		//这里我们来进行正唱的业务：
		//这里我们可以拿到我们处理一条业务需要的完整的业务属性:连接 消息，我们就针对这个做一下操作
		String string = requestMsg.toString();
		System.err.println("$$$$$$$$$$$$$$$$$$$$$$$$打印处理消息￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
		try {
//			channel.writeAndFlush("我收到了你的消息:" + string);
			
			channel.writeAndFlush(new TextWebSocketFrame("我收到了你的消息:" + string));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("OK");
	}

}

package test;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import test.message.DefaultMsg;

@Sharable
public class DispatherHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		boolean goNext = true;//假设我们需要一个过滤消息的开关，比如过滤客户端传的非json消息
		
		if (msg instanceof String) {
			//这里可以定义个特殊的消息渠道，比如如果需要实现针对某个特殊类型的消息做什么事情？
			//TextWebSocketFrame就是我们和前端约定好，比如你给我发一个BinaryWebsocket，我直接告诉你我不接受ws的二进制帧，就不用进业务处理器来浪费资源了
			//goNext = false;我们这里就不这么干了，不然上面什么消息等于TextWebSocketFrame，走不下去了！
			String request = (String)msg;
			System.err.println("request: " + request);
			DefaultMsg defaultMsg;
			try {
				defaultMsg = JSON.parseObject(request, DefaultMsg.class);
				String string = defaultMsg.toString();
				System.err.println(string);
				Dispatcher.submit(ctx.channel(), defaultMsg);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			System.err.println("msg instanceof TextWebSocketFrame");
		}
		
		if (goNext) {
//			TextWebSocketFrame mm = (TextWebSocketFrame)msg;
			//否则的话，我们就正常的把消息拿去分发，这里为了快速写消息和定位连接对象，我们把本次请求的连接也直接传
			System.err.println("Dispatcher.submit(ctx.channel(), msg.toString());");
		}
//		ctx.channel().writeAndFlush("1212121");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

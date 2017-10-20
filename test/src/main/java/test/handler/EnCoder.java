//package test.handler;
//
//import java.util.List;
//
//import com.alibaba.fastjson.JSON;
//import com.angke.common.config.MsgConfig;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;
//import io.netty.handler.codec.http.websocketx.WebSocketFrame;
//import test.message.DefaultMsg;
//import test.message.MsgBody;
//
//public class EnCoder extends WebSocket13FrameEncoder {
//
//	public EnCoder(boolean maskPayload) {
//		super(maskPayload);
//		// TODO Auto-generated constructor stub
//	}
//	@Override
//	protected void encode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
//		// TODO Auto-generated method stub
//		if (msg instanceof TextWebSocketFrame) {
//			
//		}
//		if (msg instanceof MsgBody) {
//			System.err.println("msg instanceof MsgBody");
//			Class<? extends Object> msgClass = msg.getClass();
//			System.err.println("msgClass : " + msgClass);
//			short msgCodeByMsgClass = MsgConfig.getMsgCodeByMsgClass(msgClass);
//			System.err.println("msgCodeByMsgClass " + msgCodeByMsgClass);
//			DefaultMsg defaultMsg = new DefaultMsg();
//			defaultMsg.setMsgCode(msgCodeByMsgClass);
//			defaultMsg.setMessage((MsgBody)msg);
//			System.err.println("defaultMsg " + defaultMsg);
//			String jsonString = JSON.toJSONString(defaultMsg);
//			System.err.println("出战包装：完成包装消息头部");
//			System.err.println("jsonString " + jsonString);
//			TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(jsonString);
//			super.write(ctx, textWebSocketFrame, promise);
//		}else {
//			super.write(ctx, msg, promise);
//		}
//		
//		super.encode(ctx, msg, out);
//	}
//
//}

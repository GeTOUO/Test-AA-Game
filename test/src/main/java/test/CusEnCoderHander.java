package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.angke.common.config.MsgConfig;
import com.esotericsoftware.minlog.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import test.message.DefaultMsg;
import test.message.JsonMsgBody;

public class CusEnCoderHander extends ChannelOutboundHandlerAdapter {

	Logger Log = LoggerFactory.getLogger(ChannelOutboundHandlerAdapter.class);
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (msg instanceof JsonMsgBody) {
			
			Log.info("msg instanceof JsonMsgBody");
			
			Class<? extends Object> msgClass = msg.getClass();
			
			Log.info("msgClass : {}",msgClass);
			short msgCodeByMsgClass = MsgConfig.getMsgCodeByMsgClass(msgClass);
			Log.info("msgCodeByMsgClass : {}",msgCodeByMsgClass);
			DefaultMsg defaultMsg = new DefaultMsg();
			
			String string = JSON.toJSONString(msg);
			
			defaultMsg.setMsgCode(msgCodeByMsgClass);
			defaultMsg.setMessage(string);
			Log.info("defaultMsg {}", defaultMsg);
			String jsonString = JSON.toJSONString(defaultMsg);
			
			Log.info("出战包装：完成包装消息头部 ");
			Log.info("jsonString {}", jsonString);
			
			TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(jsonString);
			super.write(ctx, textWebSocketFrame, promise);
		}else {
			super.write(ctx, msg, promise);
		}
	}
}

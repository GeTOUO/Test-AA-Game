package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.angke.common.constant.GlobalConfigNames;
import com.angke.game.config.WebSocketServerConfig;
import com.angke.game.io.transport.GameBootStrap;
import com.angke.game.io.transport.websocket.TextWebSocketFrameHandler;
import com.angke.game.io.transport.websocket.WebSocketServerBootstrap;

public abstract class ServerStartUp {

	private static final Logger LOG = LoggerFactory.getLogger(ServerStartUp.class);
	
	@SuppressWarnings("unchecked")
	private static void startServer() throws Exception {

		
		WebSocketServerConfig websocketServerConfig = new WebSocketServerConfig("WebSocket Server", "/GlobalConfig.properties", GlobalConfigNames.WEBSOCKET_OP, GlobalConfigNames.SERVER_WEBSOCKET_PORT);
		GameBootStrap webSocketServerBootstrap = new WebSocketServerBootstrap();
//		websocketServerConfig.loadParme().loadHandlers(new ChannelHandler[] {new DispatherHandler()}, false).installConfig(webSocketServerBootstrap).start();
//		websocketServerConfig.loadParme().loadHandlers(new Class[] {TextWebSocketFrameHandler.class}, null, false).installConfig(webSocketServerBootstrap).start();
		websocketServerConfig.loadParme().loadHandlers(new Class[] {DispatherHandler.class}, null, false).installConfig(webSocketServerBootstrap).start();
//		websocketServerConfig.loadParme().loadHandlers(null, false).installConfig(webSocketServerBootstrap).start();
		
		
		LOG.info(">> Start Server SUCCESS! You Can Telnet And To Send Massage For This Server");
	}
	
	public static void startUp() {
		try {
			startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

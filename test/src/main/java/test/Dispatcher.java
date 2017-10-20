package test;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.angke.common.config.GlobalConfig;
import com.angke.common.config.MsgConfig;
import com.angke.common.constant.GlobalConfigNames;

import io.netty.channel.Channel;
import test.message.DefaultMsg;
import test.message.JsonMsgBody;

/**
 * 写一个线程池处理业务示例
 * @author GeTOUO
 *
 */
public class Dispatcher {

	private static final Logger logger = Logger.getLogger(Dispatcher.class);
	
	/**
	 * 这里构造一个线程池来处理我们的具体业务！线程的梳理我们可以在配置文件里面写着！
	 */
	private static ExecutorService executorService =
			Executors.newFixedThreadPool(Integer.parseInt(GlobalConfig.get(GlobalConfigNames.DISPATCHER_MAX_THREAD_NUM)));
	/**
	 * 这里就会把我们的业务提交给线程池去处理了
	 * @param channel 这个是用户的连接，我们这里就是websocket连接，当然如果是其它的tcp连接也是一样的
	 * @param msg 这个是我们的消息
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void submit(Channel channel, DefaultMsg msg) 
			throws InstantiationException, IllegalAccessException {
		
		//可以取出来看看我们这个消息有哪些字段，调试阶段好用
		Field[] fields = msg.getClass().getFields();
		
		short msgCode = msg.getMsgCode();
		String messageBody = msg.getMessage();
		System.err.println(messageBody);
		//这一步就很关键了，这里我们根据消息一个消息协议码，在系统初始化就映射好的：消息-类型-业务执行者 这个三维关系中直接反射出我们的业务执行者。
		System.err.println(messageBody.getClass());
		System.err.println(messageBody.toString());
//		Class<?> executorClass = MsgConfig.getExecutorClassByMsgCode(MsgConfig.getMsgCodeByMsgClass(msg.getClass()));
		Class<?> execClass = MsgConfig.getExecutorClassByMsgCode(msgCode);
		logger.info("取得执行器类型 " + execClass);
		BaseBusinessLogicExecutor executor =  (BaseBusinessLogicExecutor) execClass.newInstance();
		logger.info("取得执行器实例 " + executor);
		
		
		Class<Object> msgClassByMsgCode = MsgConfig.getMsgClassByMsgCode(msgCode);
		System.err.println(msgClassByMsgCode + "=====================");
		
		Object parseObject = JSON.parseObject(messageBody, msgClassByMsgCode);
		System.err.println(parseObject.getClass());
		System.out.println(parseObject.toString());
		
		executor.setChannel(channel);
		executor.setRequestMsg(parseObject);

		//最后提交给线程池去处理！
		executorService.submit(executor);
	}
}

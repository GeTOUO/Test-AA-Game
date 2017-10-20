package test;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;

/**
 * 
 * 实现了Runable接口的基本的业务逻辑执行类
 * 
 * @author LiangShengxian
 *
 */
public abstract class BaseBusinessLogicExecutor implements Runnable {

	//这里我假设定义几个属性，当然我们AA项目具体要定义哪些属性根据实际来
	
	//这个方法 假设我们业务体系要纯粹地把重连之类的跨级别的关联操作抽离出来处理，可以在这里设置一个开关，不过这样子做的话，会损失一些平均性能，但是能够减少一小部分业务的复杂成都
	//当然这个方法不是必须的！！！！
	public abstract boolean privilegeInterception();
	
	//这个方法就很关键了，本来我们可以直接让业务类覆盖run方法的，但是出于设计的角度，这里其实包装一层可以做更多的事情，比如检测我们一条业务处理的时间消耗
	//业务类的话，就在这个方法里面实现具体的流程就号了
	public abstract void businessLogicRun();
	
	//下面一般我们有一些公共的属性，就抽取一下
	protected Logger logger;
	protected Channel channel;
	protected Object requestMsg;
	protected Object responseMsg;
	
	@Override
	public void run() {

		logger.info("进到这里就表示我们收到客户端消息了:" + this.getClass());
		logger.info("Beging run() ：" + this.getClass());
		long startTime = System.currentTimeMillis(); 

		//举例子我们在这里做全局的重连行为判断，根据判断结果走一些附加的业务或者怎么样的
		if (privilegeInterception()) {
			
		} else {
			//像这种假设错误的业务逻辑之类的，或者错误的网络状态的，我们可以直接吧这个连接关掉，当然这样做是有风险的，可能关掉了有效的连接！
//			channel.close();
		}
		
		//这里正常的业务逻辑肯定是要走的！因此回掉我们的业务处理方法
		businessLogicRun();

		
		//这里我们就能够打印出来我们这一条业务处理用掉的时间了！这也是我们把run方法包装一层带来的便利
		long endTime = System.currentTimeMillis();  
		logger.info("Run() ：" + this.getClass() + " End------------------------------------------------------------------");
		System.err.println(this.getClass() + " use time: "+ (endTime - startTime) +"ms");  
//		return;
	}
	
	public BaseBusinessLogicExecutor() {
		super();
		logger = Logger.getLogger(getClass().getName());
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel cxt) {
		this.channel = cxt;
	}

	public Object getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(Object requestMsg) {
		this.requestMsg = requestMsg;
	}

}
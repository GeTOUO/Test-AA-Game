package test;

import com.angke.game.config.ServerInit;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerInit.loadSpring("spring-config.xml");
		ServerStartUp.startUp();
	}

}

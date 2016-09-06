package com.embracesource.config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	/*
	 * public static void main(String[] args) { ZkClient zkClient = new
	 * ZkClient("198.11.180.30:2181");// 建立连接 // zkClient.exists('/root');
	 * //判断目录是否存在 zkClient.create("/abc", "mydata", CreateMode.PERSISTENT);//
	 * 创建目录并写入数据 String data = zkClient.readData("/abc");
	 * System.out.println(data); zkClient.delete("/abc");// 删除目录 //
	 * zkClient.deleteRecursive("/root");//递归删除节目录 }
	 */

	public static void main(String[] args) throws IOException, InterruptedException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("zkpublisher.application.xml");
		DynamicPropertiesHelperFactory helperFactory = ((DynamicPropertiesHelperFactory) ctx.getBean(DynamicPropertiesHelperFactory.class));
		ctx.start();
		System.out.println("正在监听................");

		ConfigChangeSubscriber zkConfig = ((ConfigChangeSubscriber) ctx.getBean("configChangeSubscriber"));

		final CountDownLatch latch = new CountDownLatch(9999);
		zkConfig.subscribe("zoo.cfg", new ConfigChangeListener() {
			public void configChanged(String key, String value) {
				System.out.println("客户端接收到数据变更通知: key=" + key + ", value=" + value + "\n");
				ZkConfigSaver.main(null);
				System.out.println("正在监听................");
				latch.countDown();
			}
		});
		latch.await();
	}
}

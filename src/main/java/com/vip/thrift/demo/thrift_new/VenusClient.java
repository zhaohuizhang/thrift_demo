package com.vip.thrift.demo.thrift_new;

import java.util.Random;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class VenusClient {
	/**
	 * 调用阻塞式、多线程处理服务
	 * 
	 * @param args
	 */
	public void startClient() {
		try {
			// 设置传输通道-普通IO流通道
			// TTransport transport = new TSocket("localhost", 7911);

			// 调用非阻塞IO（NIO）服务的客户端
			// 设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
			TTransport transport = new TFramedTransport(new TSocket("localhost", 7911));

			// 使用高密度二进制协议
			TProtocol protocol = new TCompactProtocol(transport);

			// TProtocol protocol = new TBinaryProtocol(transport);
			// 创建Client
			Venus.Client client = new Venus.Client(protocol);

			transport.open();
			long start = System.currentTimeMillis();
			Random random = new Random();
			for (int i = 0; i < 10000; i++) {
				System.out.println(client.venusBoolean(true));
				System.out.println(client.venusInt(random.nextInt(10000)));
				System.out.println(client.venusNull());
				System.out.println(client.venusString("Do Something!"));
				client.venusVoid();
			}
			System.out.println("Run time:" + (System.currentTimeMillis() - start));

			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		VenusClient vClient = new VenusClient();
		vClient.startClient();
	}
}

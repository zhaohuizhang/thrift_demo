package com.vip.thrift.demo.thrift_new;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportFactory;

public class VenusServer {

	/**
	 * 阻塞式、多线程处理
	 * 
	 * @param args
	 */
	public void startServer() {

		try {
			// 设置传输通道，普通通道
			// TServerTransport serverTransport = new TServerSocket(7911);

			// 传输通道 - 非阻塞方式
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(7911);

			// 异步IO，需要使用TFramedTransport， 它将分块缓存读取
			TTransportFactory transportFactory = new TFramedTransport.Factory();

			// 使用高密度二进制协议
			TProtocolFactory proFactory = new TCompactProtocol.Factory();

			// TProtocolFactory proFactory = new TBinaryProtocol.Factory();
			// 设置处理器VenusImpl
			TProcessor processor = new Venus.Processor(new VenusImpl());

			Args args = new Args(serverTransport);
			args.processor(processor);
			args.protocolFactory(proFactory);
			args.transportFactory(transportFactory);

			// 创建服务器
			TServer server = new TThreadedSelectorServer(args);
			// TServer server = new TThreadPoolServer(args);
			System.out.println("Start server on port 7911");

			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		VenusServer vServer = new VenusServer();
		vServer.startServer();
	}
}

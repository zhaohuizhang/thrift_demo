package com.vip.thrift.demo.thrift_demo;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.vip.thrift.demo.thrift_demo.Hello.Processor;

public class Server {
	public void startServer() {
		try {
			TServerSocket serverTransport = new TServerSocket(1122);
			Hello.Processor processor = new Processor(new HelloImpl());
			Factory portFactory = new TBinaryProtocol.Factory(true, true);
			Args args = new Args(serverTransport);
			args.processor(processor);
			args.protocolFactory(portFactory);

			TServer server = new TThreadPoolServer(args);
			System.out.println("Starting server on port 1122 ...");
			server.serve();

		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();
	}
}

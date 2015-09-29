package com.vip.thrift.demo.thrift_new;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import com.vip.thrift.demo.thrift_new.Venus.AsyncClient.venusBoolean_call;

public class AsyncClient {
	/**
	 * 调用[非阻塞IO]服务，异步
	 * */
	public void startClient() {
		try {
			// 异步调用管理器
			TAsyncClientManager clientManager = new TAsyncClientManager();

			// 设置传输通道，调用非阻塞IO
			final TNonblockingTransport transport = new TNonblockingSocket("localhost", 7911);

			// 设置协议
			TProtocolFactory protocol = new TCompactProtocol.Factory();

			// 创建Client
			final Venus.AsyncClient client = new Venus.AsyncClient(protocol, clientManager, transport);

			// 调用服务
			System.out.println("start:" + System.currentTimeMillis());

			client.venusBoolean(false, new AsyncMethodCallback<Venus.AsyncClient.venusBoolean_call>() {

				public void onComplete(venusBoolean_call arg0) {
					// TODO Auto-generated method stub
					System.out.println("完成1：" + System.currentTimeMillis());
					try {
						client.venusBoolean(false, new AsyncMethodCallback<Venus.AsyncClient.venusBoolean_call>() {

							public void onComplete(venusBoolean_call arg0) {
								// TODO Auto-generated method stub
								System.out.println("完成2：" + System.currentTimeMillis());
								transport.close();
							}

							public void onError(Exception arg0) {
								// TODO Auto-generated method stub
								System.out.println("错误2：" + System.currentTimeMillis());
							}

						});
					} catch (TException e) {
						e.printStackTrace();
					}
				}

				public void onError(Exception arg0) {
					// TODO Auto-generated method stub
					System.out.println("错误1：" + System.currentTimeMillis());
				}
			});
			System.out.println("结束：" + System.currentTimeMillis());
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AsyncClient aClient = new AsyncClient();
		aClient.startClient();
	}
}

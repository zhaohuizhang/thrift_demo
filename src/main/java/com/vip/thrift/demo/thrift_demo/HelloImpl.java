package com.vip.thrift.demo.thrift_demo;

import org.apache.thrift.TException;

public class HelloImpl implements Hello.Iface {

	public void ping(int length) throws TException {
		// TODO Auto-generated method stub
		System.out.println("Calling ping , length= " + length);
	}

}

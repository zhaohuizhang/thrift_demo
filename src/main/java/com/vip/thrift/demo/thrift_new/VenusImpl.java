package com.vip.thrift.demo.thrift_new;

import org.apache.thrift.TException;

public class VenusImpl implements Venus.Iface {

	public String venusString(String para) throws TException {
		// TODO Auto-generated method stub
		return para;
	}

	public int venusInt(int para) throws TException {
		// TODO Auto-generated method stub
		return para;
	}

	public boolean venusBoolean(boolean para) throws TException {
		// TODO Auto-generated method stub
		return para;
	}

	public void venusVoid() throws TException {
		// TODO Auto-generated method stub
		System.out.println("run venus void!");
	}

	public String venusNull() throws TException {
		// TODO Auto-generated method stub
		return "NULL";
	}

}

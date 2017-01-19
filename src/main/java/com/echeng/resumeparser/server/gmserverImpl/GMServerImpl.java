package com.echeng.resumeparser.server.gmserverImpl;

import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;

import com.echeng.resumeparser.server.BaseWorker;

public class GMServerImpl extends BaseWorker implements GearmanFunction {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init here");
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run here");
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.echeng.resumeparser.server.gmserverImpl;


import org.gearman.Gearman;
import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.core.ParseController;
import com.echeng.resumeparser.domain.serverIO.response.Response;
import com.echeng.resumeparser.server.BaseWorker;
import com.echeng.resumeparser.server.gmserverImpl.domain.Header;
import com.echeng.resumeparser.server.gmserverImpl.domain.InputGM;
import com.echeng.resumeparser.server.gmserverImpl.domain.OutputGM;

public class GMServerImpl extends BaseWorker implements GearmanFunction {
	
	private static final Logger logger = LoggerFactory.getLogger("resume_parser_worker");
	
	private Integer id;
	
	private Gearman gearman;
	private GearmanServer server;
	private MessagePack msg;
	
	//注入
	@Autowired
	private ParseController parseController;

	public GMServerImpl(){}
	public GMServerImpl(int id){this.id = id;}
	
	@Override
	public void init() {
		gearman = Gearman.createGearman();
		server  = gearman.createGearmanServer(SERVER_HOST, SERVER_PORT);
		msg     = new MessagePack();
		System.out.println("init finished");
	}

	@Override
	public void reload() {
		destory();
		init();
		run();
	}

	@Override
	public void run() {
		for (int i = 0; i < WORKER_COUNT; i++) {
			GearmanWorker worker = gearman.createGearmanWorker();
			worker.addFunction(FUNCTION_NAME, new GMServerImpl(i));
			worker.addServer(server);
		}
		System.out.println("run here");
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
	}

	@Override
	public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception {
		OutputGM output = null;
		InputGM input = null;
		
		try {

			// unpack
			String inStr = msg.read(data).toString();
			input = JsonUtil.fromJson(inStr, InputGM.class);
			input.setMList(M_LIST);
			
			// check
			if (!input.isHeaderValid()){
				output = buildOutput(ERROR_1, null, input.getHeader());
				return msg.write(JsonUtil.parseHashMap(output));
			}
			if (!input.isRequestValid()){
				ERROR_2.setInfo(input.getReqErrInfo());
				output = buildOutput(ERROR_2, null, input.getHeader());
				return msg.write(JsonUtil.parseHashMap(output));
			}
			
			// handle
			Response res = parseController.handle(input.getM(), input.getRequest());
			if (null == res)
				output = buildOutput(ERROR_3, null, input.getHeader());
			else
				output = buildOutput(ERROR_0, res, input.getHeader());
			
			
			// pack
			byte[] retByte = msg.write(JsonUtil.parseHashMap(output));

			return retByte;

		} catch (Exception e) {
			logger.error("exception:" , e);
			output = buildOutput(ERROR_4, null, null==input ? null : input.getHeader());
			return msg.write(JsonUtil.parseHashMap(output));
		}
		
	}

	private OutputGM buildOutput(ERROR err, Response res, Header header) {
		res.setErrorNo(err.getId());
		res.setErrorMsg(err.getInfo());
		OutputGM output = new OutputGM(
				header, res
				);
		return output;
	}
}

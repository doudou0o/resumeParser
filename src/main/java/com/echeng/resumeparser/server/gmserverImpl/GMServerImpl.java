package com.echeng.resumeparser.server.gmserverImpl;


import java.io.IOException;
import java.util.ArrayList;

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
import com.echeng.resumeparser.domain.serverIO.response.IResponse;
import com.echeng.resumeparser.domain.serverIO.response.impl.BaseResponse;
import com.echeng.resumeparser.server.BaseWorker;
import com.echeng.resumeparser.server.gmserverImpl.domain.Header;
import com.echeng.resumeparser.server.gmserverImpl.domain.InputGM;
import com.echeng.resumeparser.server.gmserverImpl.domain.OutputGM;

public class GMServerImpl extends BaseWorker {
	
	private static final Logger logger = LoggerFactory.getLogger("resume_parser_worker");


	private Gearman gearman;
	private GearmanServer server;
	private MessagePack msg;
	
	//注入
	@Autowired
	private ParseController parseController;

	public GMServerImpl(){}
	
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
			worker.addFunction(FUNCTION_NAME, new gmWorkerHandle(i));
			worker.addServer(server);
		}
		System.out.println("run here");
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
	}

	private OutputGM buildOutput(ERROR err, IResponse res, Header header) {
		if (null == res)
			res = new BaseResponse();
		OutputGM output = new OutputGM();
		output.setResponse(res);
		output.setHeaderObj(header);
		output.setErrorNo(err.getId());
		output.setErrorMsg(err.getInfo());
		logger.info("return response:" + output.toString());
		return output;
	}
	
	private byte[] packOutMsg(OutputGM out, Boolean isJsonIn) throws IOException{
		if ( isJsonIn )
			return JsonUtil.toJson(out, true).getBytes();
		else
			return msg.write(JsonUtil.parseHashMap(JsonUtil.toJson(out, true)));
	}
	
	private String unpackInMsg(byte[] data, ArrayList<Boolean> isJsonIN_list) throws IOException{
		String inStr = null;
		
		inStr = msg.read(data).toString();
		if ( null!=inStr && inStr.startsWith("{") && inStr.length() > 10 )
			return inStr;

		inStr = new String(data);
		if ( null!=inStr && inStr.startsWith("{") && inStr.length() > 10 ){
			isJsonIN_list.add(0, true);
			return inStr;
		}
		
		inStr = "{\"header\":{},\"request\":{\"p\":{},\"c\":\"null\",\"m\":\"resumeparser\"}}";
		return inStr;
	}
	
	public class gmWorkerHandle implements GearmanFunction {
		
		@SuppressWarnings("unused")
		private int id;
		
		public gmWorkerHandle(){}
		public gmWorkerHandle(int id){this.id = id;}

		@Override
		public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception {
			OutputGM output = null;
			InputGM input = null;
			
			Boolean isJsonInput = false;
			try {
				ArrayList<Boolean> isJsonInput_list = new ArrayList<Boolean>(5);
				isJsonInput_list.add(false);
				
				// unpack
				String inStr = unpackInMsg(data, isJsonInput_list);
				isJsonInput = isJsonInput_list.get(0);

				input = JsonUtil.fromJson(inStr, InputGM.class, true);
				input.setMList(M_LIST);
				 //logger.info("receive request:" + input.toString());
				logger.info("receive request:" + (inStr.length() > 500 ? inStr.substring(0,500) : inStr));
				
				// check
				if (!input.isHeaderValid()){
					output = buildOutput(ERROR_1, null, input.getHeaderObj());
					return packOutMsg(output, isJsonInput);
				}
				if (!input.isRequestValid()){
					ERROR_2.setInfo(input.getReqErrInfo());
					output = buildOutput(ERROR_2, null, input.getHeaderObj());
					return packOutMsg(output, isJsonInput);
				}
				
				// handle
				IResponse res = parseController.handle(input.getM(), input.getRequest());
				if (null == res)
					output = buildOutput(ERROR_3, null, input.getHeaderObj());
				else
					output = buildOutput(ERROR_0, res, input.getHeaderObj());
				

				// pack
				byte[] retByte = packOutMsg(output, isJsonInput);

				return retByte;

			} catch (Exception e) {
				logger.error("exception:" , e);
				output = buildOutput(ERROR_4, null, null==input ? null : input.getHeaderObj());
				return packOutMsg(output, isJsonInput);
			}
		}
		
	}


	//test
	public static void main(String[] args) {
		GMServerImpl gm = new GMServerImpl();
		String inStr = "{\"header\":{},\"request\":{\"p\":{\"constraint_degree\":1,\"filetype\":\"1\",\"filename\":\"1\",\"groupname\":\"local\",\"rettype\":\"json\",\"runtype\":\"1\"},\"c\":\"ii\",\"m\":\"resumeparser\"}}";

		InputGM in = JsonUtil.fromJson(inStr, InputGM.class, true);
		in.isHeaderValid();
		OutputGM out = gm.buildOutput(ERROR_1, null, in.getHeaderObj());

		String ans = JsonUtil.toJson(out);
		System.out.println(ans);

		String ans2 = JsonUtil.toJson(out, true);
		System.out.println(ans2);
	}

}

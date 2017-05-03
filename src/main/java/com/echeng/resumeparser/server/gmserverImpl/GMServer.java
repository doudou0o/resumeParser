package com.echeng.resumeparser.server.gmserverImpl;


import java.util.Map;

import org.gearman.Gearman;
import org.gearman.GearmanFunction;
import org.gearman.GearmanFunctionCallback;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;
import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;

import com.echeng.resumeparser.common.log.Logger;
import com.echeng.resumeparser.common.log.LoggerFactory;
import com.echeng.resumeparser.controller.ControllerFactory;
import com.echeng.resumeparser.controller.IController;
import com.echeng.resumeparser.controller.ParseController;
import com.echeng.resumeparser.domain.serverIO.IResponse;
import com.echeng.resumeparser.server.BaseWorker;

public class GMServer extends BaseWorker {
	
	private static final Logger logger = LoggerFactory.getLogger("resume_parser_worker");


	private Gearman gearman;
	private GearmanServer server;
	
	//注入
	@Autowired
	private ControllerFactory controllerFactory;

	public GMServer(){}
	
	@Override
	public void init() {
		gearman = Gearman.createGearman();
		server  = gearman.createGearmanServer(SERVER_HOST, SERVER_PORT);
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

	
	public class gmWorkerHandle implements GearmanFunction {
		
		@SuppressWarnings("unused")
		private int id;
		
		public gmWorkerHandle(){}
		public gmWorkerHandle(int id){this.id = id;}

		@Override
		public byte[] work(String function, byte[] data, GearmanFunctionCallback callback) throws Exception {
		    
			// unpack data
			Map<String, Object> input = null;
			String packType = "";
			try {
				input = GMServerHelper.unpack_gmresquest(data);
				packType = (String) input.get("typeType");
				if (input == null)
					throw new IllegalAccessException("unpack data error!!");
			} catch (Exception e) {
				logger.error("unpack gearman request data", e);
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_1, "", null, null),
						packType
						);
			}
			
			logger.info("##### received request: %s", input.toString());


			// check header
			String head_err_msg = GMServerHelper.checkHeaderInReq(input);
			if (head_err_msg != null)
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_3, head_err_msg, null, null),
						packType
						);
			Map<String,Object> header = GMServerHelper.getHeaderFromReq(input);
			
			// check c,m,p
			Map<String,Object> cmp = GMServerHelper.getCMPByReq(input);
			if (cmp.get("m")==null || cmp.get("p")==null)
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_5, "", null, header),
						packType
						);
			
			// headle request
			IController controller = controllerFactory.getController((String) cmp.get("m"));
			if (controller == null)
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_2, (String) cmp.get("m"), null, header),
						packType
						);
			
			@SuppressWarnings("unchecked")
			Map<String, Object> req_p = (Map<String, Object>) cmp.get("p");
			if (!controller.checkRequest(req_p)) {
				String errmsg = controller.getErrmsgByReq(req_p);
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_4, errmsg, null, header),
						packType
						);
			}
			
			// handle request
			IResponse result;
			try {
				result = controller.handle(req_p);
			} catch (Exception e) {
				logger.error("Exception raised by request:"+input.toString(), e);
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_6, e.getMessage(), null, header),
						packType
						);
			}
			
			// return result
			if (null == result)
				return GMServerHelper.pack_gmresponse(
						GMServerHelper.assembleGMResponse(ERROR.ERROR_7, "", result, header),
						packType
						);
			
			return GMServerHelper.pack_gmresponse(
					GMServerHelper.assembleGMResponse(ERROR.ERROR_0, "", result, header),
					packType
					);
				
			
		}
		
	}


}

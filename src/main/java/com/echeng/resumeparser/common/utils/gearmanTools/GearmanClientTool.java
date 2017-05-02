package com.echeng.resumeparser.common.utils.gearmanTools;

import java.util.List;

import org.apache.log4j.Logger;
import org.gearman.Gearman;
import org.gearman.GearmanClient;
import org.gearman.GearmanJobEvent;
import org.gearman.GearmanJobReturn;
import org.gearman.GearmanServer;


public class GearmanClientTool {
	
	private Integer Port;
	private String  Host;
	private String  Worker;
	private byte[]  RequestData;
	private Boolean NeedPack = true;
	
	private static final Logger logger = Logger.getLogger(GearmanClientTool.class);
	private static final Gearman gearman = Gearman.createGearman();
	
	public static <T> T GearmanClientSubmit(Object input, String workerName, String type, Class<T> classOfT){
		//try
		byte[] reqBytes = GearmanMsgPack.msgPack(input, type);
		
		byte[] retBytes = new GearmanClientTool("icdc_basic").submit(reqBytes);
		
		return GearmanMsgPack.msgUnPack(retBytes, type, classOfT);
		
	}
	


	private GearmanClientTool(String worker){
		this.Worker = worker;
		initHostPort(worker);
	}

	private void initHostPort(String worker) {
		List<String> hosts = GMDNSConfFileUtil.getWorkHost(worker);

		if (null == hosts || hosts.isEmpty())
			throw new NullPointerException("no hosts with worker name: " + worker);
		hosts.add(0, "192.168.1.111:4730");//TODO
		String[] tmp = hosts.get(0).split(":");
		if (tmp.length < 2)
			throw new NullPointerException("the host with worker name:" + worker + ";is invalid");
	
		this.Host = tmp[0];
		this.Port = Integer.parseInt(tmp[1]);
	}

	private byte[] submit(byte[] data){
		return submit(Port,Host,Worker,data);
	}
	
	private byte[] submit(Integer p, String h, String w, byte[] data) {
		this.Port = p;
		this.Host = h;
		this.Worker = w;
		this.RequestData = data;
		return sendRequest();
	}
	
	private byte[] sendRequest() {
		if (!checkNecessaryInfo()){
			logger.error("something important not initialize!!");
			return null;
		}
		
		GearmanClient client = gearman.createGearmanClient();
		
		GearmanServer server = gearman.createGearmanServer(Host, Port);
		
		client.addServer(server);
		
		if (RequestData == null){
			logger.error("something important not initialize!!");
			return null;
		}
		Worker="resume_parser_module";
				
		GearmanJobReturn jobReturn = client.submitJob(Worker, RequestData);
		
		byte[] res = read_jobReturn(jobReturn);
		if (res == null){
			logger.error("something important not initialize!!");
			return null;
		}
		
		
		return res;
	}

	private Boolean checkNecessaryInfo() {
		if (this.Port!=null && this.Host!=null && this.Worker!=null
				&& this.NeedPack!=null && this.RequestData!=null)
			return true;
		else
			return false;
	}

	
	private static byte[] read_jobReturn(GearmanJobReturn jobReturn) {
		if ( jobReturn == null )return null;
		
        while (!jobReturn.isEOF()) {
            // Poll the next job event (blocking operation)
            GearmanJobEvent event = null;
            try {
                event = jobReturn.poll();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            switch (event.getEventType()) {
                // success
                case GEARMAN_JOB_SUCCESS:
                    logger.info(event.getData().toString());
                    return event.getData();

                // failure
                case GEARMAN_SUBMIT_FAIL:
                case GEARMAN_JOB_FAIL:
                    logger.info(event.getEventType() + ": "
                            + new String(event.getData()));
                default:
            }
        }
		return null;
	}
	
}

package com.echeng.resumeparser.server.gmserverImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.msgpack.MessagePack;

import com.echeng.resumeparser.common.utils.JsonUtil;
import com.echeng.resumeparser.domain.serverIO.IResponse;
import com.echeng.resumeparser.server.BaseWorker.ERROR;


public class GMServerHelper {
	
	private static MessagePack msg = new MessagePack();

	public static Map<String, Object> assembleGMResponse(ERROR err, String errMsg, IResponse res, Map<String, Object> header) {
		Map<String, Object> m_res = new HashMap<String, Object>();
		m_res.put("err_no", err.getId());
		m_res.put("err_msg", err.getInfo()+":"+errMsg);
		m_res.put("results", res);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("header", header);
		ret.put("response", m_res);
		
		return ret;
	}

	
	/**
	 * 
	 * @param data
	 * @return Map unpacked by request data, and put "packType"
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> unpack_gmresquest(byte[] data) throws IOException{
		
		Map<String, Object> request = null;
		try {
			request = JsonUtil.fromJson(new String(data), Map.class);
			request.put("packType", "json");
		} catch (Exception e) {}
		
		try {
			request = msg.read(data, Map.class);
			request.put("packType", "msgpack");
		} catch (Exception e) {}

		return request;
	}
	
	/**
	 * 
	 * @param data
	 * @return Map unpacked by request data, and put "packType"
	 */
	public static byte[] pack_gmresponse(Map<String, Object> response, String packType) {
		try {
			if ("msgpack".equals(packType)) {
				return msg.write(JsonUtil.parse2HashMap(JsonUtil.toJson(response)));
			}
			if ("json".equals(packType)) {
				return JsonUtil.toJson(response).getBytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * this methon checks header in req, if no err then return ""
	 * @param req
	 * @return
	 */
	public static String checkHeaderInReq(Map<String, Object> req){
		if (!req.containsKey("header"))
			return "no header in gearman request!!!";
		if (req.get("header")==null || Map.class.isInstance(req.get("header")))
			return "header in gearman request is invalid!!!";
		
		return "";
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getHeaderFromReq(Map<String, Object> input) {
		return (Map<String, Object>) input.get("header");
	}

	/**
	 * this methon takes c,m,p from req(map)
	 * 
	 * @param request
	 * @return
	 * insure c,m,p all in returned map
	 * insure c,m must be null or string
	 * insure p must be null or map
	 */
	public static Map<String, Object> getCMPByReq(Map<String, Object> request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> req = (Map<String, Object>) request.get("request");
		
		Map<String, Object> cmp = new HashMap<String, Object>();
		cmp.put("c", request.getOrDefault("c", req.getOrDefault("c", null)));
		cmp.put("m", request.getOrDefault("m", req.getOrDefault("m", null)));
		cmp.put("p", request.getOrDefault("p", req.getOrDefault("p", null)));
		
		
		return cmp;
	}



	
	
	
}

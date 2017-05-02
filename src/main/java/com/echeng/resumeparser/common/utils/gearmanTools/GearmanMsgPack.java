package com.echeng.resumeparser.common.utils.gearmanTools;

import java.io.IOException;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import com.echeng.resumeparser.common.utils.JsonUtil;

public class GearmanMsgPack {
	
	//private static final Logger logger = LoggerFactory.getLogger(GearmanMsgPack.class);
	
	/**
	 * this method is to pack object
	 * if type is "json" please keep obj is serializable by json
	 * @param obj
	 * @param type (only accept "msgpack" or "json" else throw a illegalArgumentException)
	 * @return byte[]
	 */
	public static byte[] msgPack(Object obj, String type){
		if ("msgpack" == type)
			return msg_pack(obj);
		else if ("json" == type)
			return JsonUtil.toJson(obj).getBytes();
		else
			throw new IllegalArgumentException("type is illegal");
	}
	
	/**
	 * @param bytesData
	 * @param type (only accept "msgpack" or "json" else throw a illegalArgumentException)
	 * @return
	 */
	public static <T> T msgUnPack(byte[] bytesData, String type, Class<T> classOfT){
		String jsonStr = "";
		
		if ("msgpack" == type)
			jsonStr = msg_unpack(bytesData);
		else if ("json" == type)
			jsonStr = new String(bytesData);
		else
			throw new IllegalArgumentException("type is illegal");
		
		if (null != jsonStr && "" != jsonStr)
			return JsonUtil.fromJson(jsonStr, classOfT);
		
		return null;
	}
	
	private static byte[] msg_pack(Object rowObj) {
		MessagePack msg=new MessagePack();
        byte[] msg_data = null;
        try {
            msg_data=msg.write(rowObj);
            return msg_data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	private static String msg_unpack(byte[] bytesData) {
		MessagePack msg=new MessagePack();
        Value raw_data = null;
        try {
            raw_data=msg.read(bytesData);
            return raw_data.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
}

package com.oddrock.penguin.penguinlistener.camel;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;
import com.oddrock.penguin.penguinlistener.entity.PenguinIMMsg;
import com.oddrock.penguin.penguinlistener.utils.MyBatisUtil;
import com.oddrock.penguin.penguinlistener.utils.PenguinUtils;

@Component("plugins4PenguinIM")
public class Plugins4PenguinIM {
	private static Logger logger = Logger.getLogger(Plugins4PenguinIM.class);
	
	public void alert(Exchange exchange, String payload){

	}
	public String rcv(Exchange exchange, String payload){
		logger.warn("has enter rcv method");
		StringBuffer content = new StringBuffer();
		if(payload.contains("post_type")){
			if(JsonPath.read(payload, "$.post_type").equals("receive_message")){
				PenguinIMMsg penguinMsg = parseJsonStr(payload);
				exchange.setProperty("penguinMsg", penguinMsg);
				content.append("\n---收到penguinMsg start---\n")
						.append(penguinMsg.toString())
						.append("\n---收到penguinMsg end  ---\n");
			}
		}else{
			content.append("\n---收到请求 start---\n")
					.append(payload).append("\n---收到请求 end  ---\n");
		}
		logger.warn(content);
		return payload;
	}
	
	/**
	 * 将数据保存到数据库
	 * @param exchange
	 * @param payload
	 * @return
	 */
	public String saveDb(Exchange exchange, String payload){
		Object o = exchange.getProperty("penguinMsg");
		if(o!=null){
			PenguinIMMsg msg = (PenguinIMMsg)o;
			savePenguinIMMsg(msg);
		}
		return payload;
	}
	
	private PenguinIMMsg parseJsonStr(String jsonStr){
		PenguinIMMsg penguinMsg = new PenguinIMMsg();
		penguinMsg.setMsg_class(JsonPath.read(jsonStr, "$.class").toString());
		if(JsonPath.read(jsonStr, "$.content")!=null){
			String content = JsonPath.read(jsonStr, "$.content").toString();
			content = PenguinUtils.filterEmoji(content);
			if(content.length()>0){
				penguinMsg.setContent(content);
			}
		}
		if(JsonPath.read(jsonStr, "$.id")!=null){
			penguinMsg.setId(Long.parseLong(JsonPath.read(jsonStr, "$.id").toString()));
		}
		penguinMsg.setPost_type(JsonPath.read(jsonStr, "$.post_type").toString());
		penguinMsg.setReceiver(PenguinUtils.filterEmoji(JsonPath.read(jsonStr, "$.receiver").toString()));
		if(JsonPath.read(jsonStr, "$.receiver_id")!=null){
			penguinMsg.setReceiver_id(Long.parseLong(JsonPath.read(jsonStr, "$.receiver_id").toString()));
		}
		if(JsonPath.read(jsonStr, "$.receiver_uid")!=null){
			penguinMsg.setReceiver_uid(Long.parseLong(JsonPath.read(jsonStr, "$.receiver_uid").toString()));
		}
		penguinMsg.setSender(PenguinUtils.filterEmoji(JsonPath.read(jsonStr, "$.sender").toString()));
		if(JsonPath.read(jsonStr, "$.sender_id")!=null){
			penguinMsg.setSender_id(Long.parseLong(JsonPath.read(jsonStr, "$.sender_id").toString()));
		}
		if(JsonPath.read(jsonStr, "$.sender_uid")!=null){
			penguinMsg.setSender_uid(Long.parseLong(JsonPath.read(jsonStr, "$.sender_uid").toString()));
		}
		penguinMsg.setTime(Long.parseLong(JsonPath.read(jsonStr, "$.time").toString()));
		penguinMsg.setType(JsonPath.read(jsonStr, "$.type").toString());
		String type = JsonPath.read(jsonStr, "$.type").toString();
		if(type.equals("group_message")){
			if(JsonPath.read(jsonStr, "$.group")!=null){
				penguinMsg.setQqgroup(JsonPath.read(jsonStr, "$.group").toString());
			}
			if(JsonPath.read(jsonStr, "$.group_id")!=null){
				penguinMsg.setQqgroup_id(Long.parseLong(JsonPath.read(jsonStr, "$.group_id").toString()));
			}
			if(JsonPath.read(jsonStr, "$.group_uid")!=null){
				penguinMsg.setQqgroup_uid(Long.parseLong(JsonPath.read(jsonStr, "$.group_uid").toString()));
			}
		}else if(type.equals("discuss_message")){ 
			if(JsonPath.read(jsonStr, "$.discuss")!=null){
				penguinMsg.setQqgroup(JsonPath.read(jsonStr, "$.discuss").toString());
			}
			if(JsonPath.read(jsonStr, "$.discuss_id")!=null){
				penguinMsg.setQqgroup_id(Long.parseLong(JsonPath.read(jsonStr, "$.discuss_id").toString()));
			}
		}
		return penguinMsg;
	}
	
	@SuppressWarnings("unused")
	private void savePenguinIMMsg(PenguinIMMsg msg){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        String statement = "com.oddrock.penguin.penguinlistener.entity.PenguinIMMsgMapper.addMsg";//映射sql的标识字符串
        if(msg.getContent()!=null){
        	if(msg.getContent().length()<64){
        		msg.setSummary(msg.getContent());
        	}else{
        		msg.setSummary(msg.getContent().substring(0, 64));
        	}
        	
        }
        msg.setRecord_time(new Date());
        int retResult = sqlSession.insert(statement,msg);
        sqlSession.close();
	}
}

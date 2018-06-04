package com.oddrock.penguin.penguinlistener.entity;
import java.util.Date;

public class PenguinIMMsg {
	private String msg_class;
	private String content;
	private long id;
	// post_type：receive_message：接收消息，send_message：发送消息，event：其他事件
	private String post_type;	
	private String receiver;
	private long receiver_id;
	private long receiver_uid;
	private String sender;
	private long sender_id;
	private long sender_uid;
	private long time;
	// msg_type：friend_message：好友消息，group_message：群消息，discuss_message：讨论组消息，sess_message：临时消息
	private String type;
	private Date record_time;
	private String summary;
	private String qqgroup;
	private long qqgroup_id;
	private long qqgroup_uid;
	public Date getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getMsg_class() {
		return msg_class;
	}
	public void setMsg_class(String msg_class) {
		this.msg_class = msg_class;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public long getReceiver_uid() {
		return receiver_uid;
	}
	public void setReceiver_uid(long receiver_uid) {
		this.receiver_uid = receiver_uid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public long getSender_id() {
		return sender_id;
	}
	public void setSender_id(long sender_id) {
		this.sender_id = sender_id;
	}
	public long getSender_uid() {
		return sender_uid;
	}
	public void setSender_uid(long sender_uid) {
		this.sender_uid = sender_uid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQqgroup() {
		return qqgroup;
	}
	public void setQqgroup(String qqgroup) {
		this.qqgroup = qqgroup;
	}
	public long getQqgroup_id() {
		return qqgroup_id;
	}
	public void setQqgroup_id(long qqgroup_id) {
		this.qqgroup_id = qqgroup_id;
	}
	public long getQqgroup_uid() {
		return qqgroup_uid;
	}
	public void setQqgroup_uid(long qqgroup_uid) {
		this.qqgroup_uid = qqgroup_uid;
	}
	@Override
	public String toString() {
		return "QQMsg [msg_class=" + msg_class + ", content=" + content
				+ ", id=" + id + ", post_type=" + post_type + ", receiver="
				+ receiver + ", receiver_id=" + receiver_id + ", receiver_uid="
				+ receiver_uid + ", sender=" + sender + ", sender_id="
				+ sender_id + ", sender_uid=" + sender_uid + ", time=" + time
				+ ", type=" + type + ", record_time=" + record_time
				+ ", summary=" + summary + ", qqgroup=" + qqgroup
				+ ", qqgroup_id=" + qqgroup_id + ", qqgroup_uid=" + qqgroup_uid
				+ "]";
	}

	
}

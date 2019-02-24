package org.study.spring.model.message.dto;

import java.util.Date;

public class MessageDTO {
	private int messageID;
	private String targetid;
	private String sender;
	private String message;
	private Date openDate;
	private Date sendDate;
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	@Override
	public String toString() {
		return "MessageDTO [messageID=" + messageID + ", targetid=" + targetid + ", sender=" + sender + ", message="
				+ message + ", openDate=" + openDate + ", sendDate=" + sendDate + "]";
	}

	
	
}

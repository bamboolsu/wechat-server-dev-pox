package com.leo.qrcode.entity;

public class QrcodeTicket {
		
	private String ticket; 
 
	private String expire_seconds;
	
	private String url;

	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpires_secondsn() {
		return expire_seconds;
	}
	public void setExpires_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

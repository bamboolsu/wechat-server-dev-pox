package org.leo.service;

public interface AccessToken {

	public void getAccessToken(String code, String state) throws Exception;
	public void refreshAccessToken() throws Exception;
	public void checkAccessToken() throws Exception;
	public void getUserInfo() throws Exception;
}

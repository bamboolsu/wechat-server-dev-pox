package org.leo.course.pojo;

/**
 * ƾ֤
 * 
 * @author leo
 * @date 2015-10-17
 */
public class Token {
	// �ӿڷ���ƾ֤
	private String accessToken;
	// ƾ֤��Ч�ڣ���λ����
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
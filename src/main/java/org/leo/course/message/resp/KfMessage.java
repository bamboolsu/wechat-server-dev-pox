package org.leo.course.message.resp;

/**
 * 文本消息
 * 
 * @author leo
 * @date 2015-09-11
 */
public class KfMessage extends BaseMessage {
	// 回复的消息内容
	private String KfAccount;

	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}
}

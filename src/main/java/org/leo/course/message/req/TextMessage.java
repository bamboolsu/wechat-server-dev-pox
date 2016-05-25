package org.leo.course.message.req;

/**
 * 文本消息
 * 
 * @author leo
 * @date 2015-09-11
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}

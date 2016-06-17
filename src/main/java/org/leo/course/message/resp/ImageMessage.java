package org.leo.course.message.resp;

/**
 * 图片消息
 * 
 * @author leo
 * @date 2015-09-11
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}

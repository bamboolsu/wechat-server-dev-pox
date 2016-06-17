package org.leo.course.message.resp;

/**
 * 音乐消息
 * 
 * @author leo
 * @date 2015-09-11
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}

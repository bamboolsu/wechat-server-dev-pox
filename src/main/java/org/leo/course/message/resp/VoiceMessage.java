package org.leo.course.message.resp;

/**
 * ������Ϣ
 * 
 * @author leo
 * @date 2015-09-11
 */
public class VoiceMessage extends BaseMessage {
	// ����
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}

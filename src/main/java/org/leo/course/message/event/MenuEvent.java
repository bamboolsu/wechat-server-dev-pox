package org.leo.course.message.event;

/**
 * �Զ���˵��¼�
 * 
 * @author leo
 * @date 2015-11-04
 */
public class MenuEvent extends BaseEvent {
	// �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}

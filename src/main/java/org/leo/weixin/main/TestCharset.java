package org.leo.weixin.main;


import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

public class TestCharset {

	public static void main(String[] args) {
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		Set<Entry<String, Charset>> sets = charsets.entrySet();
		for (Entry<String, Charset> entry : sets) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		String recvContent = "Tet";
		System.out.println(recvContent.toLowerCase());
		recvContent = "中文STTTt";
		System.out.println(recvContent.toLowerCase());
		recvContent.toLowerCase().contains("jiaobanji".toLowerCase());
		
	}

}

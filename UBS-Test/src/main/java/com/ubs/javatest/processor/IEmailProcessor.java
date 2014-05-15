package com.ubs.javatest.processor;

public interface IEmailProcessor {

	void processEmailCommand(String from, String subject, String content);
}

package com.ubs.javatest.processor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ubs.javatest.factory.CommandFactory;


public class EmailProcessor implements IEmailProcessor {

	Map<String,Integer> counterMap = new ConcurrentHashMap<String, Integer>();
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	@Override
	public void processEmailCommand(String from, String subject, String content) {
		
		updateCounter(from);
		List<String> subjectTokens = getActionFromSubject(subject);
		String action = subjectTokens.get(0);
		String fileName = subjectTokens.get(1);
		CommandFactory.getCommand(action).execute(fileName, content);
	}

	private List<String> getActionFromSubject(String subject) {
		return Arrays.asList(subject.split(" "));
		
	}

	private void updateCounter(String from) {
		Integer currentValueOfCounter = counterMap.get(from);
		if(currentValueOfCounter == null)
		{
			counterMap.put(from, 1);
		}

		readWriteLock.writeLock().tryLock();
		try{
			currentValueOfCounter = counterMap.get(from) + 1;
		}
		finally{
			readWriteLock.writeLock().unlock();
		}
		counterMap.put(from, currentValueOfCounter);
	}
	
	public String toString(){
		StringBuffer output = new StringBuffer();
		for (Entry<String,Integer> item : counterMap.entrySet()) {
			output.append(item.getKey() + ":" + item.getValue());
		}
		return output.toString();
	}

}

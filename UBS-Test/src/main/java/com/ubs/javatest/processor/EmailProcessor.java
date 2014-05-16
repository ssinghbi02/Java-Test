package com.ubs.javatest.processor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ubs.javatest.factory.CommandFactory;

/**
 * Email processor is used to process all incoming email messages 
 * also stores in memory request count from from user
 * @author ssinghbi02
 *
 */
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

	public Map<String, Integer> getCounterMap() {
		return counterMap;
	}

	public String toString(){
		StringBuffer output = new StringBuffer();
		for (Entry<String,Integer> item : counterMap.entrySet()) {
			output.append(item.getKey() + ":" + item.getValue());
		}
		return output.toString();
	}
	
	private List<String> getActionFromSubject(String subject) {
		if(subject == null)
		{
			throw new IllegalArgumentException("subject is null");
		}
		List<String> tokens = Arrays.asList(subject.split(" "));
		if(tokens.size() != 2)
		{
			throw new IllegalArgumentException("subject param is not in correct format");
		}
		if(tokens.size() == 2 && (tokens.get(0).isEmpty() || tokens.get(1).isEmpty()))
		{
			throw new IllegalArgumentException("filename and action both value should be passed in subject");
		}
		return tokens;
		
	}

	private void updateCounter(String from) {
		readWriteLock.writeLock().tryLock();
		try{
			counterMap.put(from, counterMap.get(from) == null?1:counterMap.get(from) + 1);
		}
		finally{
			readWriteLock.writeLock().unlock();
		}
	}
}

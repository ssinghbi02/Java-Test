package com.ubs.javatest.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import com.ubs.javatest.command.ICommand;

public class EmailProcessorTest {

	@Test
	public void testWriteEmail() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("writetest", "write write-test.txt", "test content");
		
		File file = new File(ICommand.filePath + "write-test.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("file content does not match","test content", br.readLine());
		Assert.assertEquals(1,emailProcessor.getCounterMap().get("writetest").intValue());
		br.close();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWriteEmailWithNullSubject() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("writetest", null, "test content");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWriteEmailWithFileNameMissingInSubject() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("writetest", "write", "test content");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWriteEmailWithFileNameOrActionIsMissingInSubject() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("writetest", "write ", "test content");
	}
	@Test
	public void testAppendEmail() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("appendtest", "append append-test.txt", "test content");
		
		File file = new File(ICommand.filePath + "append-test.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("file content does not match","test content", br.readLine());
		Assert.assertEquals(1,emailProcessor.getCounterMap().get("appendtest").intValue());
		br.close();
	}
	
	@Test
	public void testToString() throws Exception{
		EmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("shashank", "write write-test.txt", "test content");
		
		File file = new File(ICommand.filePath + "write-test.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("file content does not match","test content", br.readLine());
		Assert.assertEquals(1,emailProcessor.getCounterMap().get("shashank").intValue());
		Assert.assertEquals("shashank:1",emailProcessor.toString());
		br.close();
	}

}

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
		IEmailProcessor emailProcessor = new EmailProcessor();
		emailProcessor.processEmailCommand("shashank", "write write-test.txt", "test content");
		
		File file = new File(ICommand.filePath + "write-test.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("file content does not match","test content", br.readLine());
		br.close();
	}

}

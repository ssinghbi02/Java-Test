package com.ubs.javatest.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import com.ubs.javatest.factory.CommandFactory;

public class WriteCommandTest {

	@Test
	public void testWriteNewContentToFile() throws Exception{
		CommandFactory.getCommand("write").execute("abc.txt", "shashank test content1");
		Thread.sleep(2000);
		
		File file = new File(ICommand.filePath + "abc.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("shashank test content1", br.readLine());
		br.close();
		CommandFactory.getCommand("delete").execute("abc.txt", null);
	}
	
	@Test
	public void testWriteContentToFileButItShouldBeOverridingNotAppending() throws Exception{
		CommandFactory.getCommand("write").execute("abc.txt", "shashank test content1");
		Thread.sleep(3000);
		
		CommandFactory.getCommand("write").execute("abc.txt", "shashank test content2");
		Thread.sleep(3000);
		
		File file = new File(ICommand.filePath + "abc.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("shashank test content2", br.readLine());
		br.close();
		
		
		CommandFactory.getCommand("delete").execute("abc.txt", null);
	}

}

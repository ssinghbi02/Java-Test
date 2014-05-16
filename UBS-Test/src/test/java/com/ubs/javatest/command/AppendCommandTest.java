package com.ubs.javatest.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import com.ubs.javatest.factory.CommandFactory;

public class AppendCommandTest {

	@Test
	public void testAppendContentToFile() throws Exception{
		CommandFactory.getCommand("append").execute("abc.txt", "shashank test content1");
		
		File file = new File(ICommand.filePath + "abc.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("shashank test content1", br.readLine());
		br.close();
		CommandFactory.getCommand("delete").execute("abc.txt", null);
	}

	@Test
	public void testAppendContentToFileIsNotWritingToNewFileForNewRequest() throws Exception{
		CommandFactory.getCommand("append").execute("abc.txt", "shashank test content1");
		
//		File file = new File(ICommand.filePath + "abc.txt");
//		FileReader fr = new FileReader(file.getAbsoluteFile());
//		BufferedReader br = new BufferedReader(fr);
//		Assert.assertEquals("shashank test content1", br.readLine());
//		br.close();
		
		CommandFactory.getCommand("append").execute("abc.txt", "+shashank test content2");
		
		File file2 = new File(ICommand.filePath + "abc.txt");
		FileReader fr2 = new FileReader(file2.getAbsoluteFile());
		BufferedReader br2 = new BufferedReader(fr2);
		Assert.assertEquals("shashank test content1+shashank test content2", br2.readLine());
		br2.close();
		CommandFactory.getCommand("delete").execute("abc.txt", null);
	}

}

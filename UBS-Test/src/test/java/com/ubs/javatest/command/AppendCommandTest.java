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
	}

}

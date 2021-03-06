package com.ubs.javatest.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

import com.ubs.javatest.factory.CommandFactory;

public class DeleteCommandTest {

	@Test
	public void testDeleteFile() throws Exception{
		CommandFactory.getCommand("write").execute("delete.txt", "shashank test content1");
		Thread.sleep(2000);
		
		File file = new File(ICommand.filePath + "delete.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("shashank test content1", br.readLine());
		
		CommandFactory.getCommand("delete").execute("delete.txt", null);
		Thread.sleep(2000);
		
		br.close();
		
		Assert.assertFalse(file.exists());
	}
	
	@Test
	public void testDeleteFileIfFileDoesNotExist() throws Exception{

		CommandFactory.getCommand("delete").execute("noexist.txt", null);
		Thread.sleep(2000);
		
		File file = new File(ICommand.filePath + "delete.txt");
		Assert.assertFalse(file.exists());
	}

}

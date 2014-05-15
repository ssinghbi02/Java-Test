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
		
		File file = new File(ICommand.filePath + "delete.txt");
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		Assert.assertEquals("shashank test content1", br.readLine());
		CommandFactory.getCommand("delete").execute("delete.txt", null);
		br.close();
		
		Assert.assertFalse(file.exists());
	}

}

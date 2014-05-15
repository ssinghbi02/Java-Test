package com.ubs.javatest.command;

import java.io.File;


public class DeleteCommand implements ICommand{

	@Override
	public void execute(String fileName,String content) {
			File file = new File(filePath+fileName);
			if (file.exists()) {
				file.delete();
			}
			System.out.println("Done");
	}

}

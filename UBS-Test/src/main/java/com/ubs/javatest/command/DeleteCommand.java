package com.ubs.javatest.command;

import java.io.File;

/**
 * This command is used to remove the email content 
 * from file system
 * @author ssinghbi02
 *
 */
public class DeleteCommand implements ICommand{

	@Override
	public void execute(String fileName,String content) {
			File file = new File(filePath+fileName);
			if (file.exists()) {
				file.delete();
			}
	}

}

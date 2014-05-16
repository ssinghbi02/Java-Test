package com.ubs.javatest.command;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * This command is used to remove the email content 
 * from file system
 * @author ssinghbi02
 *
 */
public class DeleteCommand implements ICommand{

	ExecutorService executor;
	
	public DeleteCommand(ExecutorService executor) {
		super();
		this.executor = executor;
	}
	
	@Override
	public void execute(final String fileName,final String content) {
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				File file = new File(filePath+fileName);
				if (file.exists()) {
					file.delete();
				}
				
			}
		});
		
	}

}

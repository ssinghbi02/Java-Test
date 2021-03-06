package com.ubs.javatest.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * This command is used to write email content 
 * to new file
 * @author ssinghbi02
 *
 */
public class WriteCommand implements ICommand {

	ExecutorService executor;
	
	public WriteCommand(ExecutorService executor) {
		super();
		this.executor = executor;
	}

	@Override
	public void execute(final String fileName,final String content) {
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					File file = new File(filePath+fileName);
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
				} catch (IOException e) {
					//log error e.printStackTrace();
				}
				
			}
		});
		

	}

}

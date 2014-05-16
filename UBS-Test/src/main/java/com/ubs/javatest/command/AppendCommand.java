package com.ubs.javatest.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This command class is used
 * to append the email content to same file
 * @author ssinghbi02
 *
 */
public class AppendCommand implements ICommand {

	@Override
	public void execute(String fileName,String content) {
		try {
			File file = new File(filePath+fileName);
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			
			//log the exception  e.printStackTrace();
		}

	}

}

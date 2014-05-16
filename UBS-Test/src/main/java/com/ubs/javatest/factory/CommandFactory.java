package com.ubs.javatest.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubs.javatest.command.AppendCommand;
import com.ubs.javatest.command.DeleteCommand;
import com.ubs.javatest.command.ICommand;
import com.ubs.javatest.command.WriteCommand;
/**
 * This Factory class used to provide instances 
 * of Write,Append and Delete Command
 * @author ssinghbi02
 *
 */
public class CommandFactory {

	private static ExecutorService executor = Executors.newFixedThreadPool(2);

	public static ICommand getCommand(String type) {
		if(type.equalsIgnoreCase("write"))
		{
			return new WriteCommand(executor);
		}
		if(type.equals("append"))
		{
			return new AppendCommand(executor);
		}
		if(type.equals("delete"))
		{
			return new DeleteCommand(executor);
		}
		throw new IllegalArgumentException("there is no matching command found for given instruction");
	}
}

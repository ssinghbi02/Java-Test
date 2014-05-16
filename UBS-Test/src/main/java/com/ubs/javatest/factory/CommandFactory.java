package com.ubs.javatest.factory;

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

	public static ICommand getCommand(String type) {
		
		if(type.equalsIgnoreCase("write"))
		{
			return new WriteCommand();
		}
		if(type.equals("append"))
		{
			return new AppendCommand();
		}
		if(type.equals("delete"))
		{
			return new DeleteCommand();
		}
		throw new IllegalArgumentException("there is no matching command found for given instruction");
	}
}

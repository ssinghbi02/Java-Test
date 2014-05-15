package com.ubs.javatest.factory;

import junit.framework.Assert;

import org.junit.Test;

import com.ubs.javatest.command.AppendCommand;
import com.ubs.javatest.command.DeleteCommand;
import com.ubs.javatest.command.ICommand;
import com.ubs.javatest.command.WriteCommand;

public class CommandFactoryTest {

	@Test
	public void test() {
		ICommand writeCommand = CommandFactory.getCommand("write");
		ICommand appendCommand = CommandFactory.getCommand("append");
		ICommand deleteCommand = CommandFactory.getCommand("delete");
		
		Assert.assertTrue(writeCommand instanceof WriteCommand);
		Assert.assertTrue(appendCommand instanceof AppendCommand);
		Assert.assertTrue(deleteCommand instanceof DeleteCommand);
	}

}

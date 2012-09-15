/*******************************************************************************
 * 2008-2012 Public Domain
 * Contributors
 * Marco Lopes (marcolopes@netc.pt)
 *******************************************************************************/
package org.dma.utils.eclipse.swt.dialogs;

import org.dma.utils.java.array.MessageList;

public class InformationDialog extends CustomDialog {

	/*
	 * Information
	 */
	public static boolean open(String operation, String message) {
		return open(operation, message, TYPE.INFORMATION);
	}

	public static boolean open(String operation, MessageList list) {
		return open(operation, list.toString(), TYPE.CONFIRMATION);
	}

	public static boolean open(String message) {
		return open(null, message, TYPE.CONFIRMATION);
	}

	public static boolean open(MessageList list){
		return open(null, list.toString(), TYPE.CONFIRMATION);
	}


}
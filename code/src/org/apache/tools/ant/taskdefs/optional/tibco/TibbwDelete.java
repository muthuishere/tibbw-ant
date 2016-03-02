package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;


/**
 * TibbwDelete is an ant task for deleting BusinessWorks applications from a tibco domain.
 * 
 * limitation : the name of the application should be the same as name of the application.
 * 
 * @author Cédric Rochet
 */
public class TibbwDelete extends AbstractTibbwServerTask {

	
    /**
     * Default constructor
     */
    public TibbwDelete() {
        super(".ear"); 
    }
    
	/** 
	 * Create the string which reprensents the command line to execute.
	 * @see org.apache.tools.ant.taskdefs.optional.tibco.AbstractTibbwTask#createCommand(java.io.File)
	 */
	protected String createCommand(File currentFile) {
		String appName = this.subStringBeforeFirst(currentFile.getName(), ".");

        String command = this.bindir.getPath() + File.separator + "AppManage -delete " 
		 	+ "-app " + appName
			+ " -domain " + this.domain + " -user " + this.user + " -pw " + this.password + " -force ";

        return command;
	}
	

}

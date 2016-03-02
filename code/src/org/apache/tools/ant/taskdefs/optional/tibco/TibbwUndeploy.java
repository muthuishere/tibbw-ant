package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;


/**
 * TibbwUndeploy is an ant task for undeploying BusinessWorks applications from a tibco domain.
 * 
 * limitation : the name of the application should be the same as name of the application.
 * 
 * @author Cédric Rochet
 */
public class TibbwUndeploy extends AbstractTibbwServerTask {

	
    /**
     * Default constructor
     */
    public TibbwUndeploy() {
        super(".ear");  
    }
    
	/** 
	 * Create the string which reprensents the command line to execute.
	 * @see org.apache.tools.ant.taskdefs.optional.tibco.AbstractTibbwTask#createCommand(java.io.File)
	 */
	protected String createCommand(File currentFile) {
		String appName = this.subStringBeforeFirst(currentFile.getName(), ".");

        String command = this.bindir.getPath() + File.separator + "AppManage -undeploy " 
		 	+ "-app " + appName + " -force "
			+ " -domain " + this.domain + " -user " + this.user + " -pw " + this.password;

        return command;
	}
	

}

package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;


/**
 * TibbwDeploy is an ant task for deploying BusinessWorks applications to a tibco domain. 
 * 
 * @author Cédric Rochet
 *
 */
public class TibbwDeploy extends AbstractTibbwServerTask {

    /**
     * Default constructor
     */
    public TibbwDeploy() {
        super(".ear"); 
    }

    /**
     * Create the string which reprensents the command line to execute.
     */
    protected String createCommand(File currentFile) {
   	 String fileNameNoExtension = this.subStringBeforeFirst(currentFile.getName(), ".");

        String command = this.bindir.getPath() + File.separator + "AppManage -deploy -ear " + currentFile 
		 	+ " -deployConfig " + this.srcdir.getPath()  + File.separator + fileNameNoExtension + ".xml"
			+ " -domain " + this.domain + " -user " + this.user + " -pw " + this.password + " -force -nostart ";

        return command;
   }    


	
}

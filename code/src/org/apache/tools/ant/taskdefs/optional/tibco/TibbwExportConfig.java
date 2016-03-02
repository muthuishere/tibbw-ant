package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;

/**
 * TibbwExportConfig is an ant task for generating configuration file from Tibco BusinessWorks ears.
 *
 * @author Cedric Rochet
 *
 */
public class TibbwExportConfig extends AbstractTibbwTask {
    //~ Instance fields ============================================================================================================

    // ============================================================================================================
    // ============================================================================================================

    /** the directory where the tibco businessworks ear are built * */
    protected File destdir;

    //~ Constructors ===============================================================================================================

    // ===============================================================================================================
    // ===============================================================================================================

    /**
     * Default constructor
     */
    public TibbwExportConfig() {
        super(".ear");
        this.destdir = null;
    }

    //~ Methods ====================================================================================================================

    /**
     * @param destdir The destdir to set.
     */
    public void setDestdir(File destdir) {
        this.destdir = destdir;
    }

    /**
     * @return Returns the destdir.
     */
    public File getDestdir() {
        return destdir;
    }

    // ====================================================================================================================
    // ====================================================================================================================

    /**
     * Create the string which reprensents the command line to execute.
     * @see org.apache.tools.ant.taskdefs.optional.tibco.AbstractTibbwTask#createCommand(java.io.File)
     */
    protected String createCommand(File currentFile) {
        String fileNameNoExtension = this.subStringBeforeFirst(currentFile.getName(), ".");

        String command = this.bindir.getPath() + File.separator + "AppManage -export "
			+ "-ear " + currentFile 
			+ " -out " + this.getDestdir() + File.separator + fileNameNoExtension + ".xml"
			+ " -max ";

        return command;
    }
    
    
}

package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.FileScanner;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.Commandline;


/**
 * Abstract class for all tibco ant tasks.
 *
 * @author Cédric Rochet
 *
 */
public abstract class AbstractTibbwTask extends MatchingTask {
    //~ Instance fields ============================================================================================================

    /** the directory where the tibco tra buildear tool is located */
    protected File bindir;

    /** the directory where the tibco businessworks source are located * */
    protected File srcdir;

    /** the suffix of the file to search files in the src directory upon which commands are built */
    protected String suffix;

    //~ Constructors ===============================================================================================================

    /**
     * Task Constructor.
      *@param the suffix to search files in the src directory upon which commands are built.
     */
    public AbstractTibbwTask(String suffix ) {
        this.bindir = null;
        this.srcdir = null;
        this.suffix = suffix;
    }

    //~ Methods ====================================================================================================================

    protected abstract String createCommand(File currentFile);

    /**
     * @param bindir The bindir to set.
     */
    public void setBindir(File bindir) {
        this.bindir = bindir;
    }

    /**
     * @return Returns the bindir.
     */
    public File getBindir() {
        return bindir;
    }

    /**
     * @param srcdir The srcdir to set.
     */
    public void setSrcdir(File srcdir) {
        this.srcdir = srcdir;
    }

    /**
     * @return Returns the srcdir.
     */
    public File getSrcdir() {
        return srcdir;
    }

    /**
     * Main method call by ant to run the task.
     * Generic implementation which call the abstact method createCommand to be implemented by subclass.
     */
    public void execute() throws BuildException {
    	String taskName = this.subStringAfterLast(this.getClass().getName(),".");
        log("Starting " + taskName + "...");

        validateParameters();

        // search files
        log("Searching " + this.suffix + " files into " + this.srcdir);
        this.add(new SuffixFileSelector(this.suffix));

        FileScanner scanner = super.getDirectoryScanner(this.srcdir);
        String[] files = scanner.getIncludedFiles();
        log("Found " + files.length + " " + this.suffix + " files ");

        // iterate files and run command
        boolean atLeastOneError = false;
        for (int j = 0; j < files.length; j++) {
            int errorCode = executeImpl(createCommand(new File(this.srcdir + File.separator + files[j])));
            if(errorCode!=0) {
            	atLeastOneError = true;
            }
        }
        if(atLeastOneError) {
        	log("CAUTION : at least one error occured during execution of "+taskName);
        } else {
        	log("Completed " + taskName +" successfully.");	        	
        }
        
    }

    /**
     * Execute the external command  
     * @param command the external command to execute.
     */
    protected int executeImpl(String command) {
        log(command);

        Commandline commandLine = new Commandline(command);
        Execute exec = new Execute();
        exec.setCommandline(commandLine.getCommandline());
        exec.setWorkingDirectory(this.bindir);
        int errorCode=-1;
        try {
            errorCode = exec.execute();
        } catch (IOException e) {
            throw new BuildException(e);
        }
        return errorCode;
    }

    /**
     * Helper method which extracts the string after the first occurence of the token.
     * @return the string after the first occurence of the token.
     */
    protected String subStringAfterFirst(String s, String token) {
        return s.substring(s.indexOf(token));
    }
    /**
     * Helper method which extracts the string after the last occurence of the token.
     * @return the string after the last occurence of the token.
     */
    protected String subStringAfterLast(String s, String token) {
        return s.substring(s.lastIndexOf(token)+1);
    }
    /**
     * Helper method which extracts the string before the first occurence of the token.
     * @return the string before the first occurence of the token.
     */
    protected String subStringBeforeFirst(String s, String token) {
        return s.substring(0, s.indexOf(token));
    }
    /**
     * Helper method which extracts the string before the last occurence of the token.
     * @return the string before the last occurence of the token.
     */
    protected String subStringBeforeLast(String s, String token) {
        return s.substring(0, s.lastIndexOf(token));
    }

    /**
     * Validates the parameters to be set before running the task.
     * @throws BuildException if at least one parameter has not been set.
     */
    protected void validateParameters() throws BuildException {
        if ((this.srcdir == null) || (this.bindir == null)) {
            throw new BuildException(
                "One of the following mandatory attributes have not been set into the task : srcdir and bindir.");
        } else {
            log("srcdir=" + this.srcdir);
            log("bindir=" + this.bindir);
        }
    }
}

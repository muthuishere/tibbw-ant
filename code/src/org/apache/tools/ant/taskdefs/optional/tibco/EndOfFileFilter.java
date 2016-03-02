package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;
import java.io.FilenameFilter;

/**
 * EndOfFileFilter is a generic FilenameFilter to allow file with a specific file extension.
 * 
 * @author Cédric Rochet
 *
 */
public class EndOfFileFilter implements FilenameFilter {

	/** the file extension */
	private String pattern = null;
	
	/**
	 * Constructor.
	 * @param pattern the allowed file extension
	 */
	public EndOfFileFilter(String pattern){
		this.pattern = pattern;
	}
	
	/** 
	 * Returns true if the specified file matches the allowed file extension.
	 * 	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File dir, String name) {		
			return name.endsWith(this.pattern); 
	}

}

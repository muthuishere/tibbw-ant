package org.apache.tools.ant.taskdefs.optional.tibco;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.selectors.FileSelector;

/**
 * SuffixFileSelector is a generic FileSelector to allow files with a specific file extension.
 * 
 * @author Cédric Rochet
 *
 */
public class SuffixFileSelector implements FileSelector {
	/** the file extension */
	private String pattern = null;
	
	/**
	 * Constructor.
	 * @param pattern the allowed file extension
	 */
	public SuffixFileSelector(String pattern){
		this.pattern = pattern;
	}
	
	/** 
	 * Returns true if the specified file matches the allowed file extension.
	 * @see org.apache.tools.ant.types.selectors.FileSelector#isSelected(java.io.File, java.lang.String, java.io.File)
	 */
	public boolean isSelected(File basedir, String filename, File file) throws BuildException { 
		return filename.endsWith(this.pattern); 
	}

}

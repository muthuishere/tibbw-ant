package org.apache.tools.ant.taskdefs.optional.tibco;

import org.apache.tools.ant.BuildException;

/**
 * Abstract class for tibco ant tasks which requires to connect to a tibco
 * domain.
 * 
 * @author Cédric Rochet
 *  
 */
public abstract class AbstractTibbwServerTask extends AbstractTibbwTask {
	//~ Instance fields
	// ============================================================================================================

	/** the domain where the tibco businessworks ear should be deployed * */
	protected String domain;

	/** the password to connect to the domain * */
	protected String password;

	/** the user to connect to the domain * */
	protected String user;

	//~ Constructors
	// ===============================================================================================================

	/**
	 * Constructor.
	 * @param the suffix to search files in the src directory upon which commands are built.
	 */
	public AbstractTibbwServerTask(String suffix) {
		super(suffix);
		this.user = null;
		this.domain = null;
		this.password = null;
	}

	//~ Methods
	// ====================================================================================================================

	/**
	 * @param domain
	 *            The domain to set.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return Returns the domain.
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param user
	 *            The user to set.
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return Returns the user.
	 */
	public String getUser() {
		return user;
	}

    /**
     * Validates the parameters to be set before running the task.
     * @throws BuildException if at least one parameter has not been set.
     */
	protected void validateParameters() throws BuildException {
		super.validateParameters();

		if ((this.domain == null) || (this.user == null)
				|| (this.password == null)) {
			throw new BuildException(
					"One of the following mandatory attributes have not been set into the task : domain, user, password");
		} else {
			log("domain=" + this.domain);
			log("user=" + this.user);
			log("password=" + this.password);
		}
	}
}

package models;

import java.util.Map;

public class UserModel {

	private String user;
	private String pass;

	// get
	public String getUser() {
		user = setEnvVars("SERVICE_USER");
		return user;
	}

	public String getPass() {
		user = setEnvVars("SERVICE_PASS");
		return pass;
	}

	public String setEnvVars(String envVar) {
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			if (envVar.equals(envName)) {
				return env.get(envName);
			}
		}
		return "";
	}

}

package utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.splunk.Args;
import com.splunk.HttpService;
import com.splunk.Job;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;

import models.UserModel;

public class SplunkHandler {

	private String host = "";

	public Service conn() throws InterruptedException {
		Service service = null;
		UserModel user = new UserModel();
		int attempts = 0;
		int maxAttempts = 2;
		if (service == null) {
			Map<String, Object> connectArgs = new HashMap<String, Object>();
			connectArgs.put("host", host);
			connectArgs.put("username", user.getUser());
			connectArgs.put("password", user.getPass());
			connectArgs.put("port", 8089);
			connectArgs.put("scheme", "https");
			try {
				HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
				service = Service.connect(connectArgs);
			} catch (Exception e) {
				if (attempts < maxAttempts) {
					Thread.sleep(10000);
					attempts++;
					conn();
				} else {
					System.out.println("Terminated connection attempts");
				}
			}
		}
		return service;
	}

	public String search(String earliestTime, String latestTime, String index) throws InterruptedException {
		String outSearch = "";
		Service service = conn();
		Args query = new Args();
		query.put("earliest_time", earliestTime);
		query.put("latest_time", latestTime);

		Job job = service.getJobs().create("search " + index);
		while (!job.isDone()) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Error search:" + e);
			}

			try {
				Args output = new Args();
				output.put("output_mode", "json");
				InputStream stream = job.getResults(output);
				byte[] buffer = new byte[8192];
				while (stream.read(buffer) != -1) {
					outSearch = new String(buffer);
				}
			} catch (Exception e) {
				System.out.println("Error out:" + e);
			}
		}
		return outSearch;
	}

}

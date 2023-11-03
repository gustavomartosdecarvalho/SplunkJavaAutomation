package searchs;

import utils.SplunkHandler;

public class ServiceAbcSearch {

	SplunkHandler splunk;

	public String searchLastIndexAbc() throws InterruptedException {
		splunk = new SplunkHandler();
		return splunk.search("-4h@h", "now", "index=raw_abc | head 1");
	}

	public String searchIndexAbcLastFourHours() throws InterruptedException {
		splunk = new SplunkHandler();
		return splunk.search("-4h@h", "now", "index=raw_abc");
	}

	public String searchIndexAbcByStatus(String status) throws InterruptedException {
		splunk = new SplunkHandler();
		return splunk.search("-4h@h", "now", "index=raw_abc \"" + status + "\" | head 1");
	}
}

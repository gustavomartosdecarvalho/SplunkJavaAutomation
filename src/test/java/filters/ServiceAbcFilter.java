package filters;

public class ServiceAbcFilter {

	private String serviceAbcResp;

	// set
	public void setSearchServiceAbc(String serviceAbcResp) {
		this.serviceAbcResp = serviceAbcResp;
	}

	// get

	public boolean isHTTPVerbPOST() {
		return serviceAbcResp.contains("POST");
	}

	public String getOS() {
		return subStringContent(serviceAbcResp, "NOME=", ",");
	}

	public String getUri() {
		return subStringContent(serviceAbcResp, "POST", " 192");
	}

	public String subStringContent(String search, String beginSt, String endSt) {
		String contentSub = search.substring(search.indexOf(beginSt));
		return contentSub.substring(beginSt.length() + 1, contentSub.indexOf(endSt));
	}
}

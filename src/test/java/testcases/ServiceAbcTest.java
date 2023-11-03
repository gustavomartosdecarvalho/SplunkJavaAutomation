package testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import filters.ServiceAbcFilter;
import searchs.ServiceAbcSearch;

public class ServiceAbcTest {

	private static ServiceAbcFilter serviceAbcFilter;

	@BeforeAll
	public static void setUp() throws InterruptedException {
		ServiceAbcSearch serviceAbcSearch = new ServiceAbcSearch();
		serviceAbcFilter = new ServiceAbcFilter();
		String serviceAbcResp = serviceAbcSearch.searchIndexAbcByStatus("PROCESS COMPLETED SUCCESSFULLY");
		serviceAbcFilter.setSearchServiceAbc(serviceAbcResp);
	}

	@Test
	public void verifyUriNameIsAbcReturnTrue() {
		assertEquals("/abc", serviceAbcFilter.getUri());
	}

	@Test
	public void verifyOsisCentOSReturnTrue() {
		assertEquals("CentOS", serviceAbcFilter.getOS());
	}

	@Test
	public void verifyHTTPVerbIsPOSTReturnTrue() {
		assertTrue(serviceAbcFilter.isHTTPVerbPOST());
	}

}

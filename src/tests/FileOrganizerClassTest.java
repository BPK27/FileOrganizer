package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import organizer.FileOrganizerHelper;

public class FileOrganizerClassTest {
	
	FileOrganizerHelper helper = new FileOrganizerHelper();
	@Before
	public void setUp() throws Exception {
		helper.getFolderPaths();
	}

	@Test
	public void processFiles() {
		fail("Not yet implemented");
	}

}

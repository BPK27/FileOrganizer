package tests;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import organizer.FileOrganizerHelper;

public class FileOrganizerClassTest {
	
	FileOrganizerHelper helper = new FileOrganizerHelper();
	private List<Path> folderPaths = new ArrayList<Path>();

	@Before
	public void setUp() throws Exception {
		folderPaths = helper.getFolderPaths();
	}

	@Test
	public void processFiles() {
		fail("Not yet implemented");
	}

}

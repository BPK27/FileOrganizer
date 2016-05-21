package tests;

import org.junit.Before;
import org.junit.Test;

import organizer.FileOrganizerHelper;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert; 


public class FileOrganizerHelperTest {
	FileOrganizerHelper helper = new FileOrganizerHelper();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowParser() {
		String episode = "Arrow - 4x14 - Code of Silence";
		String result = helper.showParser(episode);
		String expected = "Arrow";
		
		Assert.assertEquals(result, expected);
	}

	@Test
	public void testNewFolderName() {
		String folder = "Arrow - Updated 08.05.16";
		String result = helper.newFolderName(folder, "10.05.16");
	    String expected = "Arrow - Updated 10.05.16";
		
		Assert.assertEquals(result, expected);
	}

	@Test
	public void testPrepareFolderList() {
		Path dir = Paths.get("C:\\Users\\blayn\\Desktop\\Test Folder\\Arrow - Updated 08.05.16");
		String result = helper.prepareFolderList(dir);
	    String expected = "Arrow";
	    
	    
		Assert.assertEquals(result, expected);
	}
	
	public void testReadDirectory(){
		String episode = "C:\\Users\\blayn\\Desktop\\Test Folder\\Arrow - Updated 08.05.16\\Arrow - 4x14 - Code of Silence.mkv";
		String folder = "C:\\Users\\blayn\\Desktop\\Test Folder\\Arrow - Updated 08.05.16";
		
		helper.readDirectory(episode);
		helper.readDirectory(folder);
		
		
	}

}

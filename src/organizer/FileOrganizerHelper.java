package organizer;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class FileOrganizerHelper {

	private List<Path> folderPaths = new ArrayList<Path>();
	private List<Path> episodePaths = new ArrayList<Path>();

	public List<Path> getFolderPaths() {
		return folderPaths;
	}

	public List<Path> getEpisodePaths() {
		return episodePaths;
	}

	public String showParser(String episode) {
		String showName;
//		 Pattern p = Pattern.compile(" - (\\d*)x(\\d*).*");
//		 String[] m = p.split(episode);
		
		 String[] m = episode.split(" - ");
		 
		 if (m.length == 0){
			 showName = episode;
		 }
		 else{
			 showName = m[0];
		 }
		 
		 return showName;
	}
	
	public void readDirectory(String path){
		Path dir = Paths.get(path);
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		        if (Files.isDirectory(file)){
		        	folderPaths.add(file);
		        }
		        else if (checkShowFormat(file)){
		        	episodePaths.add(file);
		        }
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
	}

	private String folderParser(String folderName) {
		 String showName;
		 Pattern p = Pattern.compile(" - Updated (\\d*.)*");
		 String[] m = p.split(folderName);
		 
		 if (m.length == 0){
			 showName = folderName;
		 }
		 else{
			 showName = m[0];
		 }
		 
		 return showName;
	}

	public String newFolderName(String folder, String date) {
		String nameStruct = " - Updated ";
		String name = folderParser(folder);
		
		return name + nameStruct + date;
	}

//	public List<String> prepareEpisodeList(List<Path> episodePaths) {
//		List<String> cleanedList = new ArrayList<String>();
//		
//		for (Path episode : episodePaths){
//			cleanedList.add(showParser(episode.getFileName().toString()));
//		}
//		return cleanedList;
//	}

	public String prepareFolderList(Path folderPath) {
		
		return folderParser(folderPath.getFileName().toString());
	}
	
	private boolean checkShowFormat(Path file){
		return file.getFileName().toString().contains(" - ");
	}

}

package organizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileOrganizerClass {
	private List<Path> folderPaths = new ArrayList<Path>();
	private List<Path> episodePaths = new ArrayList<Path>();
	private String rootFolder;
	private List<String> folderNames = new ArrayList<String>();
	
	FileOrganizerHelper helper = new FileOrganizerHelper();
	
	public FileOrganizerClass(String path){
		setRoot(path);
		helper.readDirectory(path);
		folderPaths = helper.getFolderPaths();
		episodePaths = helper.getEpisodePaths();
	}

	public void processFiles(){
		for(Path folder:folderPaths){
			addToFoldernames(folder);
		}
		
			for (Path episode:episodePaths){
				String show = helper.showParser(episode.getFileName().toString()) ;
				int a = Arrays.binarySearch(folderNames.toArray(), show);
				
				//folder is found
				if (a >= 0){
				Path folder = Paths.get(folderPaths.get(a).toString());
				moveFile(episode, folder);
				reorderFolderpaths(a, renameFolder(folder.toString()));
				}
				//folder not found
				else{
					Path folder = makeFolder(getRoot() + "\\" + show.toString());
					//ensure folder is made
					if(folder.endsWith(show)){
						addToFoldernames(folder);
						moveFile(episode,folder);
						addToFolderpaths(folder);
					}
					else{
						System.out.println("ERROR");
					}
				}
			}
	}
	
	private Path makeFolder(String showName) {
		File dir = new File(showName);
		dir.mkdir();
	    
		return dir.toPath();
	}

	private void moveFile(Path show, Path folder){
		try {
			FileUtils.moveFileToDirectory(show.toFile(), folder.toFile(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Path renameFolder(String folder) {
		String newDirName = helper.newFolderName(folder, getDate());
		File dir = new File(folder);
		File newDir = new File(newDirName);
				try {
					Files.move(dir.toPath(), newDir.toPath());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return newDir.toPath();
		}
	
	private String getDate() {
		DateFormat df = new SimpleDateFormat("dd.MM.yy");
		Calendar calobj = Calendar.getInstance();
		
		return df.format(calobj.getTime());
	}
	
	private void setRoot(String path) {
		rootFolder = path;
	}
	
	private String getRoot(){
		return rootFolder;
	}
	
	private void addToFoldernames(Path folder){
		String newName = helper.prepareFolderList(folder);
		folderNames.add(newName);
		folderNames.sort(null);
	}

	private void reorderFolderpaths(int a, Path folder){
		folderPaths.add(a, folder);
		folderPaths.remove(a+1);
	}
	
	private void addToFolderpaths(Path folder){
		folderPaths.add(renameFolder(folder.toString()));
		folderPaths.sort(null);
	}

}

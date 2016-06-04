package organizer;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		
		JFileChooser jf = new JFileChooser();
	    jf.setDialogTitle("Choose directory to organise");
	    jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    jf.showOpenDialog(jf);
	    String f = jf.getSelectedFile().getAbsolutePath();
	      
		FileOrganizerClass fo = new FileOrganizerClass(f);

		fo.processFiles();
	}

}

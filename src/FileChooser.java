import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
 
public class FileChooser {
	private static String filePath;
	private static String fileName;
	public FileChooser() throws IOException
	{
		JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		//Multiple selection
		fc.setMultiSelectionEnabled(false);
		//selection mode
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//whether show hiding files
		fc.setFileHidingEnabled(true);
		fc.setAcceptAllFileFilterUsed(false);
		//selector
		fc.setFileFilter(new MyFileFilter("java"));
		fc.setFileFilter(new MyFileFilter("csv"));
		
		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			
			File file = fc.getSelectedFile();
			filePath = file.getPath();
			fileName = file.getName();
			/*BufferedReader bufferedReader = null;
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        System.out.println(line);
		    }*/
		
			//fc.getSelectedFiles()
		}
	}
	public String getPath() {
		return filePath;
	}
	public String getName() {
		return fileName;
	}
	
}

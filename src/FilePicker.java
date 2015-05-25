import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FilePicker {
	private static String filePath;
	private static String fileName;
	final FileChooser fileChooser = new FileChooser();

	public FilePicker() throws IOException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null) {					
					if (file != null) {
						MainWindow.L_input_file_name_card1.setText(file.getName()
								+ " loaded");
						MainWindow.L_input_file_name_card2.setText(file.getName()
								+ " loaded");
						MainWindow.L_input_file_name_card3.setText(file.getName()
								+ " loaded");
						MainWindow.compute_listsener_module1.setFileName(file
								.getName());
						MainWindow.compute_listsener_module1.setFilePath(file
								.getPath());
						MainWindow.compute_listsener_module2.setFileName(file
								.getName());
						MainWindow.compute_listsener_module2.setFilePath(file
								.getPath());
						MainWindow.compute_listsener_module3.setFileName(file
								.getName());
						MainWindow.compute_listsener_module3.setFilePath(file
								.getPath());
					}
				}
			}
		});
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Select Trade Data");
		fileChooser.setInitialDirectory(new File(System
				.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv"));
	}

	public String getPath() {
		return filePath;
	}

	public String getName() {
		return fileName;
	}

}

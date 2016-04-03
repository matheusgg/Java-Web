import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadController {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload(FileUploadEvent ev) {
		file = ev.getFile();
		System.out.println("Teste " + file.getFileName());
	}

	public String action() {
		System.out.println("Funfo");		
		if (this.file == null) {
			System.out.println("nulo");
		} else {
			System.out.println(file.getFileName());
		}
		return " ";
	}
}
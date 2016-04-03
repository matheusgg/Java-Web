package upload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class UploadMB implements Serializable {

	private static final long serialVersionUID = 8425254752182442517L;

	private List<String> files;

	@PostConstruct
	public void init() {
		this.files = new ArrayList<>();
	}

	public void uploadFile(FileUploadEvent event) {
		this.files.add(event.getFile().getFileName());
	}

	/**
	 * @return the files
	 */
	public List<String> getFiles() {
		return files;
	}

	/**
	 * @param files
	 *            the files to set
	 */
	public void setFiles(List<String> files) {
		this.files = files;
	}

}

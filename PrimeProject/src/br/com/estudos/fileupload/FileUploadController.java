package br.com.estudos.fileupload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.column.Column;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadController {

	private UploadedFile file;
	private HtmlForm form;
	private Column coluna;
	private HtmlCommandButton delete;

	public void upload(FileUploadEvent ev) {
		file = ev.getFile();
		System.out.println("Teste " + file.getFileName());
		if (file != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"O currículo foi anexado com sucesso!",
							"O currículo foi anexado com sucesso!"));
		}

		HtmlOutputLabel out = new HtmlOutputLabel();
		out.setValue(file.getFileName());

		delete = new HtmlCommandButton();
		delete.setValue("Remover");
		delete.setId("id");
		delete.setActionExpression(FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createMethodExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{fileUploadController.action}",
						FileUploadController.class, new Class[] {}));

		coluna.getChildren().add(out);
		coluna.getChildren().add(delete);
	}

	public String action() {
		try {
			InputStream curriculoIn = file.getInputstream();
			File caminhoAnexo = new File(
					"C:\\Users\\Matheus\\Downloads\\file.pdf");

			OutputStream output = new FileOutputStream(caminhoAnexo);
			byte[] buffer = new byte[1024];
			int tamanho;
			while ((tamanho = curriculoIn.read(buffer)) > 0) {
				output.write(buffer, 0, tamanho);
			}
			curriculoIn.close();
			output.close();

		} catch (IOException ex) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Erro ao anexar o arquivo.\nPor favor, tente novamente",
									""));
			ex.printStackTrace();
		}

		System.out.println("Funfo");

		if (this.file == null) {
			System.out.println("nulo");
		} else {
			System.out.println(file.getFileName());
		}
		return " ";
	}

	public void executaEvento(AjaxBehaviorEvent event) {
		System.out.println("Funfo");
	}

	public void teste(ValueChangeEvent ev) {
		System.out.println("Funfo");
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public HtmlForm getForm() {
		return form;
	}

	public void setForm(HtmlForm form) {
		this.form = form;
	}

	public Column getColuna() {
		return coluna;
	}

	public void setColuna(Column coluna) {
		this.coluna = coluna;
	}

	public HtmlCommandButton getDelete() {
		return delete;
	}

	public void setDelete(HtmlCommandButton delete) {
		this.delete = delete;
	}
}
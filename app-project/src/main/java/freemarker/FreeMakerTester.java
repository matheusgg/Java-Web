package freemarker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMakerTester {

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\mggoes\\Documents\\Projetos\\app-project\\src\\main\\webapp\\WEB-INF\\templates\\freemaker"));

		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("title", "Título de Teste");
		dataModel.put("subtitle", "Subtitulo de Teste");
		dataModel.put("text", "TextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTextoTexto");

		Template template = cfg.getTemplate("simpleTemplate.xhtml");

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Writer writer = new OutputStreamWriter(os);
		template.process(dataModel, writer);
		writer.close();

		File html = new File("C:\\Users\\mggoes\\Downloads\\template.xhtml");
		FileOutputStream fileOutputStream = new FileOutputStream(html);
		fileOutputStream.write(os.toByteArray());
		fileOutputStream.close();
	}

}

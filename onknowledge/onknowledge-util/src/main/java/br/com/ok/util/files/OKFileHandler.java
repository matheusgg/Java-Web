package br.com.ok.util.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.security.OKPasswordHandler;

/**
 * The Class OKFileHandler.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public final class OKFileHandler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(OKPasswordHandler.class.getName());

	/**
	 * Instantiates a new OK file handler.
	 */
	private OKFileHandler() {

	}

	/**
	 * Save file.
	 *
	 * @param fileData
	 *            the data
	 * @param path
	 *            the path
	 * @return true, if successful
	 */
	public static boolean saveFile(byte[] fileData, String path) {
		try (OutputStream outputStream = new FileOutputStream(path)) {
			outputStream.write(fileData);
			return true;
		} catch (IOException e) {
			OKFileHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Delete file.
	 *
	 * @param path
	 *            the path
	 * @return true, if successful
	 */
	public static boolean deleteFile(String path) {
		return new File(path).delete();
	}

	/**
	 * Load file.
	 *
	 * @param path
	 *            the path
	 * @return the input stream
	 */
	public static InputStream loadFileToInputStream(String path) {
		try {
			return new FileInputStream(path);
		} catch (Exception e) {
			OKFileHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Load file to byte array.
	 *
	 * @param path
	 *            the path
	 * @return the byte[]
	 */
	public static byte[] loadFileToByteArray(String path) {
		byte[] fileData = null;
		try (InputStream inputStream = OKFileHandler.loadFileToInputStream(path)) {
			fileData = new byte[inputStream.available()];
			inputStream.read(fileData);
		} catch (Exception e) {
			OKFileHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return fileData;
	}

	/**
	 * Extrai o nome e a extensão do arquivo a partir de um objeto Part. Retorna
	 * um array contendo as informações nas seguintes posições: <br>
	 * [0] - Nome <br>
	 * [1] - Extensão <br>
	 *
	 * @param part
	 *            the part
	 * @return the string[]
	 */
	public static String[] extractInfoFromPartHeader(Part part) {
		String filePath = part.getHeader(OKConstants.CONTENT_DISPOSITION_KEY).split(OKConstants.PONTO_VIRGULA)[2];
		filePath = filePath.split(OKConstants.SINAL_ATRIBUICAO)[1].replaceAll(OKConstants.ASPAS, OKConstants.STRING_VAZIA);
		return OKFileHandler.extractInfoFromFileName(filePath);
	}

	/**
	 * Extrai o nome e a extensão do arquivo a partir do nome informado. Retorna
	 * um array contendo as informações nas seguintes posições: <br>
	 * [0] - Nome <br>
	 * [1] - Extensão <br>
	 *
	 * @param fileName
	 *            the file name
	 * @return the string[]
	 */
	public static String[] extractInfoFromFileName(String fileName) {
		String name = FilenameUtils.getBaseName(fileName);
		String extension = FilenameUtils.getExtension(fileName);
		return new String[] { name, extension };
	}

	/**
	 * Read data from input stream.
	 *
	 * @param stream
	 *            the stream
	 * @return the byte[]
	 */
	public static byte[] readDataFromInputStream(InputStream stream) {
		byte[] fileData = null;
		try (InputStream inputStream = stream) {
			fileData = new byte[inputStream.available()];
			inputStream.read(fileData);
		} catch (IOException e) {
			OKFileHandler.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return fileData;
	}

	/**
	 * Gets the default file path.
	 *
	 * @return the default file path
	 */
	public static String getDefaultFilePath() {
		return OKFileHandler.getExternalContext().getInitParameter("br.com.ok.defaultFilePath");
	}

	/**
	 * Gets the external context.
	 *
	 * @return the external context
	 */
	private static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
}

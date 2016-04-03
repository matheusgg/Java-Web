package br.com.ok.model.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import lombok.Getter;
import br.com.ok.model.Anexo;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.files.OKFileHandler;

/**
 * The Class ProfilePicture.
 */
public class ProfilePicture implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5634466701026772415L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ProfilePicture.class.getName());

	/** The Constant VALID_EXTENSIONS. */
	private static final List<String> VALID_EXTENSIONS = Arrays.asList(OKConstants.EXTENSAO_JPG, OKConstants.EXTENSAO_PNG, OKConstants.EXTENSAO_BMP);

	/** The original picture data. */
	private byte[] originalPictureData;

	/** The encoded data. */
	@Getter
	private String encodedData;

	/** The name. */
	@Getter
	private String name;

	/** The extension. */
	@Getter
	private String extension;

	/** The path. */
	@Getter
	private String path;

	/**
	 * Instantiates a new profile picture.
	 *
	 * @param part
	 *            the part
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public ProfilePicture(Part part) throws IllegalArgumentException {
		this.createPictureFromPart(part);
	}

	/**
	 * Instantiates a new profile picture.
	 *
	 * @param anexo
	 *            the anexo
	 */
	public ProfilePicture(Anexo anexo) {
		this.createPictureFromAnexo(anexo);
	}

	/**
	 * Instantiates a new profile picture.
	 *
	 * @param inputStream
	 *            the input stream
	 * @param name
	 *            the name
	 * @param extension
	 *            the extension
	 * @param path
	 *            the path
	 */
	public ProfilePicture(InputStream inputStream, String name, String extension, String path) {
		this.name = name;
		this.path = path;
		this.extension = extension;
		this.verifyProfilePictureExtension();
		this.createPictureFromInputStraem(inputStream);
	}

	/**
	 * Creates the picture from part.
	 *
	 * @param part
	 *            the part
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private void createPictureFromPart(Part part) throws IllegalArgumentException {
		this.extractPictureInfoFromPart(part);
		try {
			this.readDataFromInputStream(part.getInputStream());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Creates the picture from anexo.
	 *
	 * @param anexo
	 *            the anexo
	 */
	private void createPictureFromAnexo(Anexo anexo) throws IllegalArgumentException {
		this.extractPictureInfoFromAnexo(anexo);
		try {
			this.readDataFromInputStream(new FileInputStream(anexo.getCaminhoArquivo()));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Creates the picture from input straem.
	 *
	 * @param inputStream
	 *            the input stream
	 */
	private void createPictureFromInputStraem(InputStream inputStream) throws IllegalArgumentException {
		try {
			this.readDataFromInputStream(inputStream);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Read data from input stream.
	 *
	 * @param stream
	 *            the stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void readDataFromInputStream(InputStream stream) throws IOException {
		this.originalPictureData = OKFileHandler.readDataFromInputStream(stream);
		this.encodedData = OKConstants.IMG_BASE64_PREFIX + Base64.getEncoder().encodeToString(this.originalPictureData);
	}

	/**
	 * Extract picture info from part.
	 *
	 * @param part
	 *            the part
	 */
	private void extractPictureInfoFromPart(Part part) {
		String[] fileInfo = OKFileHandler.extractInfoFromFileName(part.getSubmittedFileName());
		this.name = fileInfo[0];
		this.extension = fileInfo[1];
		this.verifyProfilePictureExtension();
	}

	/**
	 * Extract picture info from anexo.
	 *
	 * @param anexo
	 *            the anexo
	 */
	private void extractPictureInfoFromAnexo(Anexo anexo) {
		this.name = anexo.getNomeArquivo();
		this.path = anexo.getCaminhoArquivo();
		this.extension = anexo.getExtensaoArquivo();
		this.verifyProfilePictureExtension();
	}

	/**
	 * Verify profile picture extension.
	 *
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private void verifyProfilePictureExtension() throws IllegalArgumentException {
		if (!ProfilePicture.VALID_EXTENSIONS.contains(this.extension)) {
			throw new IllegalArgumentException("{msg.erro.extensao.invalida}");
		}
	}

	/**
	 * Gets the sub picture.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the sub picture
	 */
	public ProfilePicture getSubPicture(int x, int y, int width, int height) {
		ProfilePicture profilePicture = null;
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(this.originalPictureData); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			BufferedImage subPicture = ImageIO.read(inputStream).getSubimage(x, y, width, height);
			ImageIO.write(subPicture, this.extension, outputStream);
			profilePicture = new ProfilePicture(new ByteArrayInputStream(outputStream.toByteArray()), this.name, this.extension, this.path);

		} catch (Exception e) {
			ProfilePicture.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return profilePicture;
	}

	/**
	 * Save picture.
	 *
	 * @param basePath
	 *            the base path
	 * @param fileName
	 *            the file name
	 * @param extension
	 *            the extension
	 * @return the profile picture
	 */
	public ProfilePicture savePicture(String path) {
		OKFileHandler.saveFile(this.originalPictureData, path);
		return new ProfilePicture(new ByteArrayInputStream(this.originalPictureData), this.name, this.extension, path);
	}

}

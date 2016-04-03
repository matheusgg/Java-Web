package br.com.ok.model.enums;

/**
 * The Enum CorEvento.
 */
public enum CorEvento {

	/** The darken. */
	DARKEN("#404040"),

	/** The blue. */
	BLUE("#57889c"),

	/** The orange. */
	ORANGE("#D6A848"),

	/** The green light. */
	GREEN_LIGHT("#89AD45"),

	/** The blue light. */
	BLUE_LIGHT("#92a2a8"),

	/** The red. */
	RED("#a90329");

	/** The background. */
	private String background;

	/**
	 * Instantiates a new cor evento.
	 *
	 * @param background
	 *            the background
	 */
	private CorEvento(String background) {
		this.background = background;
	}

	/**
	 * Gets the background.
	 *
	 * @return the background
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * Sets the background.
	 *
	 * @param background
	 *            the new background
	 */
	public void setBackground(String background) {
		this.background = background;
	}

}

package multipart;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = "/MultipartServlet", name = "MultipartServlet")
// @MultipartConfig(location = "", maxFileSize = -1, maxRequestSize = -1,
// fileSizeThreshold = 0)
public class MultipartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5797195844483441342L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Arquivos Carregados:");

		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			String[] content = part.getHeader("content-disposition").split(";");
			String fileName = content[content.length - 1].split("=")[1];
			resp.getWriter().println(fileName);
		}
	}

}

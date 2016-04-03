import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculaMedia
 */
@WebServlet("/CalculaMedia")
public class CalculaMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculaMedia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float n1, n2, n3, n4, media;
		n1 = Float.parseFloat(request.getParameter("n1").replace(",", "."));
		n2 = Float.parseFloat(request.getParameter("n2").replace(",", "."));
		n3 = Float.parseFloat(request.getParameter("n3").replace(",", "."));
		n4 = Float.parseFloat(request.getParameter("n4").replace(",", "."));
		
		media = (n1+n2+n3+n4)/4;		
		
		request.setAttribute("media", String.valueOf(media));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

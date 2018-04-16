package Control;

import DAO.ManagerDAO;
import DAO.ProductDAO;
import DAO.SectionDAO;
import DAO.ShopDAO;
import DAOImpl.ManagerDAOImpl;
import DAOImpl.ProductDAOImpl;
import DAOImpl.SectionDAOImpl;
import DAOImpl.ShopDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"", "/index.html"})
public class IndexServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ShopDAO shopDAO;
    private ManagerDAO managerDAO;
    private SectionDAO sectionDAO;
    private ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServletController() {
        super();
        shopDAO = new ShopDAOImpl();
        managerDAO = new ManagerDAOImpl();
        sectionDAO = new SectionDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("shops", shopDAO.getAll());
        request.setAttribute("managers", managerDAO.getAll());
        request.setAttribute("sections", sectionDAO.getAll());
        request.setAttribute("products", productDAO.getAll());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}


package Control;
import DAO.ManagerDAO;
import DAOImpl.ManagerDAOImpl;
import Model.DbConnection;
import Model.Manager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagerServletControl  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ManagerDAO managerDAOIml;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/newManager":
                    showNewForm(request, response);
                    break;
                case "/insertManager":
                    insertManager(request, response);
                    break;
                case "/deleteManager":
                    deleteManager(request, response);
                    break;
                case "/editManager":
                    showEditForm(request, response);
                    break;
                case "/updateManager":
                    updateManger(request, response);
                    break;
                case "/list":
                    listManager(request, response);
                    break;
                case "/list_Shop":
                    request.getRequestDispatcher("/ShopServletControl").forward(request, response);
                    break;
                case "/list_Product":
                    request.getRequestDispatcher("/ProductServletControl").forward(request, response);
                    break;
                case "/list_Section":
                    request.getRequestDispatcher("/SectionServletControl").forward(request, response);

                default:
                    listManager(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listManager(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Manager> listManager = managerDAOIml.GetAll();
        request.getSession().setAttribute("listManager", listManager);
        request.getRequestDispatcher("Manager.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditManager.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Manager manager = managerDAOIml.getManagerId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditManager.jsp");
        request.setAttribute("manager", manager);
        dispatcher.forward(request, response);
    }

    private void insertManager(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");
        String telephone =request.getParameter("telephone");
        Manager newManager = new Manager();
        newManager.setName(name);
        newManager.setSurname(surname);
        newManager.setMiddlename(middlename);
        newManager.setTelephone(telephone);
        managerDAOIml.Insert(newManager);
        response.sendRedirect("list");
    }

    private void updateManger(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_manager"));
        String name = request.getParameter("name_manager");
        String surname = request.getParameter("surname_manager");
        String middlename = request.getParameter("middlename_manager");
        String telephone =request.getParameter("telephone");
        Manager newManager = new Manager();
        newManager.setManagerId(id);
        newManager.setName(name);
        newManager.setSurname(surname);
        newManager.setMiddlename(middlename);
        newManager.setTelephone(telephone);
        managerDAOIml.Update(newManager);
        response.sendRedirect("list");
    }

    private void deleteManager(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id_manager"));
        Manager manager = new Manager();
        manager.setManagerId(id);
        managerDAOIml.Delete(manager);
        response.sendRedirect("list");
    }
}

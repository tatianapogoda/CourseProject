package Control;

import DAO.SectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import DAOImpl.ShopDAOImpl;
import DAOImpl.ManagerDAOImpl;
import Model.Manager;
import Model.Section;
import Model.Shop;

import java.util.List;
import javax.servlet.RequestDispatcher;

public class SectionServletControl  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SectionDAO sectionDAOImpl;
    private ManagerDAOImpl managerDAOImpl;
    private ShopDAOImpl shopDAOImpl;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {

                case "/newSection":
                    showNewForm(request, response);
                    break;
                case "/insertSection":
                    insertSection(request, response);
                    break;
                case "/deleteSection":
                    deleteSection(request, response);
                    break;
                case "/editSection":
                    showEditForm(request, response);
                    break;
                case "/updateSection":
                    updateSection(request, response);
                    break;
                case "/newpageSection":
                    listSection(request, response);
                case "/listtManager":
                    request.getRequestDispatcher("/ManagerServletControl").forward(request, response);
                    break;
                case "/listtProduct":
                    request.getRequestDispatcher("/ProductServletControl").forward(request, response);
                    break;
                case "/listtShop":
                    request.getRequestDispatcher("/ShopServletControl").forward(request, response);
                    break;
                default:
                    listSection(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Section> listSection = sectionDAOImpl.GetAll();
        request.getSession().setAttribute("listSection", listSection);
        request.getRequestDispatcher("Section.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Manager> listManager = managerDAOImpl.GetAll();
        List<Shop> listShop = shopDAOImpl.GetAll();
        request.getSession().setAttribute("listManager", listManager);
        request.getSession().setAttribute("listShop", listShop);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditSection.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Manager> listManager = managerDAOImpl.GetAll();
        List<Shop> listShop = shopDAOImpl.GetAll();
        request.getSession().setAttribute("listManager", listManager);
        request.getSession().setAttribute("listShop", listShop);
        int id = Integer.parseInt(request.getParameter("id"));
        Section section = sectionDAOImpl.getSectionId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditSection.jsp");
        request.setAttribute("section", section);
        dispatcher.forward(request, response);

    }

    private void insertSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String telephone= request.getParameter("telephone");
        int id_shop = Integer.parseInt(request.getParameter("id_shop"));
        int id_manager = Integer.parseInt(request.getParameter("id_manager"));


        Section newSection = new Section();
        newSection.setTitle(title);
        newSection.setTelephone(telephone);
        newSection.setShopId(id_shop);
        newSection.setManagerId(id_manager);

        sectionDAOImpl.Insert(newSection);
        response.sendRedirect("listSection");
    }

    private void updateSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_section"));
        String title = request.getParameter("title_section");
        String telephone= request.getParameter("telephone");
        int id_shop = Integer.parseInt(request.getParameter("id_shop_selection"));
        int id_manager = Integer.parseInt(request.getParameter("id_manager_selection"));


        Section newSection = new Section();
        newSection.setSectionId(id);
        newSection.setTitle(title);
        newSection.setTelephone(telephone);
        newSection.setShopId(id_shop);
        newSection.setManagerId(id_manager);
        sectionDAOImpl.Update(newSection);
        response.sendRedirect("listSection");
    }

    private void deleteSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id_section"));
        Section section = new Section();
        section.setSectionId(id);
        sectionDAOImpl.Delete(section);
        response.sendRedirect("listSection");

    }


}

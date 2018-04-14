package Control;

import DAO.ShopDAO;
import Model.Shop;
import Model.DbConnection;
import DAOImpl.ShopDAOImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShopServletControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShopDAO shopDAOImpl;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newShop":
                    showNewForm(request, response);
                    break;
                case "/insertShop":
                    insertShop(request, response);
                    break;
                case "/deleteShop":
                    deleteShop(request, response);
                    break;
                case "/editShop":
                    showEditForm(request, response);
                    break;
                case "/updateShop":
                    updateShop(request, response);
                    break;
                case "/listt":
                    listShop(request, response);
                    break;
                case "/listManager":
                    request.getRequestDispatcher("/ManagerServletControl").forward(request, response);
                    break;
                case "/listProduct":
                    request.getRequestDispatcher("/ProductServletControl").forward(request, response);
                    break;
                case "/listSection":
                    request.getRequestDispatcher("/SectionServletControl").forward(request, response);

                default:
                    listShop(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listShop(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
       List<Shop> listShop = shopDAOImpl.GetAll();
       request.getSession().setAttribute("listt", listShop);
       request.getRequestDispatcher("Shop.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditShop.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Shop shop = shopDAOImpl.getShopId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditShop.jsp");
        request.setAttribute("shop", shop);
        dispatcher.forward(request, response);

    }

    private void insertShop(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name_director");
        String surname = request.getParameter("surname_director");
        String middlename = request.getParameter("middlename_director");
        String telephone =request.getParameter("telephone");
        String address =request.getParameter("address");

        Shop newShop = new Shop();
        newShop.setDirectorName(name);
        newShop.setDirectorSurname(surname);
        newShop.setDirectorMiddlename(middlename);
        newShop.setTelephone(telephone);
        newShop.setAddress(address);
        shopDAOImpl.Insert(newShop);
        response.sendRedirect("listt");
    }

   private void updateShop(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
       request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_shop"));
        String name = request.getParameter("name_director");
        String surname = request.getParameter("surname_director");
        String middlename = request.getParameter("middlename_director");
        String telephone =request.getParameter("telephone");
        String address =request.getParameter("address");


        Shop newShop = new Shop();
        newShop.setShopId(id);
        newShop.setDirectorName(name);
        newShop.setDirectorSurname(surname);
        newShop.setDirectorMiddlename(middlename);
        newShop.setTelephone(telephone);
        newShop.setAddress(address);
        shopDAOImpl.Update(newShop);
        response.sendRedirect("listt");
    }

    private void deleteShop(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Shop shop = new Shop();
        shop.setShopId(id);
        shopDAOImpl.Delete(shop);
        response.sendRedirect("listt");

    }
    private void pageManager(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        request.getRequestDispatcher("Manager.jsp").forward(request, response);
    }
}
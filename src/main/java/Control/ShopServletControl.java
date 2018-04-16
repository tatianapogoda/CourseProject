package Control;

import DAO.ProductDAO;
import DAO.ShopDAO;
import DAOImpl.ProductDAOImpl;
import DAOImpl.ShopDAOImpl;
import Model.Shop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/category.html")
public class ShopServletControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShopDAO shopDAO;
    private ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServletControl() {
        super();
        shopDAO = new ShopDAOImpl();
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
        String forward = "/index.jsp";

        try{
            List<Shop> shops = shopDAO.getAll();

        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Shop shop = (Shop) session.getAttribute("shop");

        String action = request.getParameter("action");
        if(action == null || action.equals("")) {
            if (shop == null) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            } else {
                update(session, shop, request);
                session.setAttribute("shop", shop);
                response.sendRedirect("shop.html?action=view");
            }
            return;
        }
        switch (action) {
            case "view":
                view(request, response);
                break;
            case "index":
                update(session, shop, request);
                response.sendRedirect("index.html");
                break;
            case "add":
                add(request, response);
                break;
            case "remove":
                remove(request, response);
                break;
            default:
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                break;
        }
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("shop");
        if (shop == null) {
            session.setAttribute("error", "Shop is not exist");
            response.sendRedirect("index.html");
        } else {
            request.getRequestDispatcher("/shop.jsp").forward(request, response);
        }
    }

    private void update(HttpSession session, Shop shop, HttpServletRequest request) {
        List<Shop> shops = shopDAO.getAll();
        for (Shop s : shops) {
            try {
                int newQuantity = Integer.parseInt(
                        request.getParameter(String.valueOf(s.getProduct().getId())));
                if (newQuantity == 0) {
                    items.remove(s);
                } else {
                    s.setQuantity(newQuantity);
                }
            } catch (NumberFormatException ex) {
                session.setAttribute("error", "Please enter numeric quantity!");
                break;
            }
        }
        orderDAO.update(cart);
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.find(id);
            if (product == null) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            } else {
                boolean isExists = false;
                for (Item i : cart.getItems()) {
                    if(i.getProduct().getId() == product.getId()) {
                        isExists = true;
                        session.setAttribute("error", "This product is already added to cart!");
                    }
                }
                if(!isExists) {
                    cart.add(new Item(product, 1));
                }
                session.setAttribute("cart", cart);
                response.sendRedirect("cart.html?action=view");
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.find(id);
            if (product == null) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            } else {
                cart.remove(product.getId());
                session.setAttribute("cart", cart);
                response.sendRedirect("cart.html?action=view");
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }


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
/*                case "/editShop":
                    showEditForm(request, response);
                    break;*/
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
       List<Shop> listShop = shopDAOImpl.getAll();
       request.getSession().setAttribute("listt", listShop);
       request.getRequestDispatcher("Shop.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditShop.jsp");
        dispatcher.forward(request, response);
    }
/*
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Shop shop = shopDAOImpl.getShopId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditShop.jsp");
        request.setAttribute("shop", shop);
        dispatcher.forward(request, response);

    }
*/
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
        shopDAOImpl.insert(newShop);
        response.sendRedirect("listt");
    }

   private void updateShop(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
        shopDAO.update(newShop);
       session.setAttribute("cart", cart);
       response.sendRedirect("cart.html?action=view");
        response.sendRedirect("listt");
    }

    private void deleteShop(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Shop shop = (Shop) session.getAttribute("shop");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            shop.deleteShop(id);
            session.setAttribute("shop", shop);
            response.sendRedirect("shop.html?action=view");
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }

    }
}
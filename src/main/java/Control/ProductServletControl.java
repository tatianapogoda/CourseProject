package Control;

import DAO.ProductDAO;
import Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServletControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAOImpl;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newProduct":
                    showNewForm(request, response);
                    break;
                case "/insertProduct":
                    insertProduct(request, response);
                    break;
                case "/deleteProduct":
                    deleteProduct(request, response);
                    break;
                case "/editProduct":
                    showEditForm(request, response);
                    break;
                case "/updateProduct":
                    updateProduct(request, response);
                    break;
                case "/list":
                    listProduct(request, response);
                    break;
                case "/list_manager":
                    request.getRequestDispatcher("/ManagerServletControl").forward(request, response);
                    break;
                case "/listShop":
                    request.getRequestDispatcher("/ShopServletControl").forward(request, response);
                    break;
                case "/list_section":
                    request.getRequestDispatcher("/SectionServletControl").forward(request, response);

                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDAOImpl.GetAll();
        request.getSession().setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("Product.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Product> listProduct = productDAOImpl.GetAll();
        request.getSession().setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAOImpl.getBarcode(id);
        List<Product> listProduct = productDAOImpl.GetAll();
        request.getSession().setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct.jsp");
        request.setAttribute("product", product);
        dispatcher.forward(request, response);

    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        int barcode = Integer.parseInt(request.getParameter("barcode"));
        String title = request.getParameter("title");
        int price = Integer.parseInt(request.getParameter("price"));
        int count = Integer.parseInt(request.getParameter("count"));
        int id_section = Integer.parseInt(request.getParameter("id_section"));

        Product newProduct = new Product();
        newProduct.setBarcode(barcode);
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setCount(count);
        newProduct.setSectionId(id_section);
        productDAOImpl.Insert(newProduct);
        response.sendRedirect("editProduct");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        int barcode = Integer.parseInt(request.getParameter("barcode"));
        String title = request.getParameter("title");
        int price = Integer.parseInt(request.getParameter("price"));
        int count = Integer.parseInt(request.getParameter("count"));
        int id_section = Integer.parseInt(request.getParameter("id_section_selection"));

        Product newProduct = new Product();
        newProduct.setBarcode(barcode);
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setCount(count);
        newProduct.setSectionId(id_section);
        productDAOImpl.Update(newProduct);
        response.sendRedirect("listProduct");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = new Product();
        product.setBarcode(id);
        productDAOImpl.Delete(product);
        response.sendRedirect("listProduct");

    }
}

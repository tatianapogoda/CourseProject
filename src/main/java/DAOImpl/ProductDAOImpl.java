package DAOImpl;

import DAO.ProductDAO;
import Model.Product;
import Model.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
	private DbConnection connection = null;

    private List<Product> listProduct;

    public ProductDAOImpl() {    }

    @Override
    public Product getBarcode(Integer barcode) throws SQLException {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE barcode = ?";

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, barcode);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                product.setBarcode(rs.getInt("barcode"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getInt("price"));
                product.setCount(rs.getInt("count"));
                product.setSectionId(rs.getInt("sectionId"));
            }
        }
        return product;
    }
	
    @Override
    public void Delete(Product product) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from product where barcode=?");

            ps.setInt(1, (product.getBarcode()));
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Product product) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update product set title=?," +
                    "price=?, count=?, id_section=? where barcode=?");
            ps.setString(1, product.getTitle());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getCount());
            ps.setInt(4, product.getSectionId());
            ps.setInt(5, product.getBarcode());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Insert(Product product) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product(title, price, count, id_section)" +
                            " VALUES (?,?,?,?,?)");
            ps.setString(1, product.getTitle());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getCount());
            ps.setInt(4, product.getSectionId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public List<Product> GetAll() throws SQLException {
        listProduct = new ArrayList<Product>();

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("select * from product");
            while (rs.next()) {
                Product product = new Product();
                product.setBarcode(rs.getInt("barcode"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getInt("price"));
                product.setCount(rs.getInt("count"));
                product.setSectionId(rs.getInt("sectionId"));

                listProduct.add(product);
            }
        }
        return listProduct;
    }
}

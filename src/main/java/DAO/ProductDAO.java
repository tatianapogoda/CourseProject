package DAO;

import Model.Product;
import java.sql.SQLException;
import java.util.List;


  public interface ProductDAO {
	public Product getBarcode (Integer barcode)  throws SQLException;
    public void Delete (Product product) throws SQLException;
    public void Update (Product product) throws SQLException;
    public void Insert (Product product) throws SQLException;
	public List<Product> GetAll() throws SQLException;
}

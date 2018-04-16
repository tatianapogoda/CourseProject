package DAO;

import Model.Product;
import java.util.List;


  public interface ProductDAO {
	public Product getBarcode (Integer barcode);
    public void delete (Product product);
    public void update (Product product);
    public void insert (Product product);
	public List<Product> getAll();
}

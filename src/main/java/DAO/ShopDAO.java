package DAO;

import Model.Shop;

import java.sql.SQLException;
import java.util.List;


public interface ShopDAO {
	public Shop getShopId (Integer id)  throws SQLException;
    public void Delete (Shop shop) throws SQLException;
    public void Update (Shop shop) throws SQLException;
    public void Insert (Shop shop) throws SQLException;
    public List<Shop> GetAll() throws SQLException;
}

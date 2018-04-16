package DAO;

import Model.Shop;

import java.util.List;


public interface ShopDAO {
    public void delete (Shop shop);
    public void update (Shop shop);
    public void insert (Shop shop);
    public List<Shop> getAll();
}

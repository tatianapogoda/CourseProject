package DAOImpl;

import DAO.ShopDAO;
import Model.Shop;
import Model.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDAOImpl implements ShopDAO {
	private DbConnection connection = null;

    private List<Shop> listShop;

    public ShopDAOImpl() {    }

    @Override
    public Shop getShopId(Integer id) throws SQLException {
        Shop shop =new Shop();
        String sql = "SELECT * FROM shop WHERE id_shop = ?";

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                shop.setShopId(rs.getInt("id_shop"));
                shop.setDirectorSurname(rs.getString("surname_director"));
                shop.setDirectorName(rs.getString("name_director"));
                shop.setDirectorMiddlename(rs.getString("middlename_director"));
                shop.setTelephone(rs.getString("telephone"));
				shop.setAddress(rs.getString("address"));
            }
        }
        return shop;
    }

    @Override
    public void Insert(Shop shop) {

        try(Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO shop(surname_director, name_director, middlename_director,telephone, address)" +
                            " VALUES (?,?,?,?,?)");
            ps.setString(1, shop.getDirectorSurname());
            ps.setString(2, shop.getDirectorName());
            ps.setString(3, shop.getDirectorMiddlename());
            ps.setString(4, shop.getTelephone());
            ps.setString(5, shop.getAddress());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Shop shop) {

        try(Connection connection = DbConnection.getInstance().getConnection())  {
            PreparedStatement ps = connection.prepareStatement("update shop set surname_director=?," +
                    "name_director=?,middlename_director=?, telephone=?,address=? where id_shop=?");
            ps.setString(1, shop.getDirectorSurname());
            ps.setString(2, shop.getDirectorName());
            ps.setString(3, shop.getDirectorMiddlename());            
			ps.setString(4, shop.getTelephone());
            ps.setString(5, shop.getAddress());
			ps.setInt(6, shop.getShopId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Shop shop) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from shop where id_shop=?");

            ps.setInt(1, (shop.getShopId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Shop> GetAll() {
		listShop = new ArrayList<Shop>();
		
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("select * from shop");
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setShopId(rs.getInt("id_shop"));
                shop.setDirectorSurname(rs.getString("surname_director"));
                shop.setDirectorName(rs.getString("name_director"));
                shop.setDirectorMiddlename(rs.getString("middlename_director"));
                shop.setTelephone(rs.getString("telephone"));
				shop.setAddress(rs.getString("address"));

                listShop.add(shop);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listShop;
    }


}

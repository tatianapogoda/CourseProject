package DAOImpl;

import DAO.ManagerDAO;
import Model.Manager;
import Model.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ManagerDAOImpl implements ManagerDAO {
	private DbConnection connection = null;

    private List<Manager> listManager;

    public ManagerDAOImpl() {    }

    @Override
    public Manager getManagerId(Integer id) throws SQLException {
        Manager manager = new Manager();
        String sql = "SELECT * FROM manager WHERE id_manager = ?";

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                manager.setManagerId(rs.getInt("id_manager"));
                manager.setName(rs.getString("name_manager"));
                manager.setSurname(rs.getString("surname_manager"));
                manager.setMiddlename(rs.getString("middlename_manager"));
                manager.setTelephone(rs.getString("telephone"));
            }
        }
        return manager;
	}
	
    @Override
    public void Delete(Manager manager) {
		try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from manager where id_manager=?");

            ps.setInt(1, (manager.getManagerId()));
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Manager manager) {
		try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update manager set name_manager=?," +
                    "surname_manager=?,middlename_manager=?, telephone=? where id_manager=?");
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getSurname());
            ps.setString(3, manager.getMiddlename());
            ps.setString(4, manager.getTelephone());
            ps.setInt(5, manager.getManagerId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Insert(Manager manager) {
	    try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO manager(name_manager, surname_manager, middlename_manager, telephone)" +
                            " VALUES (?,?,?,?)");
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getSurname());
            ps.setString(3, manager.getMiddlename());
            ps.setString(4, manager.getTelephone());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public List<Manager> GetAll() throws SQLException {
        listManager = new ArrayList<Manager>();

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("select * from manager");
            while (rs.next()) {
                Manager manager = new Manager();
                manager.setManagerId((rs.getInt("id_manager")));
                manager.setName((rs.getString("name_manager")));
                manager.setSurname((rs.getString("surname_manager")));
                manager.setMiddlename((rs.getString("middlename_manager")));
                manager.setTelephone((rs.getString("telephone")));


                listManager.add(manager);
            }
        }
        return listManager;
    }
}

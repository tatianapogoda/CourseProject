package DAOImpl;

import DAO.SectionDAO;
import Model.Section;
import Model.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SectionDAOImpl implements SectionDAO {
	private DbConnection connection = null;

    private List<Section> listSection;

    public SectionDAOImpl()   {    }

    @Override
    public Section getSectionId(Integer id) throws SQLException {
        Section section =new Section();
        String sql = "SELECT * FROM section WHERE id_section = ?";

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                section.setSectionId(rs.getInt("id_section"));
                section.setTelephone(rs.getString("telephone"));
                section.setTitle(rs.getString("title"));
                section.setShopId(rs.getInt("id_shop"));
                section.setManagerId(rs.getInt("id_manager"));
            }
        }
        return section;
    }
    @Override
    public void Delete(Section section) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from section where id_section=?");

            ps.setInt(1, (section.getSectionId()));
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Section section) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update section set telephone=?," +
                    "title=?,id_shop=?, id_manager=? where id_section=?");
            ps.setString(1, section.getTelephone());
            ps.setString(2, section.getTitle());
            ps.setInt(3, section.getShopId());
            ps.setInt(4, section.getManagerId());
            ps.setInt(5, section.getSectionId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Insert(Section section) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO section(telephone, title, id_shop, id_manager)" +
                            " VALUES (?,?,?,?)");
            ps.setString(1, section.getTelephone());
            ps.setString(2, section.getTitle());
            ps.setInt(3, section.getShopId());
            ps.setInt(4, section.getManagerId());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public List<Section> GetAll() throws SQLException {
        listSection = new ArrayList<Section>();

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("select * from section");
            while (rs.next()) {
                Section section = new Section();
                section.setSectionId(rs.getInt("id_section"));
                section.setTelephone(rs.getString("telephone"));
                section.setTitle(rs.getString("title"));
                section.setShopId(rs.getInt("id_shop"));
                section.setManagerId(rs.getInt("id_manager"));

                listSection.add(section);
            }
        }
        return listSection;
    }
}

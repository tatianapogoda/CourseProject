package DAO;

import Model.Section;
import java.sql.SQLException;
import java.util.List;

public interface SectionDAO {
	public Section getSectionId (Integer id)  throws SQLException;
    public void Delete(Section section) throws SQLException;
    public void Update(Section section) throws SQLException;
    public void Insert(Section section) throws SQLException;
	public List<Section> GetAll() throws SQLException;
}
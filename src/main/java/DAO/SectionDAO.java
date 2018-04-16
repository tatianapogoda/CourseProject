package DAO;

import Model.Section;
import java.util.List;

public interface SectionDAO {
    public void delete(Section section);
    public void update(Section section);
    public void insert(Section section);
	public List<Section> getAll();
}
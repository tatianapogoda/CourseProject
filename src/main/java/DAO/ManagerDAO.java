package DAO;

import java.util.List;
import Model.Manager;


public interface ManagerDAO {
    public void delete( Manager manager);
    public void update (Manager manager);
    public void insert (Manager manager);
	public List<Manager> getAll();
    
}

package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.Manager;
//import

public interface ManagerDAO {
	public Manager getManagerId (Integer id)  throws SQLException;
    public void Delete( Manager manager) throws SQLException;
    public void Update (Manager manager) throws SQLException;
    public void Insert (Manager manager) throws SQLException;
	public List<Manager> GetAll() throws SQLException; 
    
}

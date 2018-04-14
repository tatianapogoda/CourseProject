package Model;
public class Manager {

    private int managerId;
    private String surname;
    private String name;
    private String middlename;
    private String telephone;

    public Manager() {
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    public int getManagerId(){
        return managerId;
    }

    public void setSurname(String managerSurname) {
        this.surname= managerSurname;
    }
    public String getSurname(){ return surname;   }

    public void setName(String managerName) {
        this.name = managerName;
    }
    public String getName(){
        return name;
    }

    public void setMiddlename(String managerMiddlename) {
        this.middlename= managerMiddlename;
    }
    public String getMiddlename(){
        return middlename;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone(){
        return telephone;
    }



}

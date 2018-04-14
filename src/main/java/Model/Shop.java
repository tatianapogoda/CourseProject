package Model;

public class Shop {
    private int shopId;
    private String directorSurname;
    private String directorName;
    private String directorMiddlename;
    private String telephone;
    private String address;

    public Shop(){

    }

    public void setShopId(int shopId){
        this.shopId=shopId;
    }
    public int getShopId(){
        return shopId;
    }

    public void setDirectorSurname(String directorSurname){
        this.directorSurname=directorSurname;
    }
    public String getDirectorSurname(){
        return directorSurname;
    }

    public void setDirectorName(String directorName){
        this.directorName=directorName;
    }
    public String getDirectorName(){
        return directorName;
    }

    public void setDirectorMiddlename(String directorMiddlename){
        this.directorMiddlename=directorMiddlename;
    }
    public String getDirectorMiddlename(){
        return directorMiddlename;
    }

    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public String getTelephone(){
        return telephone;
    }

    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }

}

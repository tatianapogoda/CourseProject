package Model;

public class Section {
    private int sectionId;
    private String title;
    private String telephone;
    private int shopId;
    private int managerId;

    public Section(){

    }

    public void setSectionId(int sectionId){
        this.sectionId=sectionId;
    }
    public int getSectionId(){
        return sectionId;
    }

    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public String getTelephone(){
        return telephone;
    }

    public void setTitle(String sectionTitle){
        this.title=sectionTitle;
    }
    public String getTitle(){
        return title;
    }

    public void setShopId(int shopId){
        this.shopId=shopId;
    }
    public int getShopId(){
        return shopId;
    }

    public void setManagerId(int managerId){
        this.managerId=managerId;
    }
    public int getManagerId(){
        return managerId;
    }
}

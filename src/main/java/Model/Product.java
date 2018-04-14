package Model;

public class Product {
    private int barcode ;
    private String title;
    private int price;
    private int count;
    private int sectionId;

    public Product(){
    }

    public void setBarcode(int barcode){
        this.barcode=barcode;
    }
    public int getBarcode(){
        return barcode;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }

    public void setPrice(int price){
        this.price=price;
    }
    public int getPrice(){
        return price;
    }

    public void setCount(int count){
        this.count=count;
    }
    public int getCount(){
        return  count;
    }

    public void setSectionId(int sectionId){
        this.sectionId=sectionId;
    }
    public int getSectionId(){
        return  sectionId;
    }


}

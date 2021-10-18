package co.tiagoaguiar.codelab.myapplication;
// para colocar botões e o titulo do botão dinamico crei essa classe
public class MainItem {
    private int id;
    private int drowableId;
    private int textStrigId;
    private int color;

    public MainItem(int id, int drowableId,int textStrigId, int color) {
        this.id = id;
        this.drowableId = drowableId;
        this.textStrigId =textStrigId;
        this.color = color;
    }

    public void setColor(int drowableId){
        this.color = color;
    }
    public  void setDrowableId(int drowableId){
        this.drowableId = drowableId;
    }
    public void setTextStrigId(int textStrigId){
        this.textStrigId =textStrigId;
    }
    public void setId(int id){
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }
    public int getDrowableId(){
        return drowableId;
    }
    public int getTextStrigId(){
        return textStrigId;
    }
}
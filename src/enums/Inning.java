package enums;

public enum Inning {

    TOP1 ("TOP1"), BOTTOM1 ("BOTTOM1"), TOP2 ("TOP2"), BOTTOM2 ("BOTTOM2"), TOP3 ("TOP3"), BOTTOM3 ("BOTTOM3"), TOP4 ("TOP4"), BOTTOM4 ("BOTTOM4");

    public String name;

    Inning (String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

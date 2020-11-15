package sample;

public class GlobalSettingsData {
    private int gold;

    public GlobalSettingsData(){
        gold = 1000000;
    }

    public int getGold(){
        return gold;
    }


    public void updategold(int newValue){
        gold = newValue;
    }
}

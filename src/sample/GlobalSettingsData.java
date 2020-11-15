package sample;

public class GlobalSettingsData {
    private int gold;
    private int era;

    public GlobalSettingsData(){
        era = 1;
        gold = 1000000;
    }

    public int getGold(){
        return gold;
    }

    public void updategold(int newValue){
        gold = newValue;
    }

    public int getEra(){
        return era;
    }

    public void incrementEra(){
        era = era + 1;
    }
}

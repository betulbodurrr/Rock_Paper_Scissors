package oyun;

import java.util.ArrayList;

public class Kullanici extends Oyuncu{
    
    public int[] nesneler = new int[5];
    
    ArrayList <Tas> tasList = new ArrayList <Tas> (5);
    ArrayList <Kagit> kagitList = new ArrayList <Kagit> (5);
    ArrayList <Makas> makasList = new ArrayList <Makas> (5);
    
    ArrayList <agirTas> agirTasList = new ArrayList <agirTas> (5);
    ArrayList <ozelKagit> ozelKagitList = new ArrayList <ozelKagit> (5);
    ArrayList <ustaMakas> ustaMakasList = new ArrayList <ustaMakas> (5);
        
    public Kullanici() {
    }

    public Kullanici(String oyuncuID, String oyuncuAdi, double skor) {
        super(oyuncuID, oyuncuAdi, skor);
    }
    
    @Override
    double skorGoster() {
        
        this.skor=0;
        
        for (int i = 0; i < 5; i++) {
            this.skor += tasList.get(i).seviyePuani;
            this.skor += kagitList.get(i).seviyePuani;
            this.skor += makasList.get(i).seviyePuani;
        }
        
        return this.skor;
        
    }

    int next=0, nesne=0;
    
    @Override
    void nesneSec() {
        
        for (int i = 0; i < 5; i++) {
            tasList.add(new Tas());
            agirTasList.add(new agirTas());
            
            kagitList.add(new Kagit());
            ozelKagitList.add(new ozelKagit());
            
            makasList.add(new Makas());
            ustaMakasList.add(new ustaMakas());
        }
        
    }
    
}

package oyun;

import java.util.ArrayList;
import java.util.Random;

public class Bilgisayar extends Oyuncu {

    Random r = new Random();
    int randomSayi=0;
    
    public int[] nesneler = new int[5];
    int tIndex=0, kIndex=0, mIndex=0; 
           
    ArrayList <Tas> tasList = new ArrayList <Tas> (5);
    ArrayList <Kagit> kagitList = new ArrayList <Kagit> (5);
    ArrayList <Makas> makasList = new ArrayList <Makas> (5);
    
    ArrayList <agirTas> agirTasList = new ArrayList <agirTas> (5);
    ArrayList <ozelKagit> ozelKagitList = new ArrayList <ozelKagit> (5);
    ArrayList <ustaMakas> ustaMakasList = new ArrayList <ustaMakas> (5);
    
    public Bilgisayar() {
    }

    public Bilgisayar(String oyuncuID, String oyuncuAdi, double skor) {
        super(oyuncuID, oyuncuAdi, skor);
    }   
    
    //double seviyePuaniToplam=0;
    
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
    
    @Override //ilk yazdıgım fonksiyon
    void nesneSec() 
    {  
        for (int i = 0; i < 5; i++) 
        {
            randomSayi=r.nextInt(3)+1;
            
            agirTasList.add(new agirTas());
            ozelKagitList.add(new ozelKagit());
            ustaMakasList.add(new ustaMakas());
            
            if(randomSayi==1)
            {                        
                nesneListesi.add("Tas"+(tIndex+1));
                nesneler[i]=randomSayi;
                tIndex++;
            }
                
            if(randomSayi==2)
            {
                nesneListesi.add("Kagit"+(kIndex+1));
                nesneler[i]=randomSayi;
                kIndex++;
            }    
                
            if(randomSayi==3)
            {
                nesneListesi.add("Makas"+(mIndex+1));
                nesneler[i]=randomSayi;
                mIndex++;
            }     
            
        }   
    
        for (int i = 0; i < 5; i++) {
            tasList.add(new Tas());
            agirTasList.add(new agirTas());
        }
        
        for (int i = 0; i < 5; i++) {
            kagitList.add(new Kagit());
            ozelKagitList.add(new ozelKagit());
        }
        
        for (int i = 0; i < 5; i++) {
            makasList.add(new Makas());
            ustaMakasList.add(new ustaMakas());
        }

        System.out.println(nesneListesi);      
        
    }
    
    
    
}

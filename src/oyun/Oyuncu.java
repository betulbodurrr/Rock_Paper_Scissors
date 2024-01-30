package oyun;

import java.util.ArrayList;

abstract class Oyuncu {
    
    String oyuncuID, oyuncuAdi;
    double skor;

    public Oyuncu(){        
    }
    
    public Oyuncu(String oyuncuID, String oyuncuAdi, double skor) {
    }

    ArrayList <String> nesneListesi = new ArrayList <String>();
    
    abstract double skorGoster();
    abstract void nesneSec(); //bilgisayar ve kullanıcı icin ayrı olarak calisacak.
    
    
}

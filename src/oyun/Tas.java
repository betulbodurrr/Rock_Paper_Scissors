package oyun;

public class Tas extends Nesne {

    double katilik=2;
    
    int objectType=0;
    double ozellik=0, rutbeOzellik=0, a=0.2, etki=0, artis=0;

    public Tas(){        
    }
    
    public Tas(double dayaniklilik, double seviyePuani, double katilik){
        super(dayaniklilik, seviyePuani);
    }

    @Override
    void nesnePuaniGoster() {
    }

    @Override
    double etkiHesapla(int objectType, double ozellik, double rutbeOzellik) {
        
        if(objectType==2) //kagit geldi
        {
            etki=katilik/((1-a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==3) //makas geldi
        {
            etki=katilik/(a*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void durumGuncelle(double etki, double artis) {
        this.dayaniklilik-=etki;        
        this.seviyePuani+=artis;
    }
 
      
}

package oyun;

public class Kagit extends Nesne{

    double nufuz=2;
    
    int objectType=0;
    double ozellik=0, rutbeOzellik=0, a=0.2, etki=0, artis=0;

    public Kagit(){       
    }
    
    public Kagit(double dayaniklilik, double seviyePuani, double nufuz) {
        super(dayaniklilik, seviyePuani);
    }
    
    
    @Override
    void nesnePuaniGoster() {
    }

    @Override
    double etkiHesapla(int objectType, double ozellik, double rutbeOzellik) {
        
        if(objectType==1) //tas geldi
        {
            etki=nufuz/((a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==3) //makas geldi
        {
            etki=nufuz/((1-a)*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void durumGuncelle(double etki, double artis) {
        this.dayaniklilik-=etki;        
        this.seviyePuani+=artis;
    }
       
}

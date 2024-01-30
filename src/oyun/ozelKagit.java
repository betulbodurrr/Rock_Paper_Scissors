package oyun;

public class ozelKagit extends Kagit {

    double kalinlik=2;
    
    public ozelKagit(){
    }
    
    public ozelKagit(double dayaniklilik, double seviyePuani, double nufuz, double kalinlik) {
        super(dayaniklilik, seviyePuani, nufuz);
    }

    @Override
    void durumGuncelle(double etki, double artis) {
        this.dayaniklilik-=etki;        
        this.seviyePuani+=artis;
    }

    @Override
    double etkiHesapla(int objectType, double ozellik, double rutbeOzellik) {
        super.etkiHesapla(objectType, ozellik, rutbeOzellik);
        
        if(objectType==1) //tas geldi
        {
            etki=(nufuz*kalinlik)/((a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==3) //makas geldi
        {
            etki=(nufuz*kalinlik)/((1-a)*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void nesnePuaniGoster() {
        super.nesnePuaniGoster();
    }
    
    
    
}

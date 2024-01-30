package oyun;

public class agirTas extends Tas {

    double sicaklik=2;
    
    public agirTas() {
    }

    public agirTas(double dayaniklilik, double seviyePuani, double katilik, double sicaklik) {
        super(dayaniklilik, seviyePuani, katilik);
    }

    @Override
    void durumGuncelle(double etki, double artis) {
        this.dayaniklilik-=etki;        
        this.seviyePuani+=artis;
    }

    @Override
    double etkiHesapla(int objectType, double ozellik, double rutbeOzellik) {
        super.etkiHesapla(objectType, ozellik, rutbeOzellik);
        
        if(objectType==2) //kagit geldi
        {
            etki=(katilik*sicaklik)/((1-a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==3) //makas geldi
        {
            etki=(katilik*sicaklik)/(a*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void nesnePuaniGoster() {
        super.nesnePuaniGoster();
    }
    
    
    
}

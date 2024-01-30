package oyun;

public class ustaMakas extends Makas {

    double direnc=2;
    
    public ustaMakas() {
    }

    public ustaMakas(double dayaniklilik, double seviyePuani, double keskinlik, double direnc) {
        super(dayaniklilik, seviyePuani, keskinlik);
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
            etki=(keskinlik*direnc)/((1-a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==2) //kagit geldi
        {
            etki=(keskinlik*direnc)/(a*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void nesnePuaniGoster() {
        super.nesnePuaniGoster();
    }
    
    
    
}

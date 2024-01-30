package oyun;

public class Makas extends Nesne {

    double keskinlik=2;
    
    int objectType=0;
    double ozellik=0, rutbeOzellik=0, a=0.2, etki=0, artis=0;
    
    public Makas() {
    }

    public Makas(double dayaniklilik, double seviyePuani, double keskinlik) {
        super(dayaniklilik, seviyePuani);
    }

    @Override
    void nesnePuaniGoster() {
    }

    @Override
    double etkiHesapla(int objectType, double ozellik, double rutbeOzellik) {
        
        if(objectType==1) //tas geldi
        {
            etki=keskinlik/((1-a)*ozellik*rutbeOzellik);
        }
        
        else if(objectType==2) //kagit geldi
        {
            etki=keskinlik/(a*ozellik*rutbeOzellik);
        }
        
        return etki;
    }

    @Override
    void durumGuncelle(double etki, double artis) {
        this.dayaniklilik-=etki;        
        this.seviyePuani+=artis;
    }  
    
    
}

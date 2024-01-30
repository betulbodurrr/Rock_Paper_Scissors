package oyun;

abstract class Nesne {

    double dayaniklilik, seviyePuani;
    
    public Nesne() {
        this.dayaniklilik=20;
        this.seviyePuani=0;
    }
    
    public Nesne(double dayaniklilik, double seviyePuani){        
    }

    abstract void nesnePuaniGoster();
    abstract double etkiHesapla(int objectType, double ozellik, double rutbeOzellik);
    abstract void durumGuncelle(double etki, double artis);    
                          
       
}

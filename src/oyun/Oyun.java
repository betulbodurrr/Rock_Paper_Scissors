//18.11.2022 dosyaYaz fonksiyonu eklendi. Fonksiyona "String str" degiskeni olusturuldu.

package oyun;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Oyun {

    public static void main(String[] args) throws IOException {

        File file = new File("output.txt"); //oyun basında txt ici siliniyor.
        FileWriter fileWriter = new FileWriter(file, false);
        
        Arayuz arayuz = new Arayuz();
        arayuz.gui();
    }

    public int[] whoPlays = new int[3];

    public int com1 = 0, com2 = 0, mainPlayer = 0, setSayisi = 0;

    Bilgisayar bilgisayar1 = new Bilgisayar();
    Bilgisayar bilgisayar2 = new Bilgisayar();
    Kullanici kullanici = new Kullanici();

    public void startGame() {
        whoPlays[0] = com1;
        whoPlays[1] = com2;
        whoPlays[2] = mainPlayer;

        // eğer 0. ve 2. indis 1 ise kullanici-pc // eğer 0. ve 1. indis 1 ise pc-pc seçimi yapıldı.
        if (whoPlays[0] == 1) {
            bilgisayar1.nesneSec(); //bilgisayar 5 adet nesne seçti.                                                                                                   

            System.out.println("Computer 1");
        }

        if (whoPlays[1] == 1) {
            bilgisayar2.nesneSec();

            System.out.println("Computer 2");
        }

        if (whoPlays[2] == 1) {
            kullanici.nesneSec();
        }

    }

    Tas t1 = new Tas();
    Kagit k1 = new Kagit();
    Makas m1 = new Makas();

    agirTas agirt1 = new agirTas();
    ozelKagit ozelk1 = new ozelKagit();
    ustaMakas ustam1 = new ustaMakas();

    Tas t2 = new Tas();
    Kagit k2 = new Kagit();
    Makas m2 = new Makas();

    agirTas agirt2 = new agirTas();
    ozelKagit ozelk2 = new ozelKagit();
    ustaMakas ustam2 = new ustaMakas();
    
    double etki1, etki2;
    
    public void dosyaYaz(String str) throws IOException{
        
        File file = new File("output.txt");
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
            
        bWriter.append(str);
        
        bWriter.close();
    }
    
    public void kapistir(int c1temp, int c1temp2, int c2temp, int c2temp2, int secilen1, int secilen2) throws IOException {
        
        if (c1temp2 == 1) {
            
            t1 = bilgisayar1.tasList.get(c1temp-1);
            
            if (c2temp2 == 1)
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                t1.durumGuncelle(0.5, 0);
                t2.durumGuncelle(0.5, 0);
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(t1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) 
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                        
                System.out.println(t1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");                
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3)
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                 if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                 
                System.out.println(t1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4)
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                System.out.println("Agir Tas Kazandi");
                
                t1.durumGuncelle(3, 0);
                agirt2.durumGuncelle(0.25, 20);
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");                               
                
                System.out.println(t1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
               
            }

            if (c2temp2 == 5) 
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);     
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                k2.seviyePuani = ozelk2.seviyePuani;
                k2.dayaniklilik = ozelk2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
  
                System.out.println(t1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) 
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = ustam2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");                              
                
                System.out.println(t1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 2) {
            
            k1 = bilgisayar1.kagitList.get(c1temp-1);
            
            if (c2temp2 == 1) // k1 t2
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(k1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) 
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                k1.durumGuncelle(0.5, 0);
                k2.durumGuncelle(0.5, 0);
                
                System.out.println(k1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+k2.seviyePuani);
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // k1 m2
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(k1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // k1 agirt2
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                etki1 = k1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
               
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
   
                System.out.println(k1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 5) // k1 ozelk2
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                System.out.println("Ozel Kagit Kazandi.");
                
                k1.durumGuncelle(3, 0);
                ozelk2.durumGuncelle(0.25, 20);
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                              
                System.out.println(k1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 6) // k1 ustam2
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = ustam2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");            
                
                System.out.println(k1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 3) {
            
            m1 = bilgisayar1.makasList.get(c1temp-1);
            
            if (c2temp2 == 1) // m1 t2
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(m1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // m1 k2
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(m1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3)
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                m1.durumGuncelle(0.5, 0);
                m2.durumGuncelle(0.5,0);
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                           
                System.out.println(m1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // m1 agirt2
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                etki1 = m1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
               
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;

                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");   
                
                System.out.println(m1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // m1 ozelk2
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");       
                
                System.out.println(m1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // m1 ustam2
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                System.out.println("Usta Makas Kazandi."); 
                
                m1.durumGuncelle(3, 0);
                ustam2.durumGuncelle(0.25, 20);
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");           
                
                System.out.println(m1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 4) {
            
            agirt1 = bilgisayar1.agirTasList.get(c1temp-1);
            t1 = bilgisayar1.tasList.get(c1temp-1);
            
            if (c2temp2 == 1) // agirt1 t2
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                System.out.println("Agir Tas Kazandi.");
                
                t2.durumGuncelle(3, 0);
                agirt2.durumGuncelle(0.25, 20);
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(agirt1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // agirt1 k2
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                                
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(agirt1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // agirt1 m2
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
               
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                               
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(agirt1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // agirt1 agirt2
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                System.out.println("Berabere");
                
                agirt1.durumGuncelle(1, 0);
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                agirt2.durumGuncelle(1, 0);
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                System.out.println(agirt1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+agirt2.seviyePuani);
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // agirt1 ozelk2
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
           
                System.out.println(agirt1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // agirt1 ustam2
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = agirt2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
               
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }         
                
                System.out.println(agirt1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
    
        if (c1temp2 == 5) {
            
            ozelk1 = bilgisayar1.ozelKagitList.get(c1temp-1);
            k1 = bilgisayar1.kagitList.get(c1temp-1);
            
            if (c2temp2 == 1) // ozelk1 t2
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ozelk1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // ozelk1 k2
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                System.out.println("Ozel Kağıt Kazandı.");
                
                k2.durumGuncelle(3, 0);               
                ozelk1.durumGuncelle(0.25, 20);
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ozelk1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // ozelk1 m2
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
               
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ozelk1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // ozelk1 agirt2
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                etki1 = ozelk1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
    
                System.out.println(ozelk1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // ozelk1 ozelk2
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                ozelk1.durumGuncelle(1, 0);
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                ozelk2.durumGuncelle(1, 0);
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                                           
                
                System.out.println(ozelk1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // ozelk1 ustam2
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = agirt2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
               
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                } 
                
                System.out.println(ozelk1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        } 
        
        if (c1temp2 == 6) {
            
            ustam1 = bilgisayar1.ustaMakasList.get(c1temp-1);
            m1 = bilgisayar1.makasList.get(c1temp-1);
            
            if (c2temp2 == 1) // ustam1 t2
            {
                t2 = bilgisayar2.tasList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ustam1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // ustam1 k2
            {
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
               
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ustam1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // ustam1 m2
            {
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                System.out.println("Usta Makas Kazandi.");
                
                m2.durumGuncelle(3, 0);
                ustam1.durumGuncelle(0.25, 20);
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ustam1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // ustam1 agirt2
            {
                agirt2 = bilgisayar2.agirTasList.get(c2temp-1);
                t2 = bilgisayar2.tasList.get(c2temp-1);
               
                etki1 = ustam1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
                
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ustam1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // ustam1 ozelk2
            {
                ozelk2 = bilgisayar2.ozelKagitList.get(c2temp-1);
                k2 = bilgisayar2.kagitList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    m1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
               
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(ustam1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // ustam1 ustam2
            {
                ustam2 = bilgisayar2.ustaMakasList.get(c2temp-1);
                m2 = bilgisayar2.makasList.get(c2temp-1);
                
                System.out.println("Berabere"); 
                
                ustam1.durumGuncelle(1, 0);
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                ustam2.durumGuncelle(1, 0);
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    bilgisayar2.nesneler[secilen2]=-1;
                    this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(ustam1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(bilgisayar2.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }    
  
    } // burayı kopyala ve bilgisayar2 leri kullanici yaparak alta yapistir.

    public void kapistir2(int c1temp, int c1temp2, int c2temp, int c2temp2, int secilen1, int secilen2) throws IOException {
        
        if (c1temp2 == 1) {
            
            t1 = bilgisayar1.tasList.get(c1temp-1);
            
            if (c2temp2 == 1)
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                t1.durumGuncelle(0.5, 0);
                t2.durumGuncelle(0.5, 0);
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(t1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) 
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                        
                System.out.println(t1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");                
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3)
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                 if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                 
                System.out.println(t1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4)
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                System.out.println("Agir Tas Kazandi");
                
                t1.durumGuncelle(3, 0);
                agirt2.durumGuncelle(0.25, 20);
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");                               
                
                System.out.println(t1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
               
            }

            if (c2temp2 == 5) 
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);     
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                k2.seviyePuani = ozelk2.seviyePuani;
                k2.dayaniklilik = ozelk2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
  
                System.out.println(t1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) 
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = t1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = ustam2.etkiHesapla(c1temp2, t1.katilik, 1);
                
                if(etki1 > etki2)
                {
                    t1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    t1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(t1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");                              
                
                System.out.println(t1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(t1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 2) {
            
            k1 = bilgisayar1.kagitList.get(c1temp-1);
            
            if (c2temp2 == 1) // k1 t2
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(k1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) 
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                k1.durumGuncelle(0.5, 0);
                k2.durumGuncelle(0.5, 0);
                
                System.out.println(k1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+k2.seviyePuani);
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // k1 m2
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(k1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // k1 agirt2
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                etki1 = k1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
               
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
   
                System.out.println(k1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 5) // k1 ozelk2
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                System.out.println("Ozel Kagit Kazandi.");
                
                k1.durumGuncelle(3, 0);
                ozelk2.durumGuncelle(0.25, 20);
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                              
                System.out.println(k1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 6) // k1 ustam2
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = k1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = ustam2.etkiHesapla(c1temp2, k1.nufuz, 1);
                
                if(etki1 > etki2)
                {
                    k1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    k1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(k1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");            
                
                System.out.println(k1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(k1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 3) {
            
            m1 = bilgisayar1.makasList.get(c1temp-1);
            
            if (c2temp2 == 1) // m1 t2
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(m1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // m1 k2
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(m1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3)
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                m1.durumGuncelle(0.5, 0);
                m2.durumGuncelle(0.5,0);
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                           
                System.out.println(m1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.5\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // m1 agirt2
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                etki1 = m1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
               
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;

                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");   
                
                System.out.println(m1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // m1 ozelk2
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = m1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, m1.keskinlik, 1);
                
                if(etki1 > etki2)
                {
                    m1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    m1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");       
                
                System.out.println(m1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // m1 ustam2
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                System.out.println("Usta Makas Kazandi."); 
                
                m1.durumGuncelle(3, 0);
                ustam2.durumGuncelle(0.25, 20);
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(m1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m1.seviyePuani > 30)
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Rütbe Atladı ! !\n\n");           
                
                System.out.println(m1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(m1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
        
        if (c1temp2 == 4) {
            
            agirt1 = bilgisayar1.agirTasList.get(c1temp-1);
            t1 = bilgisayar1.tasList.get(c1temp-1);
            
            if (c2temp2 == 1) // agirt1 t2
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                System.out.println("Agir Tas Kazandi.");
                
                t2.durumGuncelle(3, 0);
                agirt2.durumGuncelle(0.25, 20);
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(agirt1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // agirt1 k2
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                                
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(agirt1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // agirt1 m2
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
               
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                               
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(agirt1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // agirt1 agirt2
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                System.out.println("Berabere");
                
                agirt1.durumGuncelle(1, 0);
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                agirt2.durumGuncelle(1, 0);
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                System.out.println(agirt1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+agirt2.seviyePuani);
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // agirt1 ozelk2
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
                
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
           
                System.out.println(agirt1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // agirt1 ustam2
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = agirt1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = agirt2.etkiHesapla(c1temp2, agirt1.katilik, agirt1.sicaklik);
                
                if(etki1 > etki2)
                {
                    agirt1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    agirt1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
               
                t1.seviyePuani=agirt1.seviyePuani;
                t1.dayaniklilik=agirt1.dayaniklilik;
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(agirt1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }         
                
                System.out.println(agirt1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(agirt1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }
    
        if (c1temp2 == 5) {
            
            ozelk1 = bilgisayar1.ozelKagitList.get(c1temp-1);
            k1 = bilgisayar1.kagitList.get(c1temp-1);
            
            if (c2temp2 == 1) // ozelk1 t2
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ozelk1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // ozelk1 k2
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                System.out.println("Ozel Kağıt Kazandı.");
                
                k2.durumGuncelle(3, 0);               
                ozelk1.durumGuncelle(0.25, 20);
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ozelk1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // ozelk1 m2
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, m2.keskinlik, 1);
                etki2 = m2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    m2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    m2.durumGuncelle(etki1, 20);
                }
               
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(m2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ozelk1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // ozelk1 agirt2
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                etki1 = ozelk1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
                
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
    
                System.out.println(ozelk1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // ozelk1 ozelk2
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                System.out.println("Berabere");
                
                ozelk1.durumGuncelle(1, 0);
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                ozelk2.durumGuncelle(1, 0);
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                                           
                
                System.out.println(ozelk1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // ozelk1 ustam2
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                etki1 = ozelk1.etkiHesapla(c2temp2, ustam2.keskinlik, ustam2.direnc);
                etki2 = agirt2.etkiHesapla(c1temp2, ozelk1.nufuz, ozelk1.kalinlik);
                
                if(etki1 > etki2)
                {
                    ozelk1.durumGuncelle(etki2, 20);
                    ustam2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ozelk1.durumGuncelle(etki2, 0);
                    ustam2.durumGuncelle(etki1, 20);
                }
               
                k1.seviyePuani=ozelk1.seviyePuani;
                k1.dayaniklilik=ozelk1.dayaniklilik;
                
                m2.seviyePuani=ustam2.seviyePuani;
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(ozelk1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                } 
                
                System.out.println(ozelk1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(ozelk1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        } 
        
        if (c1temp2 == 6) {
            
            ustam1 = bilgisayar1.ustaMakasList.get(c1temp-1);
            m1 = bilgisayar1.makasList.get(c1temp-1);
            
            if (c2temp2 == 1) // ustam1 t2
            {
                t2 = kullanici.tasList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, t2.katilik, 1);
                etki2 = t2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    t2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    t2.durumGuncelle(etki1, 20);
                }
                
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(t2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(t2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ustam1.dayaniklilik+" and "+t2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+t2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+t2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+t2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 2) // ustam1 k2
            {
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, k2.nufuz, 1);
                etki2 = k2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    k2.durumGuncelle(etki1, 20);
                }
               
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(k2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                if(k2.seviyePuani > 30)
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Rütbe Atladı ! !\n\n");
                
                System.out.println(ustam1.dayaniklilik+" and "+k2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+k2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+k2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+k2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 3) // ustam1 m2
            {
                m2 = kullanici.makasList.get(c2temp-1);
                
                System.out.println("Usta Makas Kazandi.");
                
                m2.durumGuncelle(3, 0);
                ustam1.durumGuncelle(0.25, 20);
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(m2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ustam1.dayaniklilik+" and "+m2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+m2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 3\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+m2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+m2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 0.25\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 4) // ustam1 agirt2
            {
                agirt2 = kullanici.agirTasList.get(c2temp-1);
                t2 = kullanici.tasList.get(c2temp-1);
               
                etki1 = ustam1.etkiHesapla(c2temp2, agirt2.katilik, agirt2.sicaklik);
                etki2 = agirt2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    agirt2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    agirt2.durumGuncelle(etki1, 20);
                }
                
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                t2.seviyePuani=agirt2.seviyePuani;
                t2.dayaniklilik=agirt2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(agirt2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }                              
                
                System.out.println(ustam1.dayaniklilik+" and "+agirt2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+agirt2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+agirt2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+agirt2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
                
            }

            if (c2temp2 == 5) // ustam1 ozelk2
            {
                ozelk2 = kullanici.ozelKagitList.get(c2temp-1);
                k2 = kullanici.kagitList.get(c2temp-1);
                
                etki1 = ustam1.etkiHesapla(c2temp2, ozelk2.nufuz, ozelk2.kalinlik);
                etki2 = ozelk2.etkiHesapla(c1temp2, ustam1.keskinlik, ustam1.direnc);
                
                if(etki1 > etki2)
                {
                    ustam1.durumGuncelle(etki2, 20);
                    m1.durumGuncelle(etki2, 20);
                    ozelk2.durumGuncelle(etki1, 0);
                    k2.durumGuncelle(etki1, 0);
                }
                
                else
                {
                    ustam1.durumGuncelle(etki2, 0);
                    ozelk2.durumGuncelle(etki1, 20);
                }
               
                m1.seviyePuani=ustam1.seviyePuani;
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                k2.seviyePuani=ozelk2.seviyePuani;
                k2.dayaniklilik=ozelk2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ozelk2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(ustam1.dayaniklilik+" and "+ozelk2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+ozelk2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki1 +"\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ozelk2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ozelk2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: "+ etki2 +"\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

            if (c2temp2 == 6) // ustam1 ustam2
            {
                ustam2 = kullanici.ustaMakasList.get(c2temp-1);
                m2 = kullanici.makasList.get(c2temp-1);
                
                System.out.println("Berabere"); 
                
                ustam1.durumGuncelle(1, 0);
                m1.dayaniklilik=ustam1.dayaniklilik;
                
                ustam2.durumGuncelle(1, 0);
                m2.dayaniklilik=ustam2.dayaniklilik;
                
                if(ustam1.dayaniklilik<=0){
                    bilgisayar1.nesneler[secilen1]=-1;
                    this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+" Oyundan Elendi !\n\n");
                }
                
                if(ustam2.dayaniklilik<=0){
                    kullanici.nesneler[secilen2]=-1;
                    this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+" Oyundan Elendi !\n\n");
                }
                
                System.out.println(ustam1.dayaniklilik+" and "+ustam2.dayaniklilik);
                System.out.println(ustam1.seviyePuani+" and "+ustam2.seviyePuani);
                
                this.dosyaYaz(bilgisayar1.nesneListesi.get(secilen1).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam1.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam1.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz(kullanici.nesneListesi.get(secilen2).toString()+"\n");
                this.dosyaYaz("Dayanıklılık: "+ustam2.dayaniklilik+"\n");
                this.dosyaYaz("Seviye Puanı: "+ustam2.seviyePuani+"\n");
                this.dosyaYaz("Karşı tarafa etkisi: 1\n\n");
                
                this.dosyaYaz("--------Next Round--------\n\n");
            }

        }    
  
    }
    
}

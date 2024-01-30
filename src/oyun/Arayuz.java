
package oyun;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Arayuz {
    
    public int oyunBaslasin=0;     

    Oyun oyun = new Oyun();
    
    JLabel resim1 = new JLabel();
    JLabel nesne1 = new JLabel();
    
    JLabel resim2 = new JLabel();
    JLabel nesne2 = new JLabel();
    
    JRadioButton tasBtn = new JRadioButton();
    JRadioButton kagitBtn = new JRadioButton();
    JRadioButton makasBtn = new JRadioButton();
    
    ButtonGroup bg = new ButtonGroup();
    
    JButton devam = new JButton();
    JButton savas = new JButton();
    
    URL tasImg = this.getClass().getResource("/images/stone.png");
    URL kagitImg = this.getClass().getResource("/images/paper.png");
    URL makasImg = this.getClass().getResource("/images/scissors.png");
    URL agirTasImg = this.getClass().getResource("/images/heavyStone.png");
    URL ozelKagitImg = this.getClass().getResource("/images/specialPaper.png");
    URL ustaMakasImg = this.getClass().getResource("/images/masterScissors.png");
    
    URL fightUrl = this.getClass().getResource("/images/fight.png");
    URL arrowUrl = this.getClass().getResource("/images/arrow.png");
    URL colorfulUrl = this.getClass().getResource("/images/colorful.png");
    
    URL hackerUrl = this.getClass().getResource("/images/hacker.png");
    URL computerUrl = this.getClass().getResource("/images/computer.png");
    URL lightningUrl = this.getClass().getResource("/images/lightning.png");
    
    URL cupUrl = this.getClass().getResource("/images/cup.png");
    URL drawUrl = this.getClass().getResource("/images/draw.png");
    URL stars = this.getClass().getResource("/images/stars.jpeg");
    
    ImageIcon tasImage = new ImageIcon(tasImg);
    ImageIcon kagitImage = new ImageIcon(kagitImg);
    ImageIcon makasImage = new ImageIcon(makasImg);
    ImageIcon agirTasImage = new ImageIcon(agirTasImg);
    ImageIcon ozelKagitImage = new ImageIcon(ozelKagitImg);
    ImageIcon ustaMakasImage = new ImageIcon(ustaMakasImg);
    
    JFrame f = new JFrame("Taş Kağıt Makas");       
    
    JLabel skor1;
    JLabel skor2;
    JLabel kupa; 
    JLabel kazanan;
    JLabel berabere;
    
    JLabel secilen = new JLabel();
    
    JComboBox liste;
    
    int bittiMi=0;
    int winner=0;
    int birinciSkor=0, ikinciSkor=0;
    
    public void gui() throws IOException{
                          
        Font myFont = new Font("Ink Free", Font.BOLD, 15);
        Font myFont2 = new Font("Comic Sans MS", Font.BOLD, 12);
        Font myFont3 = new Font("Comic Sans MS", Font.BOLD, 15);
             
        JLabel img = new JLabel(new ImageIcon(fightUrl));
        img.setBounds(0, 5, 200, 33);
        f.add(img);
        
        JLabel img2 = new JLabel(new ImageIcon(arrowUrl));
        img2.setBounds(0, 80, 200, 200);
        img2.setVisible(false);
        f.add(img2);
        
        JLabel lab = new JLabel();
        lab.setText("Lütfen Seçiminizi Yapınız");
        lab.setForeground(Color.white);
        lab.setBounds(15, 38, 300, 30);
        lab.setFont(myFont);
        f.add(lab);              
        
        JLabel lab2 = new JLabel(new ImageIcon(colorfulUrl));
        lab2.setBounds(10,370,180,180);
        f.add(lab2);                
        
        JButton btn = new JButton();
        btn.setText("Bilgisayar - Bilgisayar");
        btn.setFont(myFont2);
        btn.setBounds(10,65,180,30);
        f.add(btn);
        
        JButton btn2 = new JButton();
        btn2.setText("Kullanıcı - Bilgisayar");
        btn2.setFont(myFont2);
        btn2.setBounds(10,100,180,30);
        f.add(btn2);
        
        JTextField sayi = new JTextField("10");
        sayi.setBounds(10, 220, 180, 30);
        sayi.setBackground(Color.black);
        sayi.setForeground(Color.green);
        sayi.setCaretColor(Color.red);
        sayi.setFont(myFont2);
        sayi.setVisible(false);
        f.add(sayi);
        
        JTextField kullanici = new JTextField("Kullanıcı Adınızı Giriniz");
        kullanici.setBounds(10, 300, 180, 30);
        kullanici.setBackground(Color.black);
        kullanici.setForeground(Color.green);
        kullanici.setCaretColor(Color.red);
        kullanici.setFont(myFont2);
        kullanici.setVisible(false);
        f.add(kullanici);
        
        JButton btn3 = new JButton(); //set sayisi
        btn3.setText("Onayla");
        btn3.setFont(myFont2);
        btn3.setBackground(Color.orange);
        btn3.setBounds(10,255,180,30);
        btn3.setVisible(false);
        f.add(btn3);
        
        JButton btn4 = new JButton(); // kullanici adi onay
        btn4.setText("Onayla");
        btn4.setBackground(Color.orange);
        btn4.setFont(myFont2);
        btn4.setBounds(10,335,180,30);
        btn4.setVisible(false);
        btn4.setEnabled(false);
        f.add(btn4);
        
        JLabel user = new JLabel(); // üstteki kullanıcı adı
        user.setBounds(730, 20, 200, 20);
        user.setFont(myFont3);
        user.setForeground(Color.white);
        f.add(user);
        
        JLabel pcAdi1 = new JLabel(); // üstteki pc adı 1
        pcAdi1.setBounds(370, 20, 100, 20);
        pcAdi1.setFont(myFont3);
        pcAdi1.setForeground(Color.white);
        f.add(pcAdi1);
        
        JLabel pcAdi2 = new JLabel(); // üstteki pc adı 2
        pcAdi2.setBounds(730, 20, 100, 20);
        pcAdi2.setFont(myFont3);
        pcAdi2.setForeground(Color.white);
        f.add(pcAdi2);
        
        JLabel img1 = new JLabel(new ImageIcon(hackerUrl));
        img1.setBounds(830,2,130,130);
        f.add(img1);
        img1.setVisible(false);
        
        JLabel img5 = new JLabel(new ImageIcon(computerUrl));
        img5.setBounds(845,5,130,110);
        f.add(img5);
        img5.setVisible(false);
        
        JLabel img6 = new JLabel(new ImageIcon(computerUrl));
        img6.setBounds(210,5,130,110);
        f.add(img6);
        img6.setVisible(false);
        
        JLabel img7 = new JLabel(new ImageIcon(lightningUrl));
        img7.setBounds(550,15,130,110);
        f.add(img7);
        
        Font skorFont = new Font("Impact", Font.BOLD, 40);
 
        skor1 = new JLabel("0");
        skor1.setBounds(370,25,100,100);
        skor1.setFont(skorFont);
        skor1.setForeground(Color.orange);
        skor1.setVisible(false);
        f.add(skor1);
        
        skor2 = new JLabel("0");
        skor2.setBounds(730,25,100,100);
        skor2.setForeground(Color.orange);
        skor2.setFont(skorFont);
        skor2.setVisible(false);
        f.add(skor2);
        
        kupa = new JLabel(new ImageIcon(cupUrl));
        kupa.setBounds(440, 180, 300, 400);
        kupa.setVisible(false);
        f.add(kupa);
        
        kazanan = new JLabel();
        kazanan.setFont(skorFont);
        kazanan.setForeground(Color.yellow);
        kazanan.setBounds(420, 150, 1000, 50);
        kazanan.setVisible(false);
        f.add(kazanan);
        
        berabere = new JLabel(new ImageIcon(drawUrl));
        berabere.setBounds(440,180, 300, 400);
        berabere.setVisible(false);
        f.add(berabere);
        
        tasBtn.setBounds(400, 200, 150, 50);
        tasBtn.setText("Taş");
        tasBtn.setBackground(Color.orange);
        tasBtn.setFont(skorFont);
        tasBtn.setVisible(false);
        f.add(tasBtn);
        
        kagitBtn.setBounds(400, 300, 150, 50);
        kagitBtn.setText("Kağıt");
        kagitBtn.setBackground(Color.orange);
        kagitBtn.setFont(skorFont);
        kagitBtn.setVisible(false);
        f.add(kagitBtn);
        
        makasBtn.setBounds(400, 400, 150, 50);
        makasBtn.setText("Makas");
        makasBtn.setBackground(Color.orange);
        makasBtn.setFont(skorFont);
        makasBtn.setVisible(false);
        f.add(makasBtn);
       
        bg.add(tasBtn);
        bg.add(kagitBtn);
        bg.add(makasBtn);   
        
        devam.setBounds(580, 230, 200, 200);
        devam.setText("Seç !");
        devam.setFont(skorFont);
        devam.setBackground(Color.DARK_GRAY);
        devam.setForeground(Color.orange);
        devam.setVisible(false);
        f.add(devam);
        
        liste = new JComboBox();
        liste.setBounds(470, 250, 200, 30);
        liste.setFont(myFont3);
        liste.setBackground(Color.orange);
        liste.setForeground(Color.black);
        liste.setVisible(false);
        f.add(liste);
        
        savas.setBounds(470,300,200, 50);
        savas.setFont(skorFont);
        savas.setText("Savaş !");
        savas.setBackground(Color.DARK_GRAY);
        savas.setForeground(Color.orange);
        savas.setVisible(false);
        f.add(savas);      
        
        secilen.setBounds(300,480,800,60);
        secilen.setFont(skorFont);
        secilen.setForeground(Color.yellow);
        f.add(secilen);
        
        JButton baslat = new JButton("Savaş Başlasın !");
        baslat.setBounds(500, 150, 200, 40);
        baslat.setFont(myFont3);
        baslat.setForeground(Color.green);
        baslat.setBackground(Color.DARK_GRAY);
        f.add(baslat);
        baslat.setEnabled(false);
                       
        btn.addActionListener(new ActionListener(){ // pc - pc seçilirse
            @Override
            public void actionPerformed(ActionEvent e){                                
                
                oyun.com1++;
                oyun.com2++;                
                
                try {
                    oyun.dosyaYaz("Bilgisayar - Bilgisayar Karşılaşması Seçildi.\n\n");
                } catch (IOException ex) {
                    Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                pcAdi1.setText("Computer 1");
                pcAdi2.setText("Computer 2");
                
                skor1.setVisible(true);
                skor2.setVisible(true);                
                
                oyun.bilgisayar1.oyuncuAdi = "Computer 1";
                oyun.bilgisayar2.oyuncuAdi = "Computer 2";
                
                img5.setVisible(true);
                img6.setVisible(true);
                
                btn2.setEnabled(false);
                btn.setEnabled(false);
                sayi.setVisible(true);
                btn3.setVisible(true);
                img2.setVisible(true);
               
                oyunBaslasin++;               
           }
        });
        
        btn2.addActionListener(new ActionListener(){ // kullanıcı - pc seçilirse
            @Override
            public void actionPerformed(ActionEvent e) {                                
                
                oyun.com1++;
                oyun.mainPlayer++;
                
                try {
                    oyun.dosyaYaz("Bilgisayar - Kullanıcı Karşılaşması Seçildi.\n\n");
                } catch (IOException ex) {
                    Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                skor1.setVisible(true);
                skor2.setVisible(true);     
                
                pcAdi1.setText("Computer 1");
                
                img1.setVisible(true);
                img6.setVisible(true);
                
                btn.setEnabled(false);
                btn2.setEnabled(false);
                sayi.setVisible(true);
                btn3.setVisible(true);
                img2.setVisible(true);
                btn4.setVisible(true);
                kullanici.setVisible(true);
                    
            }
        });                              
        
        btn3.addActionListener(new ActionListener(){ // onayla seçilirse set sayısı için
            @Override
            public void actionPerformed(ActionEvent e) {
                
                oyun.setSayisi=Integer.parseInt(sayi.getText()); // set sayisi burada
                System.out.println("Oyun "+oyun.setSayisi+" el oynanacak.");
                sayi.setEnabled(false);
                btn3.setEnabled(false);
                
                try {
                    oyun.dosyaYaz("Oyun en fazla "+oyun.setSayisi+" el oynanacak. (Önceden Kazanan Olmazsa)\n\n");
                    oyun.dosyaYaz("------Maç Başlıyor !-------\n\n");
                } catch (IOException ex) {
                    Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(kullanici.isEnabled()==true){ // kullanici - pc seçildiyse bu yazi alani aktiftir.
                    btn4.setEnabled(true);
                }                   
                
                oyunBaslasin++;
                
                if(oyunBaslasin==2)
                   baslat.setEnabled(true);
            }
        });
        
        btn4.addActionListener(new ActionListener(){ // onayla kullanici adi
            @Override
            public void actionPerformed(ActionEvent e) {
                                               
                oyun.kullanici.oyuncuAdi = kullanici.getText();
                System.out.println("Oyuna Hoşgeldin "+oyun.kullanici.oyuncuAdi);                                                              
                btn4.setEnabled(false);
                kullanici.setEnabled(false);
                
                try {
                    oyun.dosyaYaz("Oyuna Hoşgeldin "+oyun.kullanici.oyuncuAdi+"\n\n");
                } catch (IOException ex) {
                    Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                user.setText(oyun.kullanici.oyuncuAdi); 
                
                oyunBaslasin++;
                
                if(oyunBaslasin==2)
                   baslat.setEnabled(true);
           }
        });                                         
                  
        resim1.setBounds(300,200,150,150);
        f.add(resim1);
        
        resim2.setBounds(710,200,150,150);
        f.add(resim2);
        
        nesne1.setBounds(350, 350, 200, 50);
        nesne1.setFont(myFont3);
        f.add(nesne1);
        
        nesne2.setBounds(760, 350, 200, 50);
        nesne2.setFont(myFont3);
        f.add(nesne2);
        
        baslat.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                synchronized (baslat) {
                    baslat.notify();
                }
            }            
        });
        
        JPanel p1 = new JPanel();
        p1.setBackground(Color.DARK_GRAY);
        p1.setBounds(0,0,200,600);
        f.add(p1);
        
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(50,200,100,150));
        p2.setBounds(210,10,765,115);
        f.add(p2);
                  
        JLabel p = new JLabel(new ImageIcon(stars));
        p.setBounds(0,0,1000,600);
        f.add(p);
            
        f.pack();
        f.setLocationRelativeTo(null);
        f.setSize(1000,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLayout(null);
        
        synchronized(baslat) {
            try 
            {
                baslat.wait();
            } 
        
            catch (InterruptedException ex) 
            {
                System.out.println("Interrupted");
            }
        }   
             
        baslat.setVisible(false);
        baslat.setVisible(false);             
                
        System.out.println("Oyun Başladı"); //Oyun Burada Başlayacak              
                               
        oyun.startGame();                                          
                
        for (int i = 0; i < 5; i++) {
            System.out.println(oyun.bilgisayar1.nesneler[i]+" and "+oyun.bilgisayar2.nesneler[i]);
                }                              
            
        if(oyun.com1==1 && oyun.com2==1)
            kartSec(); 
        
        if(oyun.com1==1 && oyun.mainPlayer==1)
        {
            kullaniciBaslangic();
            kartSec2();
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
    
    public void kartSec() throws IOException //bilgisayar - bilgisayar karsilasmasi icin
    {           
        Random r1 = new Random(); 
        Random r2 = new Random();
        
        int secilen1=0, secilen2=0;        
        int isEmpty1=0, isEmpty2=0;
        
        for (int i=0; i<oyun.setSayisi; i++) 
        {   
            oyun.dosyaYaz("-> Round "+(i+1)+" <-\n\n");
                    
            isEmpty1=0;
            isEmpty2=0;
            bittiMi=0;
            
            if(i%5==0 && i!=0)
            {
                System.out.println(i);
                for (int j=0; j<5; j++) {
                    
                    if(oyun.bilgisayar1.nesneler[j]>0) // dayaniklilik < 0 durumunda eleme icin
                        oyun.bilgisayar1.nesneler[j]/=10;
                    
                    if(oyun.bilgisayar2.nesneler[j]>0)
                        oyun.bilgisayar2.nesneler[j]/=10;
                }
            }
            
            secilen1 = r1.nextInt(5); 
            secilen2 = r2.nextInt(5);  
            
            birinciSkor = (int) oyun.bilgisayar1.skorGoster();
            ikinciSkor = (int) oyun.bilgisayar2.skorGoster();
        
            oyun.dosyaYaz("1. Bilgisayarın Elindeki Nesneler: ");
            
            for (int j = 0; j < 5; j++) {
                    
                    if(oyun.bilgisayar1.nesneler[j]!=-1 && oyun.bilgisayar1.nesneler[j]%10!=0){
                        oyun.dosyaYaz(oyun.bilgisayar1.nesneListesi.get(j)+" ");
                    }                   
                }
            
            oyun.dosyaYaz("\n2. Bilgisayarın Elindeki Nesneler: ");
            
            for (int j = 0; j < 5; j++) {
                    
                    if(oyun.bilgisayar2.nesneler[j]!=-1 && oyun.bilgisayar2.nesneler[j]%10!=0){
                        oyun.dosyaYaz(oyun.bilgisayar2.nesneListesi.get(j)+" ");
                    }                   
                }
            
            oyun.dosyaYaz("\n\n"); //Buraya kadar forun içine sonradan eklendi. 23 Kasım
            
            while(oyun.bilgisayar1.nesneler[secilen1]%10==0 || oyun.bilgisayar1.nesneler[secilen1]==-1)
            {
                secilen1=r1.nextInt(5);
            }
            
            System.out.println();
            
             while(oyun.bilgisayar2.nesneler[secilen2]%10==0 || oyun.bilgisayar2.nesneler[secilen2]==-1) // dayaniklilik < 0 durumu kontrol edilebilir mi ?
             {
                 secilen2=r2.nextInt(5);
             }                                                                    
             
            int c1temp=delim(oyun.bilgisayar1.nesneListesi.get(secilen1));
            int c1temp2=hangiNesne(oyun.bilgisayar1.nesneListesi.get(secilen1));
            oyun.bilgisayar1.nesneler[secilen1]*=10;
            
            //temp2 nin arrayListinin temp-1. elemanı benim nesnem.
            
            switch (c1temp2) // BURADASIN 9 Kasim
            {
                case 1:
                    t1 = oyun.bilgisayar1.tasList.get(c1temp-1);
                    
                    if(t1.seviyePuani > 30)
                    {
                        c1temp2=4;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "AgirTas"+c1temp);
                        agirt1=oyun.bilgisayar1.agirTasList.get(c1temp-1);
                        agirt1.dayaniklilik=t1.dayaniklilik;
                        agirt1.seviyePuani=t1.seviyePuani;
                        resim1.setIcon(agirTasImage);
                        break;
                    }
                    
                    resim1.setIcon(tasImage);
                    break;
                    
                case 2:
                    k1 = oyun.bilgisayar1.kagitList.get(c1temp-1);
                    
                    if(k1.seviyePuani > 30)
                    {
                        c1temp2=5;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "OzelKagit"+c1temp);
                        ozelk1=oyun.bilgisayar1.ozelKagitList.get(c1temp-1);
                        ozelk1.dayaniklilik=k1.dayaniklilik;
                        ozelk1.seviyePuani=k1.seviyePuani;
                        resim1.setIcon(ozelKagitImage);
                        break;
                    }
                    
                    resim1.setIcon(kagitImage);
                    break;
                    
                case 3:
                    m1 = oyun.bilgisayar1.makasList.get(c1temp-1);
                    
                    if(m1.seviyePuani > 30)
                    {
                        c1temp2=6;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "UstaMakas"+c1temp);
                        ustam1=oyun.bilgisayar1.ustaMakasList.get(c1temp-1);
                        ustam1.dayaniklilik=m1.dayaniklilik;
                        ustam1.seviyePuani=m1.seviyePuani;
                        resim1.setIcon(ustaMakasImage);
                        break;
                    }
                    
                    resim1.setIcon(makasImage);
                    break;
            }     
            
            System.out.println(oyun.bilgisayar1.nesneListesi.get(secilen1)+" Seçildi.");
            oyun.dosyaYaz(oyun.bilgisayar1.nesneListesi.get(secilen1)+" Seçildi.\n");
            
            nesne1.setText(oyun.bilgisayar1.nesneListesi.get(secilen1));
            nesne1.setForeground(Color.cyan);

            int c2temp=delim(oyun.bilgisayar2.nesneListesi.get(secilen2));
            int c2temp2=hangiNesne(oyun.bilgisayar2.nesneListesi.get(secilen2));
            oyun.bilgisayar2.nesneler[secilen2]*=10;
            
            //temp2 nin arrayListinin temp-1. elemanı benim nesnem.
            
            switch (c2temp2) // BURADASIN 9 Kasim
            {
                case 1:
                    t2 = oyun.bilgisayar2.tasList.get(c2temp-1);
                    
                    if(t2.seviyePuani > 30)
                    {
                        c2temp2=4;
                        oyun.bilgisayar2.nesneListesi.set(secilen2, "AgirTas"+c2temp);
                        agirt2=oyun.bilgisayar2.agirTasList.get(c2temp-1);
                        agirt2.dayaniklilik=t2.dayaniklilik;
                        agirt2.seviyePuani=t2.seviyePuani;
                        resim2.setIcon(agirTasImage);
                        break;
                    }
                    
                    resim2.setIcon(tasImage);
                    break;
                    
                case 2:
                    k2 = oyun.bilgisayar2.kagitList.get(c2temp-1);
                    
                    if(k2.seviyePuani > 30)
                    {
                        c2temp2=5;
                        oyun.bilgisayar2.nesneListesi.set(secilen2, "OzelKagit"+c2temp);
                        ozelk2=oyun.bilgisayar2.ozelKagitList.get(c2temp-1);
                        ozelk2.dayaniklilik=k2.dayaniklilik;
                        ozelk2.seviyePuani=k2.seviyePuani;
                        resim2.setIcon(ozelKagitImage);
                        break;
                    }
                    
                    resim2.setIcon(kagitImage);
                    break;
                case 3:
                    m2 = oyun.bilgisayar2.makasList.get(c2temp-1);
                    
                     if(m2.seviyePuani > 30)
                    {
                        c2temp2=6;
                        oyun.bilgisayar2.nesneListesi.set(secilen2, "UstaMakas"+c2temp);
                        ustam2=oyun.bilgisayar2.ustaMakasList.get(c2temp-1);
                        ustam2.dayaniklilik=m2.dayaniklilik;
                        ustam2.seviyePuani=m2.seviyePuani;
                        resim2.setIcon(ustaMakasImage);
                        break;
                    }
                    
                    resim2.setIcon(makasImage);
                    break;
            }
                 
            System.out.println(oyun.bilgisayar2.nesneListesi.get(secilen2)+" Seçildi.");
            oyun.dosyaYaz(oyun.bilgisayar2.nesneListesi.get(secilen2)+" Seçildi.\n\n");
            
            nesne2.setText(oyun.bilgisayar2.nesneListesi.get(secilen2));
            nesne2.setForeground(Color.cyan);
            
            oyun.kapistir(c1temp, c1temp2, c2temp, c2temp2, secilen1, secilen2);
            
            //Buradan sonra ornegin t1 ile m2 savaşacak. // ilk önce keskinlik vb. sonra temp2 (double - int)                       
                                
            
            for (int j = 0; j < 5; j++) {
                System.out.print(oyun.bilgisayar1.nesneler[j]+" ");
            }
            
            System.out.println("\ni degeri: "+i);
            
            for (int j = 0; j < 5; j++) {
                System.out.print(oyun.bilgisayar2.nesneler[j]+" ");
            }
            
            //buradan sonrası butona tıklanana kadar beklenmesi icin
            //butona bastıktan sonra kodun devamı çalışacak
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int j = 0; j < 5; j++) 
            {
                if(oyun.bilgisayar1.nesneler[j]==-1 || oyun.bilgisayar1.nesneler[j]%10==0)              
                    isEmpty1++;
                
                if(oyun.bilgisayar2.nesneler[j]==-1 || oyun.bilgisayar2.nesneler[j]%10==0)              
                    isEmpty2++;         
            }
              
            if(isEmpty1==5 && isEmpty2!=5) // birincinin kartları bitti
            {
                System.out.println("\n\n2nd Computer is Winner ! Congrulations !");
                oyun.dosyaYaz("Birinci Bilgisayarın Elinde Nesne Kalmadı !\n");
                oyun.dosyaYaz("2nd Computer is Winner ! Congrulations !\n\n");
                bittiMi=1;
                winner=2;
                break;
            }
            
            else if(isEmpty1!=5 && isEmpty2==5) // ikincinin kartları bitti
            {
                System.out.println("\n\n1st Computer is Winner ! Congrulations !");
                oyun.dosyaYaz("İkinci Bilgisayarın Elinde Nesne Kalmadı !\n");
                oyun.dosyaYaz("1st Computer is Winner ! Congrulations !\n\n");
                bittiMi=1;
                winner=1;
                break;
            }
            
            else if(isEmpty1==5 && isEmpty2==5 && (i+1)%5!=0) // oyun bitmeden ikisinin de kartları bittiyse
            {
                bittiMi=0;
                break;
            }
            
            System.out.println("\n");
            
            System.out.println(oyun.bilgisayar1.nesneListesi);
            System.out.println(oyun.bilgisayar2.nesneListesi);  
            
            System.out.println("\n");
            
            System.out.println(oyun.bilgisayar1.skorGoster());
            System.out.println(oyun.bilgisayar2.skorGoster());
            
            birinciSkor = (int) oyun.bilgisayar1.skorGoster();
            ikinciSkor = (int) oyun.bilgisayar2.skorGoster();
            
            skor1.setText(Integer.toString(birinciSkor));
            skor2.setText(Integer.toString(ikinciSkor));
            
            oyun.dosyaYaz("Bilgisayar 1: "+Integer.toString(birinciSkor)+"\n");
            oyun.dosyaYaz("Bilgisayar 2: "+Integer.toString(ikinciSkor)+"\n\n");
        }  
        
        //for dongusunden burada çıkıyorum. Eğer Kartlar bitmemişse buradan itibaren yaz.
        
        resim1.setVisible(false);
        resim2.setVisible(false);
        nesne1.setVisible(false);
        nesne2.setVisible(false);
        
        if(oyun.bilgisayar1.skorGoster() > oyun.bilgisayar2.skorGoster() && bittiMi!=1)
        {
            System.out.println("\n\nComputer 1 is Winner !");
            oyun.dosyaYaz("1st Computer is Winner ! Congrulations !\n\n");
            winner=1;
        }
        
        else if(oyun.bilgisayar1.skorGoster() < oyun.bilgisayar2.skorGoster() && bittiMi!=1)
        {
            System.out.println("\n\nComputer 2 is Winner !");
            oyun.dosyaYaz("2nd Computer is Winner ! Congrulations !\n\n");
            winner=2;
        }

        else if(oyun.bilgisayar1.skorGoster() == oyun.bilgisayar2.skorGoster() && bittiMi!=1)
        {
            System.out.println("Berabere !");
            oyun.dosyaYaz("Maç Berabere Bitti. Dostluk Kazandı !\n\n");
        }
        
       if(winner==1)
       {
           kupa.setVisible(true);
           kazanan.setText("Computer 1 Wins !");
           kazanan.setVisible(true);
       }
       
       if(winner==2)
       {
           kupa.setVisible(true);
           kazanan.setText("Computer 2 Wins !");
           kazanan.setVisible(true);
       }
       
       if(winner==0)
       {
           berabere.setVisible(true);
           kazanan.setForeground(Color.gray);
           kazanan.setText("                Draw !");
           kazanan.setVisible(true);
       }
        
       System.out.println(oyun.bilgisayar1.nesneListesi);
       System.out.println(oyun.bilgisayar2.nesneListesi);
        
    }
 
    //buradan sonra ek fonsiyonlar var.
    
   
    int next=0;
    
    public void kullaniciBaslangic()
    {
        int tIndex=0, kIndex=0, mIndex=0, u=0;
        
        tasBtn.setVisible(true);
        kagitBtn.setVisible(true);
        makasBtn.setVisible(true);
        devam.setVisible(true);
        
        while(true)
        {   
         devam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (devam) {
                    devam.notify();
                }
            }
        });
        
        synchronized(devam) {
            try 
            {
                devam.wait();
            } 
        
            catch (InterruptedException ex) 
            {
                System.out.println("Interrupted");
            }
        }          
        
        next++;
        
        if(tasBtn.isSelected())
        {
            System.out.println("Tas Secildi");
            oyun.kullanici.nesneListesi.add("Tas"+(tIndex+1));
            oyun.kullanici.nesneler[u]=1;
            System.out.println(oyun.kullanici.nesneler[tIndex]+" and "+tIndex);
            u++;
            tIndex++;
        }
        
        else if(kagitBtn.isSelected())
        {
            System.out.println("Kagit Secildi");
            oyun.kullanici.nesneListesi.add("Kagit"+(kIndex+1));
            oyun.kullanici.nesneler[u]=2;
            System.out.println(oyun.kullanici.nesneler[kIndex]+" and "+kIndex);
            u++;
            kIndex++;
        }
        
        else if(makasBtn.isSelected())
        {
            System.out.println("Makas Secildi");
            oyun.kullanici.nesneListesi.add("Makas"+(mIndex+1));
            oyun.kullanici.nesneler[u]=3;
            System.out.println(oyun.kullanici.nesneler[mIndex]+" and "+mIndex);
            u++;
            mIndex++;
        }
        
            System.out.println(oyun.kullanici.nesneListesi);
        
            secilen.setText(oyun.kullanici.nesneListesi.toString());
            
        if(next==5)
        {
            tasBtn.setVisible(false);
            kagitBtn.setVisible(false);
            makasBtn.setVisible(false);
            devam.setVisible(false);
            
            for (int i = 0; i < 5; i++) {
                System.out.println(oyun.kullanici.nesneler[i]+" ");
            }
            
            break;
        }
       
        }
        
    }
    
    public int kullaniciSecilen()
    {
        savas.setVisible(true);
        liste.setVisible(true);
        
        String[] arr = new String[5];
        liste.removeAllItems();
        
        for (int i = 0; i < 5; i++) {
            
            if(oyun.kullanici.nesneler[i]!=-1 && oyun.kullanici.nesneler[i]%10!=0)
            {
                arr[i]=oyun.kullanici.nesneListesi.get(i);
                liste.addItem(arr[i]);
            }
            
            else
                liste.addItem("Seçildi");
        }
        
        savas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (savas) {
                    savas.notify();
                }
            }
        });
        
        synchronized(savas) {
            try 
            {
                savas.wait();
            } 
        
            catch (InterruptedException ex) 
            {
                System.out.println("Interrupted");
            }
        }
        
        return liste.getSelectedIndex();
        
    }
    
    public void kartSec2() throws IOException //bilgisayar - kullanici karsilasmasi icin
    {           
        Random r1 = new Random(); 
        
        int secilen1=0, secilen2=0;   
        int isEmpty1=0, isEmpty2=0;
        
        for (int i=0; i<oyun.setSayisi; i++) 
        {   
            oyun.dosyaYaz("-> Round "+(i+1)+" <-\n\n");
 
            savas.setVisible(true);
            liste.setVisible(true);
            
            isEmpty1=0;
            isEmpty2=0;
            bittiMi=0;
            
            if(i%5==0 && i!=0)
            {
                System.out.println(i);
                for (int j=0; j<5; j++) {
                    
                    if(oyun.bilgisayar1.nesneler[j]>0) // dayaniklilik < 0 durumunda eleme icin
                        oyun.bilgisayar1.nesneler[j]/=10;
                    
                    if(oyun.kullanici.nesneler[j]>0)
                        oyun.kullanici.nesneler[j]/=10;
                }
            }
            
            secilen1 = r1.nextInt(5); 
            secilen2=kullaniciSecilen();
            
            birinciSkor = (int) oyun.bilgisayar1.skorGoster();
            ikinciSkor = (int) oyun.kullanici.skorGoster();     
     
            oyun.dosyaYaz("1. Bilgisayarın Elindeki Nesneler: ");
            
            for (int j = 0; j < 5; j++) {
                    
                    if(oyun.bilgisayar1.nesneler[j]!=-1 && oyun.bilgisayar1.nesneler[j]%10!=0){
                        oyun.dosyaYaz(oyun.bilgisayar1.nesneListesi.get(j)+" ");
                    }                   
                }
            
            oyun.dosyaYaz("\n"+oyun.kullanici.oyuncuAdi+" Oyuncusunun Elindeki Nesneler: ");
            
            for (int j = 0; j < 5; j++) {
                    
                    if(oyun.kullanici.nesneler[j]!=-1 && oyun.kullanici.nesneler[j]%10!=0){
                        oyun.dosyaYaz(oyun.kullanici.nesneListesi.get(j)+" ");
                    }                   
                }
            
            oyun.dosyaYaz("\n\n");
            
            while(oyun.bilgisayar1.nesneler[secilen1]%10==0 || oyun.bilgisayar1.nesneler[secilen1]==-1)
            {
                secilen1=r1.nextInt(5);
            }
            
            System.out.println();
            
             while(oyun.kullanici.nesneler[secilen2]%10==0 || oyun.kullanici.nesneler[secilen2]==-1) // dayaniklilik < 0 durumu kontrol edilebilir mi ?
             {
                 secilen2=kullaniciSecilen();
             }                                                                   
             
            int c1temp=delim(oyun.bilgisayar1.nesneListesi.get(secilen1));
            int c1temp2=hangiNesne(oyun.bilgisayar1.nesneListesi.get(secilen1));
            oyun.bilgisayar1.nesneler[secilen1]*=10;
            
            //temp2 nin arrayListinin temp-1. elemanı benim nesnem.
            
            switch (c1temp2) // BURADASIN 9 Kasim
            {
                case 1:
                    t1 = oyun.bilgisayar1.tasList.get(c1temp-1);
                    
                    if(t1.seviyePuani > 30)
                    {
                        c1temp2=4;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "AgirTas"+c1temp);
                        agirt1=oyun.bilgisayar1.agirTasList.get(c1temp-1);
                        agirt1.dayaniklilik=t1.dayaniklilik;
                        agirt1.seviyePuani=t1.seviyePuani;
                        resim1.setIcon(agirTasImage);
                        break;
                    }
                    
                    resim1.setIcon(tasImage);
                    break;
                    
                case 2:
                    k1 = oyun.bilgisayar1.kagitList.get(c1temp-1);
                    
                    if(k1.seviyePuani > 30)
                    {
                        c1temp2=5;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "OzelKagit"+c1temp);
                        ozelk1=oyun.bilgisayar1.ozelKagitList.get(c1temp-1);
                        ozelk1.dayaniklilik=k1.dayaniklilik;
                        ozelk1.seviyePuani=k1.seviyePuani;
                        resim1.setIcon(ozelKagitImage);
                        break;
                    }
                    
                    resim1.setIcon(kagitImage);
                    break;
                    
                case 3:
                    m1 = oyun.bilgisayar1.makasList.get(c1temp-1);
                    
                    if(m1.seviyePuani > 30)
                    {
                        c1temp2=6;
                        oyun.bilgisayar1.nesneListesi.set(secilen1, "UstaMakas"+c1temp);
                        ustam1=oyun.bilgisayar1.ustaMakasList.get(c1temp-1);
                        ustam1.dayaniklilik=m1.dayaniklilik;
                        ustam1.seviyePuani=m1.seviyePuani;
                        resim1.setIcon(ustaMakasImage);
                        break;
                    }
                    
                    resim1.setIcon(makasImage);
                    break;
            }     
            
            System.out.println(oyun.bilgisayar1.nesneListesi.get(secilen1)+" Seçildi.");
            oyun.dosyaYaz(oyun.bilgisayar1.nesneListesi.get(secilen1)+" Seçildi.\n");
            
            nesne1.setText(oyun.bilgisayar1.nesneListesi.get(secilen1));
            nesne1.setForeground(Color.cyan);

            int c2temp=delim(oyun.kullanici.nesneListesi.get(secilen2));
            int c2temp2=hangiNesne(oyun.kullanici.nesneListesi.get(secilen2));
            oyun.kullanici.nesneler[secilen2]*=10;
            
            //temp2 nin arrayListinin temp-1. elemanı benim nesnem.
            
            switch (c2temp2) // BURADASIN 9 Kasim
            {
                case 1:
                    t2 = oyun.kullanici.tasList.get(c2temp-1);
                    
                    if(t2.seviyePuani > 30)
                    {
                        c2temp2=4;
                        oyun.kullanici.nesneListesi.set(secilen2, "AgirTas"+c2temp);
                        agirt2=oyun.kullanici.agirTasList.get(c2temp-1);
                        agirt2.dayaniklilik=t2.dayaniklilik;
                        agirt2.seviyePuani=t2.seviyePuani;
                        resim2.setIcon(agirTasImage);
                        break;
                    }
                    
                    resim2.setIcon(tasImage);
                    break;
                    
                case 2:
                    k2 = oyun.kullanici.kagitList.get(c2temp-1);
                    
                    if(k2.seviyePuani > 30)
                    {
                        c2temp2=5;
                        oyun.kullanici.nesneListesi.set(secilen2, "OzelKagit"+c2temp);
                        ozelk2=oyun.kullanici.ozelKagitList.get(c2temp-1);
                        ozelk2.dayaniklilik=k2.dayaniklilik;
                        ozelk2.seviyePuani=k2.seviyePuani;
                        resim2.setIcon(ozelKagitImage);
                        break;
                    }
                    
                    resim2.setIcon(kagitImage);
                    break;
                case 3:
                    m2 = oyun.kullanici.makasList.get(c2temp-1);
                    
                     if(m2.seviyePuani > 30)
                    {
                        c2temp2=6;
                        oyun.kullanici.nesneListesi.set(secilen2, "UstaMakas"+c2temp);
                        ustam2=oyun.kullanici.ustaMakasList.get(c2temp-1);
                        ustam2.dayaniklilik=m2.dayaniklilik;
                        ustam2.seviyePuani=m2.seviyePuani;
                        resim2.setIcon(ustaMakasImage);
                        break;
                    }
                    
                    resim2.setIcon(makasImage);
                    break;
            }
                 
            System.out.println(oyun.kullanici.nesneListesi.get(secilen2)+" Seçildi.");
            oyun.dosyaYaz(oyun.kullanici.nesneListesi.get(secilen2)+" Seçildi.\n\n");
            
            nesne2.setText(oyun.kullanici.nesneListesi.get(secilen2));
            nesne2.setForeground(Color.cyan);
            
            oyun.kapistir2(c1temp, c1temp2, c2temp, c2temp2, secilen1, secilen2);
            
            //Buradan sonra ornegin t1 ile m2 savaşacak. // ilk önce keskinlik vb. sonra temp2 (double - int)                       
                                
            
            for (int j = 0; j < 5; j++) {
                System.out.print(oyun.bilgisayar1.nesneler[j]+" ");
            }
            
            System.out.println("\ni degeri: "+i);
            
            for (int j = 0; j < 5; j++) {
                System.out.print(oyun.kullanici.nesneler[j]+" ");
            }
            
            //buradan sonrası butona tıklanana kadar beklenmesi icin
            //butona bastıktan sonra kodun devamı çalışacak
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (int j = 0; j < 5; j++) 
            {
                if(oyun.bilgisayar1.nesneler[j]==-1 || oyun.bilgisayar1.nesneler[j]%10==0)              
                    isEmpty1++;
                
                if(oyun.kullanici.nesneler[j]==-1 || oyun.kullanici.nesneler[j]%10==0)              
                    isEmpty2++;         
            }
              
            if(isEmpty1==5 && isEmpty2!=5) // birincinin kartları bitti
            {
                System.out.println("\n\n"+oyun.kullanici.oyuncuAdi+" is Winner ! Congrulations !");  
                oyun.dosyaYaz("Bilgisayarın Elinde Nesne Kalmadı !\n");
                oyun.dosyaYaz(oyun.kullanici.oyuncuAdi+" is Winner ! Congrulations !\n\n");
                
                bittiMi=1;
                winner=2;
                break;
            }
            
            else if(isEmpty1!=5 && isEmpty2==5) // ikincinin kartları bitti
            {
                System.out.println("\n\nComputer is Winner ! Congrulations !");
                oyun.dosyaYaz(oyun.kullanici.oyuncuAdi+" Oyuncusunun Elinde Nesne Kalmadı !\n");
                oyun.dosyaYaz("Computer is Winner ! Congrulations !\n\n");
                
                bittiMi=1;
                winner=1;
                break;
            }
            
            else if(isEmpty1==5 && isEmpty2==5 && (i+1)%5!=0) // oyun bitmeden ikisinin de kartları bittiyse
            {
                bittiMi=0;
                break;
            }
            
            System.out.println("\n");
            
            System.out.println(oyun.bilgisayar1.nesneListesi);
            System.out.println(oyun.kullanici.nesneListesi);  
            
            System.out.println("\n");
            
            System.out.println(oyun.bilgisayar1.skorGoster());
            System.out.println(oyun.kullanici.skorGoster());
            
            birinciSkor = (int) oyun.bilgisayar1.skorGoster();
            ikinciSkor = (int) oyun.kullanici.skorGoster();
                      
            skor1.setText(Integer.toString(birinciSkor));
            skor2.setText(Integer.toString(ikinciSkor));
            
            oyun.dosyaYaz("Bilgisayar 1: "+Integer.toString(birinciSkor)+"\n");
            oyun.dosyaYaz(oyun.kullanici.oyuncuAdi+": "+Integer.toString(ikinciSkor)+"\n\n");
        }  
        
        //for dongusunden burada çıkıyorum. Eğer Kartlar bitmemişse buradan itibaren yaz.
        
        resim1.setVisible(false);
        resim2.setVisible(false);
        nesne1.setVisible(false);
        nesne2.setVisible(false);
        
        savas.setVisible(false);
        liste.setVisible(false);
        secilen.setVisible(false);
        
        if(oyun.bilgisayar1.skorGoster() > oyun.kullanici.skorGoster() && bittiMi!=1)
        {
            System.out.println("\n\nComputer is Winner !");
            oyun.dosyaYaz("Computer is Winner ! Congrulations !\n\n");           
            winner=1;
        }
        
        else if(oyun.bilgisayar1.skorGoster() < oyun.kullanici.skorGoster() && bittiMi!=1)
        {
            System.out.println("\n\n"+oyun.kullanici.oyuncuAdi+" is Winner !");
            oyun.dosyaYaz(oyun.kullanici.oyuncuAdi+" is Winner !\n\n");
            winner=2;
        }

        else if(oyun.bilgisayar1.skorGoster() == oyun.kullanici.skorGoster() && bittiMi!=1)
        {
            System.out.println("Berabere !");
            oyun.dosyaYaz("Maç Berabere Bitti. Dostluk Kazandı !\n\n");
        }
        
       if(winner==1)
       {
           kupa.setVisible(true);
           kazanan.setText("Computer 1 Wins !");
           kazanan.setVisible(true);
       }
       
       if(winner==2)
       {
           kupa.setVisible(true);
           kazanan.setText(oyun.kullanici.oyuncuAdi+" Wins !");
           kazanan.setVisible(true);
       }
       
       if(winner==0)
       {
           berabere.setVisible(true);
           kazanan.setForeground(Color.gray);
           kazanan.setText("                Draw !");
           kazanan.setVisible(true);
       }
        
       System.out.println(oyun.bilgisayar1.nesneListesi);
       System.out.println(oyun.kullanici.nesneListesi);
    }
    
    public Integer delim(String str)
    {
        int temp=0;      
        char c = str.charAt(str.length()-1);
        String s = String.valueOf(c);
                       
        temp = Integer.parseInt(s);
        
        return temp;
    }
   
    public Integer hangiNesne(String str)
    {
        int temp=0;
        
        if(str.substring(0, str.length()-1).equals("Tas") || str.substring(0, str.length()-1).equals("AgirTas"))
            return 1;
        
        if(str.substring(0, str.length()-1).equals("Kagit") || str.substring(0, str.length()-1).equals("OzelKagit"))
            return 2;
        
        if(str.substring(0, str.length()-1).equals("Makas") || str.substring(0, str.length()-1).equals("UstaMakas"))
            return 3;
        
        return temp;
    }
    
   
}

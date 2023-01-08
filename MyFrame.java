//kkkk

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class MyFrame extends JFrame implements ActionListener{


    //rozmiar okna
    final int szerokosc = 1280;
    final int wysokosc = 720;
    //-----------------------
    //generowanie wsp pojawienia sie celu
    Random xlos = new Random();
    Random ylos = new Random();
    int x = xlos.nextInt(1152);
    int y= ylos.nextInt(492)+100;
    //---------------------------------
    float pkt = 0; //ilosc trafien uzyskanych
    float klik = 0; //ilosc prob trafienia
    float skutecznosc;
    boolean started = false; //czy gra trwa
    int lcelow =20; //ile celow pojawi sie podczas rozgrywki (zalezy od trudnosci)
    //rozmiar celu (zalezy od poziomu trudnosci domyslnie normalny)
    int celszer=64;
    int celwys=64;
    Timer timer;
    float czas;
    String[] poziom={"Normalny","Łatwy","Trudny"};
    JLabel label;
    JComboBox poziomt;

    //-------------------------------------------

        MyFrame(){
            //ustawienie okna gry
            this.setSize(szerokosc,wysokosc);
            this.setLayout(null);
            this.setResizable(true);
            this.setBackground(Color.white);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Celuj!");
            //----------------------------

            //menu
            JPanel menu = new JPanel();
            menu.setBounds(0,0,1280,100);
            menu.setLayout(null);
            menu.setBackground(Color.GRAY);
            menu.setOpaque(true);
            this.add(menu);
            //------------------------------------

            //części menu
            JButton start = new JButton();
            start.setText("START");
            start.setBounds(300,8,120,50);
            start.setBackground(Color.LIGHT_GRAY);
            menu.add(start);

            JButton stop = new JButton();
            stop.setText("PRZERWIJ");
            stop.setBounds(425,8,120,50);
            stop.setBackground(Color.LIGHT_GRAY);
            menu.add(stop);

            JLabel punkty = new JLabel();
            punkty.setBounds(0,66,1280,32);
            punkty.setBackground(Color.LIGHT_GRAY);
            punkty.setHorizontalAlignment(SwingConstants.CENTER);
            punkty.setVerticalAlignment(SwingConstants.CENTER);
            punkty.setOpaque(true);
            //--------------------------------------

            //wyswietlanie skutecznosci po wlaczeniu gry
            if(klik == 0){

                punkty.setText("Wynik");
            }
            else {
                skutecznosc = (100 * pkt) / klik;

                punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
            }

            menu.add(punkty);
            punkty.setVisible(true);

            JButton exit = new JButton();
            exit.setText("WYJDŹ");
            exit.setBounds(1100,8,120,50);
            exit.setBackground(Color.LIGHT_GRAY);
            menu.add(exit);

            poziomt= new JComboBox(poziom);
            poziomt.setBounds(700,8,120,50);
            poziomt.setBackground(Color.LIGHT_GRAY);
            poziomt.addActionListener(this);
            menu.add(poziomt);
            //-------------------------------------------
            timer = new Timer(60,this);
            //cel
            label = new JLabel();
            this.add(label);
            label.setBackground(Color.black);
            label.setOpaque(true);
            label.setSize(celszer,celwys);
            //----------------------------------------


            this.setLocationRelativeTo(null);
            this.setVisible(true);


            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                   if(started == true) {
                       klik++;
                       if (klik == 0) {

                           punkty.setText("Skutecznosc: "+ " %" + "Średnie opóźnienie: " +  " ms");
                       } else {
                           skutecznosc = (100 * pkt) / klik;

                           punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
                       }


                   }

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            label.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                    if (pkt < lcelow-1){
                        x = xlos.nextInt(1152);
                        y = ylos.nextInt(492) + 100;
                        label.setLocation(x, y);

                        pkt++;
                        klik++;
                        if (klik == 0) {

                            punkty.setText("Wynik");
                        } else {
                            skutecznosc = (100 * pkt) / klik;

                            punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
                        }
                    }
                    else {
                        System.out.println("winek");
                        started=false;
                        label.setVisible(false);
                    }




                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            start.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    czas=0;
                    timer.start();
                    label.setVisible(true);
                    label.setLocation(x,y);
                    pkt=0;
                    klik=0;
                    if(klik == 0){

                        punkty.setText("Skutecznosc: "+ " %" + "Średnie opóźnienie: " + " ms");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
                    }
                    started=true;
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            stop.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pkt =0;
                    klik=0;
                    label.setVisible(false);
                    started = false;
                    timer.stop();
                    if(klik == 0){

                        punkty.setText("Wynik");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            exit.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }


    @Override
    public void actionPerformed(ActionEvent e) {
        czas=czas+60;
        if(e.getSource()==poziomt){
            System.out.println(poziomt.getSelectedItem());
            if(poziomt.getSelectedItem()=="Normalny"){
                lcelow=20;
                celwys=64;
                celszer=64;
                label.setSize(celwys,celszer);

            }
            else if(poziomt.getSelectedItem()=="Łatwy"){
                lcelow=10;
                celwys=96;
                celszer=96;
                label.setSize(celwys,celszer);

            }
            else if(poziomt.getSelectedItem()=="Trudny"){
                lcelow=25;
                celwys=32;
                celszer=32;
                label.setSize(celwys,celszer);

            }
        }
    }
}
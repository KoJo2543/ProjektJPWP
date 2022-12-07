//kkkk
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;


public class MyFrame extends JFrame{


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
    int lcelow =5; //ile celow pojawi sie podczas rozgrywki (zalezy od trudnosci)
    //rozmiar celu (zalezy od poziomu trudnosci domyslnie normalny)
    int celszer=64;
    int celwys=64;
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
            menu.setBackground(Color.GRAY);
            menu.setOpaque(true);
            this.add(menu);
            //------------------------------------

            //części menu
            JButton start = new JButton();
            start.setText("START");
            start.setBounds(0,0,64,32);
            menu.add(start);

            JButton stop = new JButton();
            stop.setText("PRZERWIJ");
            stop.setBounds(0,33,64,32);
            menu.add(stop);

            JLabel punkty = new JLabel();
            punkty.setBounds(0,0,128,128);
            punkty.setBackground(Color.white);
            punkty.setOpaque(true);
            //--------------------------------------

            //wyswietlanie skutecznosci po wlaczeniu gry
            if(klik == 0){

                punkty.setText("Skutecznosc: %");
            }
            else {
                skutecznosc = (100 * pkt) / klik;

                punkty.setText("Skutecznosc: "+skutecznosc + " %");
            }

            menu.add(punkty);
            punkty.setVisible(true);
            //-------------------------------------------

            //cel
            JLabel label = new JLabel();
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

                           punkty.setText("Skutecznosc: %");
                       } else {
                           skutecznosc = (100 * pkt) / klik;

                           punkty.setText("Skutecznosc: " + skutecznosc + " %");
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

                            punkty.setText("Skutecznosc: %");
                        } else {
                            skutecznosc = (100 * pkt) / klik;

                            punkty.setText("Skutecznosc: " + skutecznosc + " %");
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
                    label.setVisible(true);
                    label.setLocation(x,y);
                    pkt=0;
                    klik=0;
                    if(klik == 0){

                        punkty.setText("Skutecznosc: %");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %");
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
                    if(klik == 0){

                        punkty.setText("Skutecznosc: %");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %");
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

        }


}



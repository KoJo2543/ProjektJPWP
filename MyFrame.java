/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * Copyright 2023 Gdansk University of Technology,
 * Dept. of Electronics and telecommunication engineering, Gdansk, Poland
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted without written permission from the copyright
 * holders.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/** klasa zawierajaca tworzenie okna gry oraz rozgrywki
 * @author Konrad Cieślak
 */
public class MyFrame extends JFrame implements ActionListener{


    /** szerokosc okna */
    final int szerokosc = 1280;
    /** wysokosc okna */
    final int wysokosc = 720;
    /** zmienna pomocnicza do losowania x celu */
    Random xlos = new Random();
    /** zmienna pomocnicza do losowania y celu */
    Random ylos = new Random();
    /** losowanie wspolrzednej x celu */
    int x = xlos.nextInt(1152);
    /** losowanie wspolrzednej y celu */
    int y= ylos.nextInt(492)+100;
    /** liczba punktow punktow */
    float pkt = 0;
    /** liczba klikniec */
    float klik = 0;
    /**  skutecnzosc trafien */
    float skutecznosc;
    /** zmienna informujaca o trwaniu gry */
    boolean started = false;
    /** liczba celow */
    int lcelow =20;
    /** szerokosc celu */
    int celszer=64;
    /** wysokosc celu */
    int celwys=64;
    /** timer */
    Timer timer;
    /** czas trwania gry */
    float czas;
    /** tabela z dostepnymi poziomami trudnosci */
    String[] poziom={"Normalny","Latwy","Trudny"};
    /** cel */
    JLabel cel;
    /** combobox z wyborem poziomu trudnosci */
    JComboBox poziomt;


    //-------------------------------------------

        MyFrame(){
            //ustawienie okna gry
            this.setSize(szerokosc,wysokosc);
            this.setLayout(null);
            this.setResizable(false);
            this.setBackground(Color.BLACK);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Celuj!");
            ImageIcon logo = new ImageIcon("logo.png");
            this.setIconImage(logo.getImage());


            //----------------------------

            /** panel tworzacy menu gry */
            JPanel menu = new JPanel();
            menu.setBounds(0,0,1280,100);
            menu.setLayout(null);
            menu.setBackground(Color.GRAY);
            menu.setOpaque(true);
            this.add(menu);
            //------------------------------------

            /** guzik rozpoczynajacy gre */
            JButton start = new JButton();
            start.setText("START");
            start.setBounds(300,8,120,50);
            start.setBackground(Color.LIGHT_GRAY);
            start.setOpaque(true);
            menu.add(start);

            /** guzik przerywajacy gre */
            JButton stop = new JButton();
            stop.setText("PRZERWIJ");
            stop.setBounds(425,8,120,50);
            stop.setBackground(Color.LIGHT_GRAY);
            menu.add(stop);

            /** pole pokazujace statystyki gry */
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

                punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Czas: " + czas + " ms");
            }

            menu.add(punkty);
            punkty.setVisible(true);

            /** guzik do zamykania gry */
            JButton exit = new JButton();
            exit.setText("WYJDŹ");
            exit.setBounds(1100,8,120,50);
            exit.setBackground(Color.LIGHT_GRAY);
            menu.add(exit);

            /** pole pokazujace logo gry */
            JLabel logomenu = new JLabel(new ImageIcon("logomenu.png"));
            logomenu.setSize(120,50);
            logomenu.setLocation(8,8);
            logomenu.setVisible(true);
            menu.add(logomenu);

            poziomt= new JComboBox(poziom);
            poziomt.setBounds(700,8,120,50);
            poziomt.setBackground(Color.LIGHT_GRAY);
            poziomt.addActionListener(this);
            menu.add(poziomt);
            //-------------------------------------------
            timer = new Timer(60,this);
            //cel
            cel = new JLabel(new ImageIcon("cel.png"));
            this.add(cel);
            //label.setBackground(Color.black);
            cel.setOpaque(true);
            cel.setSize(celszer,celwys);
            //----------------------------------------


            this.setLocationRelativeTo(null);
            this.setVisible(true);


            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                /** zwiekszanie ilosci klikniec i liczenie skutecznosci
                 * @param e
                 */
                @Override

                public void mousePressed(MouseEvent e) {
                   if(started == true) {
                       klik++;
                       if (klik == 0) {

                           punkty.setText("Skutecznosc: "+ " %" + "Czas: " +  " ms");
                       } else {
                           skutecznosc = (100 * pkt) / klik;

                           punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Czas: " + czas + " ms");
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
            cel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                /** wyznaczania koordynatow po kliknieciu celu
                 * @param e
                 */
                @Override

                public void mousePressed(MouseEvent e) {

                    if (pkt < lcelow-1){
                        x = xlos.nextInt(1152);
                        y = ylos.nextInt(492) + 100;
                        cel.setLocation(x, y);

                        pkt++;
                        klik++;
                        if (klik == 0) {

                            punkty.setText("Wynik");
                        } else {
                            skutecznosc = (100 * pkt) / klik;

                            punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Czas: " + czas + " ms");
                        }
                    }
                    else {
                        punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Średnie opóźnienie: " + czas/lcelow + " ms");
                        System.out.println("winek");
                        started=false;
                        cel.setVisible(false);
                        poziomt.setEnabled(true);
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
                /** dzialanie przycisku start
                 * @param e
                 */
                @Override

                public void mouseClicked(MouseEvent e) {
                    czas=0;
                    timer.start();
                    cel.setVisible(true);
                    cel.setLocation(x,y);
                    pkt=0;
                    klik=0;
                    poziomt.setEnabled(false);
                    if(klik == 0){

                        punkty.setText("Skutecznosc: "+ " %" + "Czas: " + " ms");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Czas: " + czas + " ms");
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
                /** dzialanie przycisku przerywajacego gre
                 * @param e
                 */
                @Override

                public void mouseClicked(MouseEvent e) {
                    pkt =0;
                    klik=0;
                    cel.setVisible(false);
                    started = false;
                    timer.stop();
                    poziomt.setEnabled(true);
                    if(klik == 0){

                        punkty.setText("Wynik");
                    }
                    else {
                        skutecznosc = (100 * pkt) / klik;

                        punkty.setText("Skutecznosc: "+skutecznosc + " %" + "Czas: " + czas + " ms");
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
                /** dzialanie przycisku zamykajacego gre
                 * @param e
                 */
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

    /** zliczanie czasu co tick timera i ustawianie poziomu trudnosci
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        czas=czas+60;
        if(e.getSource()==poziomt){
            System.out.println(poziomt.getSelectedItem());
            if(poziomt.getSelectedItem()=="Normalny"){
                lcelow=20;
                celwys=64;
                celszer=64;
                cel.setSize(celwys,celszer);
                cel.setIcon(new ImageIcon("cel.png"));

            }
            else if(poziomt.getSelectedItem()=="Latwy"){
                lcelow=10;
                celwys=96;
                celszer=96;
                cel.setSize(celwys,celszer);
                cel.setIcon(new ImageIcon("cellatwy.png"));

            }
            else if(poziomt.getSelectedItem()=="Trudny"){
                lcelow=25;
                celwys=32;
                celszer=32;
                cel.setSize(celwys,celszer);
                cel.setIcon(new ImageIcon("celtrudny.png"));

            }
        }
    }
}
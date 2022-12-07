//kkkk
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;


public class MyFrame extends JFrame{



    final int szerokosc = 1280;
    final int wysokosc = 720;
    Random xlos = new Random();
    Random ylos = new Random();
    int x = xlos.nextInt(1152);
    int y= ylos.nextInt(492)+100;
    float pkt = 0;
    float klik = 0;
    float skutecznosc;
    boolean started = false;
    int trudnosc =5;

        MyFrame(){

            this.setSize(szerokosc,wysokosc);
            this.setLayout(null);
            this.setResizable(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Celuj!");

            JPanel menu = new JPanel();
            menu.setBounds(0,0,1280,100);
            menu.setBackground(Color.YELLOW);
            menu.setOpaque(true);
            this.add(menu);

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
            if(klik == 0){

                punkty.setText("Skutecznosc: %");
            }
            else {
                skutecznosc = (100 * pkt) / klik;

                punkty.setText("Skutecznosc: "+skutecznosc + " %");
            }


            JLabel label = new JLabel();


            menu.add(punkty);
            punkty.setVisible(true);
            this.add(label);

            label.setBackground(Color.black);
            label.setOpaque(true);



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

                    if (pkt < trudnosc-1){
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
                    label.setBounds(x,y,64,64);
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
                    punkty.setText("Skutecznosc: %");

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



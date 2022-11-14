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
    int y= ylos.nextInt(592);
    float pkt = 1;
    float klik = 1;
    float skutecznosc;


        MyFrame(){

            this.setSize(szerokosc,wysokosc);
            this.setLayout(null);

            this.setResizable(false);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel punkty = new JLabel();
            punkty.setBounds(0,0,128,128);
            punkty.setBackground(Color.white);
            punkty.setOpaque(true);
            skutecznosc = (100*pkt)/klik;
            punkty.setText(skutecznosc +" %");


            JLabel label = new JLabel();
            label.setBounds(x,y,128,128);
            label.setBackground(Color.blue);
            label.setOpaque(true);

            this.add(punkty);
            punkty.setVisible(true);
            this.add(label);
            label.setVisible(true);

            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    klik++;
                    skutecznosc = (100*pkt)/klik;
                    punkty.setText(skutecznosc +" %");

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

                    x = xlos.nextInt(1152);
                    y = ylos.nextInt(592);
                    label.setLocation(x,y);

                    pkt++;
                    klik++;
                    skutecznosc = (100*pkt)/klik;
                    punkty.setText(skutecznosc +" %");


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



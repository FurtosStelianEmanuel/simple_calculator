/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Manel
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    static char operator=' ';
    static float nr1=0,nr2=0;
    static int resizeAmount=6;
    static Color backgroundColorCifre;
     public static String getPathToJar() throws URISyntaxException {
        String x = Calculator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        StringBuilder b = new StringBuilder(x);
        b.delete(0, 1);
        return b.toString();
    }
     public static String get_path() throws URISyntaxException{
         String full=getPathToJar();
         StringBuilder b=new StringBuilder(full);
         b.delete(b.length()-14, b.length());
         return b.toString();
     }
    public static void main(String[] args) throws URISyntaxException {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        form form = new form();
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        form.setLocation((int)screenDimension.getWidth()/2-form.getWidth()/2,
                (int)screenDimension.getHeight()/2-form.getHeight()/2);
        //<editor-fold defaultstate="collapsed" desc="iconita">
        String path=get_path();
        form.setIconImage(new ImageIcon(path+"iconita.png").getImage());
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="butonase">

        JButton butoane[] = {form.jButton1, form.jButton2, form.jButton3, form.jButton4, form.jButton5, form.jButton6, form.jButton7, form.jButton8, form.jButton9, form.jButton10};
        JButton operatori[] = {form.jButton11, form.jButton12, form.jButton13, form.jButton14};
        backgroundColorCifre = butoane[0].getBackground();
//</editor-fold>
        form.setVisible(true);
        //<editor-fold defaultstate="collapsed" desc="actiune butoane">
        MouseListener animatieClickListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton b = (JButton) e.getSource();
                b.setLocation((int) b.getLocation().getX() + resizeAmount / 2, (int) b.getLocation().getY() + resizeAmount / 2);
                b.setSize((int) b.getSize().getWidth() - resizeAmount, (int) b.getSize().getHeight() - resizeAmount);
                int text = (int) ((b.getText().charAt(0)) - '0');
                if (text >= 0 && text < 10) {
                    b.setBackground(Color.GREEN);
                }else{
                    b.setBackground(Color.blue);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton b = (JButton) e.getSource();
                b.setLocation((int) b.getLocation().getX() - resizeAmount / 2, (int) b.getLocation().getY() - resizeAmount / 2);
                b.setSize((int) b.getSize().getWidth() + resizeAmount, (int) b.getSize().getHeight() + resizeAmount);
                b.setBackground(backgroundColorCifre);
                
            }
        };
        ActionListener actiune = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JButton b = (JButton) e.getSource();
                    //form.text.setText(form.text.getText() + b.getText());
                    if (operator == ' ') {
                        form.text.setText(form.text.getText() + b.getText());
                        nr1 = Float.valueOf(form.text.getText());
                    } else {
                        form.text.setText(form.text.getText() + b.getText());
                        nr2 = Float.valueOf(form.text.getText());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex, "AM MURIT", TrayIcon.MessageType.ERROR.ordinal());
                }
            }
        };
        ActionListener actiuneOperator = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                form.text.setText("");
                operator = b.getText().charAt(0);
            }
        };
        ActionListener calcul = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float rezultat = 0;
                switch (operator) {
                    case '+':
                        rezultat = nr1 + nr2;
                        break;
                    case '*':
                        rezultat = nr1 * nr2;
                        break;
                    case '-':
                        rezultat = nr1 - nr2;
                        break;
                    case '/':
                        if (nr2 != 0) {
                            rezultat = nr1 / nr2;
                        } else {
                            JOptionPane.showMessageDialog(null, "MAAAAAIIIII!!!");
                            rezultat = 0;
                            nr1 = 0;
                            nr2 = 0;
                            form.text.setText("");
                        }

                        break;
                    default:
                        rezultat = 69;
                        break;
                }
                operator = ' ';
                nr1 = rezultat;
                form.text.setText(((int) nr1 == nr1) ? Integer.toString((int) rezultat) : Float.toString(rezultat));
            }
        };
        ActionListener actiuneC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nr1 = 0;
                nr2 = 0;
                operator = ' ';
                form.text.setText("");
            }
        };
        form.jButton16.addActionListener(actiuneC);
        form.jButton15.addActionListener(calcul);
        for (JButton buton : operatori) {
            buton.addActionListener(actiuneOperator);
            buton.addMouseListener(animatieClickListener);
        }
        for (JButton buton : butoane) {
            buton.addActionListener(actiune);
            buton.addMouseListener(animatieClickListener);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="tastatura">
        KeyListener escListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode()==27){
                    System.exit(0);
                }
            }
        };
        form.jDesktopPane1.addKeyListener(escListener);  
        for (JButton b:butoane)
            b.addKeyListener(escListener);
        for (JButton b:operatori)
            b.addKeyListener(escListener);
        form.text.addKeyListener(escListener);
        form.addKeyListener(escListener);
        //</editor-fold>   
        form.toFront();
        form.setTitle("Calculator");
        
    }

}

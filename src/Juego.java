import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Juego extends JPanel {
  int x = xR;
  int y = 330;
  boolean der = true;
  boolean abajo = false;
  static int xR = 110;
  JFrame msgOver = new JFrame();
  static Bloque[] b = new Bloque[4];
  static int k = 50;
  static int contador = 0;

  private void moverPelota() {
    //otras maneras--
    // eje horizontal--
    // x+=x==250 ? dx=-1 : x==0 ? dx=1 : dx;
    //eje vertical--
    // y+=y==330 ? dy=-1 : y==0 ? dy=1 : dy;

    // eje horizontal
    if (x == 250) {
      der = false;
    }
    if (!der) {
      x--;
    } else {
      x++;
    }
    if (x == 0) {
      der = true;
    }
    //eje vertical
    if (x + 10 > xR && x + 10 < (xR + 70) && y == 310) {
      abajo = false;
    }
    if (!abajo) {
      y--;
    } else {
      y++;
    }
    if (y == 0) {
      abajo = true;
    }
    if (y > 330) {
      JOptionPane.showMessageDialog(msgOver, "Game Over!");
      System.exit(0);
    }
  }

  public void moverRaqueta() {
    if (xR < 0) {
      xR = 0;
    }
    if (xR > 225) {
      xR = 225;
    }
  }

  public void destruirBloque() {
    for (int i = 0; i < b.length; i++) {
      if (x + 10 > b[i].xB && x + 10 < b[i].xB + b[i].anchura && y < b[i].yB + 20 && !b[i].destruido) {
        b[i].destruido = true;
        abajo = !abajo;
        contador--;
      }
    }
  }
  public void win() {
    if (contador == 0) {
      JOptionPane.showMessageDialog(msgOver, "You win!");
      System.exit(0);
    }
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.fillOval(x, y, 20, 20);
    g.fillRect(xR, 330, 70, 15);

    for (int i = 0; i < b.length; i++) {
      if (!b[i].destruido) {
        g.setColor(b[i].color);
        g.fillRect(b[i].xB, b[i].yB, b[i].anchura, b[i].altura);

      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Arkanoid");
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Juego juego = new Juego();
    frame.add(juego);
    frame.setSize(300, 400);

    for (int i = 0; i < b.length; i++) {
      b[i] = new Bloque(k, 50);
      k += 50;
      contador++;
    }

    frame.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          xR += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          xR -= 10;
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });

    while (true) {
      juego.moverPelota();
      juego.moverRaqueta();
      juego.destruirBloque();
      juego.repaint();
      juego.win();
      Thread.sleep(7);
    }
  }
}
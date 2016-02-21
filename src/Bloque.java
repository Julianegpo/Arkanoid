import java.awt.*;

public class Bloque {
  int xB;
  int yB;
  int altura;
  int anchura;
  Color color;
  boolean destruido;

  public Bloque(int x, int y){
    xB=x;
    yB=y;
    altura=20;
    anchura=50;
    color = new Color((int) (Math.random() * 0xffffff));
    destruido=false;
  }
}

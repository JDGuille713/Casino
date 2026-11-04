import java.util.Random;

public class Ruleta {
    private int ultimoNumero;

    public Ruleta() {
        ultimoNumero = -1;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }

    public int girar() {
        ultimoNumero = (int) (Math.random() * 37);
        return ultimoNumero;
    }

    // Prueba en el main
    /* public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();
        for (int i = 1; i <= 20; i++) {
            ruleta.girar();
            System.out.println(i + ". " + ruleta.getUltimoNumero());
        }
    } */
}

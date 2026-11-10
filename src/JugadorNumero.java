public class JugadorNumero extends Jugador {
    private static final int APUESTA = 10;
    private final int numeroElegido;

    public JugadorNumero(String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
        this.numeroElegido = (int) (Math.random() * 36) + 1;
    }

    @Override
    public Apuesta crearApuesta() {
        return new Apuesta(Apuesta.NUMERO, numeroElegido, APUESTA);
    }
}
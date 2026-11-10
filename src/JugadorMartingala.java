public class JugadorMartingala extends Jugador {
    private final int numeroElegido;
    private int apuestaActual;
    private final static int APUESTA_INICIAL = 10;

    public JugadorMartingala(String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
        this.numeroElegido = (int) (Math.random() * 36) + 1;
        this.apuestaActual = APUESTA_INICIAL;
    }

    @Override
    public Apuesta crearApuesta() {
        return new Apuesta(Apuesta.NUMERO, numeroElegido, apuestaActual);
    }

    @Override
    protected void alTerminarRonda(boolean haGanado) {
        if (haGanado) {
            apuestaActual = APUESTA_INICIAL;
        } else {
            apuestaActual = apuestaActual * 2;
        }
    }
}

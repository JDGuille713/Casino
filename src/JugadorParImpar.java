public class JugadorParImpar extends Jugador {
    private static final int APUESTA = 10;
    private final String tipoElegido;

    public JugadorParImpar (String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
        if (Math.random() < 0.5) {
            this.tipoElegido = Apuesta.PAR;
        } else {
            this.tipoElegido = Apuesta.IMPAR;
        }
    }

    @Override
    public Apuesta crearApuesta(){
        return new Apuesta(tipoElegido, -1, APUESTA);
    }

}

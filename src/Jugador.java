public abstract class Jugador extends Thread {
    private String nombre;
    private int saldo = 1000;
    private Ruleta ruleta;
    private Banca banca;

    public Jugador(String nombre, Ruleta ruleta, Banca banca) {
        this.ruleta = ruleta;
        this.nombre = nombre;
        this.banca = banca;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void sumarSaldo(int cantidad) {
        saldo += cantidad;
    }

    public void restarSaldo(int cantidad) {
        saldo -= cantidad;
    }

    public abstract Apuesta crearApuesta();

    @Override
    public void run() {
        while (ruleta.isEnMarcha()) {
            int rondaLeida = ruleta.getNumTurno();

            Apuesta apuesta = crearApuesta();

            if (saldo >= apuesta.getCantidad()) {
                restarSaldo(apuesta.getCantidad());
                banca.recibirApuesta(apuesta.getCantidad());
                System.out.println(nombre + " apuesta " + apuesta);
            } else {
                apuesta = null;
                System.out.println(nombre + " no tiene saldo suficiente.");
            }

            ruleta.registrarApuesta();

            ruleta.esperarResultado();

            if (!ruleta.isEnMarcha()) {
                break;
            }

            if (apuesta != null) {
                boolean haGanado = apuesta.haGanado(ruleta.getUltimoNumero());
                if (haGanado) {
                    int cobrado = banca.pagar(apuesta.calcularPremio());
                    sumarSaldo(cobrado);
                    System.out.println(nombre + " gana " + cobrado + " € (saldo: " + saldo + "€)");
                } else {
                    System.out.println(nombre + " pierde (saldo: " + saldo + "€)");
                }
                alTerminarRonda(haGanado);
            }

            ruleta.resultadoComprobado();

            ruleta.esperarNuevoTurno(rondaLeida);
        }
    }

        protected void alTerminarRonda(boolean haGanado) {

        }


}

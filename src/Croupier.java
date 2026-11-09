public class Croupier extends Thread {

    private final Ruleta ruleta;
    private final Banca banca;

    public Croupier(Ruleta ruleta, Banca banca) {
        this.ruleta = ruleta;
        this.banca = banca;
    }

    @Override
    public void run() {
        while (ruleta.isEnMarcha()) {
            if (!ruleta.esperarApuestas()) {
                break;
            }

            ruleta.girar();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ruleta.generarNumero();

            ruleta.esperarComprobaciones();

            if (!banca.siguePagando()) {
                ruleta.finalizar();
                break;
            }

            ruleta.abrirNuevoTurno();

        }
    }
}

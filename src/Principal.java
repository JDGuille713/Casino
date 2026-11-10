public class Principal {

    private static final int JUGADORES_POR_TIPO = 4;
    private static final int TOTAL_JUGADORES = JUGADORES_POR_TIPO * 3;

    public static void main(String[] args) {
        Banca banca = new Banca();
        Ruleta ruleta = new Ruleta(TOTAL_JUGADORES);

        Croupier croupier = new Croupier(ruleta, banca);

        Jugador[] jugadores = new Jugador[TOTAL_JUGADORES];
        int contador = 0;
        for (int i=1; i <= JUGADORES_POR_TIPO; i++) {
            jugadores[contador++] = new JugadorNumero("JugadorNumero " + i, ruleta, banca);
            jugadores[contador++] = new JugadorParImpar("JugadorParImpar " + i, ruleta, banca);
            jugadores[contador++] = new JugadorMartingala("JugadorMartingala " + i, ruleta, banca);
        }

        croupier.start();
        for (Jugador jugador : jugadores) {
            jugador.start();
        }

        try {
            croupier.join();
            for (Jugador jugador : jugadores){
                jugador.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo de la banca: " + banca.getSaldo() + " €");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getSaldo() + " €");
        }

    }



}

public class Banca {
    private int saldo = 50000;
    private static final int PREMIO_MAXIMO = 360;

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void recibirApuesta(int cantidad) {
        saldo += cantidad;
    }

    public synchronized int pagar(int cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente");
            cantidad = saldo;
            saldo = 0;
        }
        return cantidad;
    }

    public synchronized boolean siguePagando() {
        return saldo >= PREMIO_MAXIMO;
    }

}

public class Ruleta {
    private int ultimoNumero;
    private int numJugadores;
    private int numTurno;
    private int apuestasRealizadas;
    private int resultadosComprobados;
    private boolean girando;
    private boolean todosHanApostado;
    private boolean hayResultado;
    private boolean enMarcha;


    public Ruleta(int numJugadores) {
        this.numJugadores = numJugadores;
        ultimoNumero = -1;
        numTurno = 1;
        apuestasRealizadas = 0;
        resultadosComprobados = 0;

        enMarcha = true;
        girando = false;
        todosHanApostado = false;
        hayResultado = false;
    }

    public synchronized int getUltimoNumero() {
        return ultimoNumero;
    }

    public synchronized int getNumTurno() {
        return numTurno;
    }

    public synchronized boolean isEnMarcha() {
        return enMarcha;
    }

    public synchronized void registrarApuesta() {
        apuestasRealizadas++;
        if (apuestasRealizadas == numJugadores) {
            todosHanApostado = true;
            notifyAll();
        }
    }

    public synchronized void esperarResultado() {
        while (!hayResultado && enMarcha) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void resultadoComprobado() {
        resultadosComprobados++;
        if (resultadosComprobados == numJugadores) {
            notifyAll();
        }
    }

    public synchronized void esperarNuevoTurno(int turnoActual) {
        while (numTurno == turnoActual && enMarcha) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean esperarApuestas() {
        while (!todosHanApostado && enMarcha) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return enMarcha;
    }


    public synchronized void girar() {
        if (todosHanApostado) {
            girando = true;
            System.out.println("La ruleta está girando.");
        }
    }

    public synchronized void generarNumero() {
        ultimoNumero = (int) (Math.random() * 37);
        girando = false;
        hayResultado = true;
        System.out.println("El numero premiado es el " +  ultimoNumero);
        notifyAll();
    }

    public synchronized void esperarComprobaciones() {
        while (resultadosComprobados < numJugadores && enMarcha) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void abrirNuevoTurno() {
        apuestasRealizadas = 0;
        resultadosComprobados = 0;
        girando = false;
        todosHanApostado = false;
        hayResultado = false;
        numTurno++;
        notifyAll();
    }

    public synchronized void finalizar() {
        enMarcha = false;
        notifyAll();
    }
}

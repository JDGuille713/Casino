public class Apuesta {
    public static final String NUMERO = "NUMERO";
    public static final String PAR = "PAR";
    public static final String IMPAR = "IMPAR";

    // Multiplicadores del premio
    public static final int MULTIPLICADOR_NUMERO = 36;
    public static final int MULTIPLICADOR_PAR_IMPAR = 2;

    // Atributos
    private final String tipo;
    private final int valor;
    private final int cantidad;

    public Apuesta(String tipo, int valor, int cantidad) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public boolean haGanado(int numeroGanador) {
        if (numeroGanador == 0) {
            return false;
        }

        if (tipo.equals(NUMERO)) {
            if (numeroGanador == valor) {
                return true;
            }
        } else if (tipo.equals(PAR)) {
            if (numeroGanador % 2 == 0) {
                return true;
            }
        } else if (tipo.equals(IMPAR)) {
            if (numeroGanador % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    public int calcularPremio() {
        if (NUMERO.equals(tipo)) {
            return cantidad * MULTIPLICADOR_NUMERO;
        } else if (PAR.equals(tipo) || IMPAR.equals(tipo)) {
            return cantidad * MULTIPLICADOR_PAR_IMPAR;
        }
        return 0;
    }

    @Override
    public String toString() {
        if (NUMERO.equals(tipo)) {
            return cantidad + "€ al numero " + valor;
        } else if (PAR.equals(tipo)) {
            return cantidad + "€ a PAR";
        } else if (IMPAR.equals(tipo)) {
            return cantidad + "€ a IMPAR";
        }
        return "";
    }
}

public class Somador {
    private int resultado;

    public Somador() {
        resultado = 0;
    }

    public void setResultado (int opA, int opB) {
        this.resultado = opA + opB;
    }

    public int getResultado() {
        return resultado;
    }
}

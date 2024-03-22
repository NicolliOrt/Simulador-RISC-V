public class Mux {
    private int resultado;

    public Mux() {
        this.resultado = 0;
    }

    public void setResultado (int opA, int opB, Boolean bitSinal) {
        this.resultado = (bitSinal == true) ? opB : opA;
    }

    public int getResultado() {
        return resultado;
    }

    
}

public class ALU {
    private int resultado;
    private boolean flagZero; // entrada do MUX + Branch

    public ALU () {
        this.resultado = 0;
        this.flagZero = false;
    }
    
    public int getResultado() {
        return resultado;
    }
    
    public boolean getEhZero() {
        return flagZero;
    }

    void setResultado (int entradaALUControl, int opA, int opB) {
        switch (entradaALUControl) {
            case 0:
                this.resultado = opA & opB;

                break;

            case 1:
                this.resultado = opA | opB;
                
                break;

            case 2:
                this.resultado = opA + opB;
                
                break;

            case 6:
                this.resultado = opA - opB;
                
                break;
        
            default:
                
                break;
        }

        if(this.resultado == 0) {
            this.flagZero = true;
        } else {
            this.flagZero = false;
        }
    }
}

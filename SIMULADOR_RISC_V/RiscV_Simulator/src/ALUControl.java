/**
 * This class is responsible for controlling the ALU operations. It decodes the instructions and determines the appropriate operation to be performed.
 */
public class ALUControl {
    private int resultado;
    private int ALUop;
    private int funct7;
    private int funct3;

    /**
     * Default constructor.
     */
    public ALUControl() {
        this.ALUop = 0;
        this.funct7 = 0;
        this.funct3 = 0;
    }

    /**
     * Decodes the ALU operation from the given instruction.
     * 
     * @param instrucao the instruction to be decoded
     * @return the decoded ALU operation
     */
    private static int decodificarALUop(String instrucao) {
        String aux = instrucao.substring(25, 31+1);
        int opcode = Integer.parseInt(aux, 2);
        int ALUop = 0;

        switch (opcode) {
            case 51:
                ALUop = 2;    

                break;

            case 3:
                ALUop = 0;

                break;

            case 35:
                ALUop = 0;

                break;

            case 99:
                ALUop = 1;

                break;

            case 19://addi
                ALUop = 0;
        
            default:
                break;
        }

        return ALUop;
    }

    /**
     * Decodes the funct3 field from the given instruction.
     * 
     * @param instrucao the instruction to be decoded
     * @return the decoded funct3 field
     */    
    private static int decodificarFunct3(String instrucao) {
        String aux = instrucao.substring(17, 19+1);
        int funct3 = Integer.parseInt(aux, 2);

        return funct3;
    }

    /**
     * Decodes the funct7 field from the given instruction.
     * 
     * @param instrucao the instruction to be decoded
     * @return the decoded funct7 field
     */
    private static int decodificarFunct7(String instrucao) {
        String aux = instrucao.substring(0, 6+1);
        int funct7 = Integer.parseInt(aux, 2);

        return funct7;
    }

    /**
     * Sets the result of the ALU operation based on the given instruction.
     * 
     * @param instrucao the instruction to be used for determining the result
     */
    public void setResultado(String instrucao) {
        this.ALUop = decodificarALUop(instrucao);
        this.funct3 = decodificarFunct3(instrucao);
        this.funct7 = decodificarFunct7(instrucao);

        if(ALUop == 0) { //lw ou sw
            this.resultado = 2; //add
        }
        else if (ALUop == 1) { //beq
            if(funct3 == 0){
                this.resultado = 6; //sub 
            }
            else{
                this.resultado = 7;
            }
        }
        else if (ALUop == 2) { //add, sub, and, or
            if(funct7 == 0) {
                if(funct3 == 0) {
                    this.resultado = 2;//add
                }
                else if(funct3 == 7) {
                    this.resultado = 0;
                }
                else if(funct3 == 6) {
                    this.resultado = 1;
                }
            }
            else {
                this.resultado = 6;//sub
            }
        }
    }

    /**
     * Returns the result of the ALU operation.
     * 
     * @return the result of the ALU operation
     */
    public int getResultado() {
        return resultado;
    }

}

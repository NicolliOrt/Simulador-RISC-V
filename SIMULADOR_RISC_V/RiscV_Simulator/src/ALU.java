/**
 * The ALU class implements the arithmetic and logic unit of the CPU. It performs
 * operations on two inputs and produces a single output. The ALU receives the
 * operation code as input and performs the corresponding operation on the two
 * operand inputs. The result is then passed to the next stage of the CPU.
 * 
 * The ALU has two flags - zero flag and carry flag. The zero flag is set to 1 if
 * the result of the operation is zero, and it is set to 0 otherwise. The carry
 * flag is used for carry-save adder operations and is not relevant for other
 * operations.
 * 
 * The ALU uses a control input to select among different operations. The control
 * input is a 3-bit value that selects one of the eight possible operations: AND,
 * OR, ADD, SUB, XOR, SLL, SRL, and SRA. The operation code is provided to the
 * ALU as part of the control input.
 * 
 * The ALU also has an input for the result of the branch instruction. This input
 * is used to set the zero flag based on the result of the branch operation.
 */
public class ALU {
    private int resultado;
    private boolean flagZero; // entrada do MUX + Branch

    /**
     * Constructor for the ALU class. Initializes the result and zero flag to
     * zero.
     */
    public ALU () {
        this.resultado = 0;
        this.flagZero = false;
    }
    
    /**
     * Returns the result of the last operation performed by the ALU.
     * 
     * @return the result of the last operation
     */
    public int getResultado() {
        return resultado;
    }
    
    /**
     * Returns the value of the zero flag.
     * 
     * @return the value of the zero flag
     */
    public boolean getEhZero() {
        return flagZero;
    }

    /**
     * Sets the result of the last operation performed by the ALU and updates the
     * zero flag based on the result and the value of the branch flag.
     * 
     * @param entradaALUControl the control input to the ALU
     * @param opA the first operand
     * @param opB the second operand
     */
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
                
                if(this.resultado == 0) {
                    this.flagZero = true;
                } else {
                    this.flagZero = false;
                }
                
                break;

            case 7:
                this.resultado = opA ^ opB;
                
                if(this.resultado == 0) {
                    this.flagZero = false;
                } else {
                    this.flagZero = true;
                }

                break;
        
            default:
                
                break;
        }
    }
}

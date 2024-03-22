/**
 * This class contains the control signals for the CPU.
 * 
 * @author Nicolli, Guilherme Miller, Matheus Rezende
 */
public class Control {
    private boolean ALUSrc;
    private boolean MemToReg;
    private boolean RegWrite;
    private boolean MemRead;
    private boolean MemWrite;
    private boolean Branch;
    private int ALUop;

    /**
     * Creates a new instance of the Control class with default values.
     */
    public Control() {
        this.ALUSrc = false;
        this.MemRead = false;
        this.MemWrite = false;
        this.RegWrite = false;
        this.MemToReg = false;
        this.Branch = false;
        this.ALUop = 0;
    }

    /**
     * Decodes the opcode of an instruction and sets the control signals accordingly.
     * 
     * @param instrucao The instruction to be decoded
     */
    private static int decodificarOpcode (String instrucao) {
        String aux = instrucao.substring(25, 31+1);

        return Integer.parseInt(aux, 2);
    }

    /**
     * Sets the control signals based on the given instruction.
     * 
     * @param instrucao The instruction to be decoded
     */
    public void setFlags(String instrucao) {      
        int opcode = decodificarOpcode(instrucao);

        switch (opcode) {
            case 51:
                this.ALUSrc = false;
                this.MemToReg = false;
                this.RegWrite = true;
                this.MemRead = false;
                this.MemWrite = false;
                this.Branch = false;
                this.ALUop = 2;
                
                break;
            
            case 3:
                this.ALUSrc = true;
                this.MemToReg = true;
                this.RegWrite = true;
                this.MemRead = true;
                this.MemWrite = false;
                this.Branch = false;
                this.ALUop = 0;

                break;

            case 35:
                this.ALUSrc = true;
                this.MemToReg = false;
                this.RegWrite = false;
                this.MemRead = false;
                this.MemWrite = true;
                this.Branch = false;
                this.ALUop = 0;

                break;

            case 99:
                this.ALUSrc = false;
                this.MemToReg = false;
                this.RegWrite = false;
                this.MemRead = false;
                this.MemWrite = false;
                this.Branch = true;
                this.ALUop = 1;
                
                break;

            case 19:
                this.ALUSrc = true;
                this.MemToReg = false;
                this.RegWrite = true;
                this.MemRead = false;
                this.MemWrite = false;
                this.Branch = false;
                this.ALUop = 0;

                break;

            default:
                break;
        }
    }

    public boolean isALUSrc() {
        return ALUSrc;
    }

    public boolean isMemToReg() {
        return MemToReg;
    }

    public boolean isRegWrite() {
        return RegWrite;
    }

    public boolean isMemRead() {
        return MemRead;
    }

    public boolean isMemWrite() {
        return MemWrite;
    }

    public boolean isBranch() {
        return Branch;
    }

    public int getALUop() {
        return ALUop;
    }

    public void printFlags() {
        System.out.println("ALUSrc: " + ALUSrc);
        System.out.println("MemToReg: " + MemToReg);
        System.out.println("RegWrite: " + RegWrite);
        System.out.println("MemRead: " + MemRead);
        System.out.println("MemWrite: " + MemWrite);
        System.out.println("Branch: " + Branch);
        System.out.println("ALUop: " + ALUop);
        System.out.println("------------------------------------------------");
    }
    
}

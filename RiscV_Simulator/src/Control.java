public class Control {
    private boolean ALUSrc;
    private boolean MemToReg;
    private boolean RegWrite;
    private boolean MemRead;
    private boolean MemWrite;
    private boolean Branch;
    private int ALUop;

    public Control() {
        this.ALUSrc = false;
        this.MemRead = false;
        this.MemWrite = false;
        this.RegWrite = false;
        this.MemToReg = false;
        this.Branch = false;
        this.ALUop = 0;
    }

    private static int decodificarOpcode (String instrucao) {
        String aux = instrucao.substring(25, 31+1);

        return Integer.parseInt(aux, 2);
    }

    public void setFlags(String instrucao) {      
        int opcode = decodificarOpcode(instrucao);
          
        switch (opcode) {
            case 51:
                System.out.println("TYPE R");

                this.ALUSrc = false;
                this.MemToReg = false;
                this.RegWrite = true;
                this.MemRead = false;
                this.MemWrite = false;
                this.Branch = false;
                this.ALUop = 2;
                
                break;
            
            case 3:
                System.out.println("TYPE LW");

                this.ALUSrc = true;
                this.MemToReg = true;
                this.RegWrite = true;
                this.MemRead = true;
                this.MemWrite = false;
                this.Branch = false;
                this.ALUop = 0;

                break;

            case 35:
                System.out.println("TYPE SW");

                this.ALUSrc = true;
                this.MemToReg = false;
                this.RegWrite = false;
                this.MemRead = false;
                this.MemWrite = true;
                this.Branch = false;
                this.ALUop = 0;

                break;

            case 99:
                System.out.println("BEQ");

                this.ALUSrc = false;
                this.MemToReg = false;
                this.RegWrite = false;
                this.MemRead = false;
                this.MemWrite = false;
                this.Branch = true;
                this.ALUop = 1;
                
                break;

            case 19:
                System.out.println("addi");

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
}

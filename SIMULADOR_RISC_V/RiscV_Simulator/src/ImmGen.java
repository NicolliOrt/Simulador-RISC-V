public class ImmGen {
    private int imm;

    public ImmGen() {
        this.imm = 0;
    }
    
    /**
     * This method is responsible for parsing an integer from a binary string.
     * 
     * @param bin the binary string to be parsed
     * @return the integer represented by the binary string
     */
    public static int parseInteger (String bin) {
        int imm = 0;
        char[] binAux = bin.toCharArray(); 
    
        if(binAux[0] == '0') {//positivo
            imm = Integer.parseInt(bin, 2);
        } 
        else{//negativo
            for(int i = 0; i < binAux.length; i++) {
                if(binAux[i] == '0') {//inversão
                    binAux[i] = '1';
                }
                else {
                    if(binAux[i] == '1'){
                        binAux[i] = '0';
                    }
                }
            }
    
            String strAux = new String (binAux);
    
            imm = ((Integer.parseInt(strAux, 2)) + 1) * (-1);
        }
    
        return imm;
    }

    /**
     * This method is responsible for generating the immediate field of the R-type instructions.
     * 
     * @param instrucao the instruction to be processed
     */
    public void gerarImm (String instrucao) {
        String opcodeAux = instrucao.substring(25, 31+1);
        int opcode = Integer.parseInt(opcodeAux, 2);
        String imm = "";

        switch (opcode) {
            case 3: // TYPE L
                imm = instrucao.substring(0, 11+1);

                this.imm = parseInteger(imm);

                break;

            case 35: // TYPE S
                imm = instrucao.substring(0, 6+1) + instrucao.substring(20, 24+1);
                
                this.imm = parseInteger(imm);

                break;

            case 99:// BEQ
                imm = instrucao.substring(0, 0+1) + instrucao.substring(24, 24+1) +
                instrucao.substring(1, 6+1) + instrucao.substring(20, 23+1) + "0";

                this.imm = parseInteger(imm);

                break;

            case 19: //TYPE ADDI
                imm = instrucao.substring(0, 11+1);

                this.imm = parseInteger(imm);

                break;
            default:
                break;
        }
    }
    
    public int getImmInt () {
        return imm;
    }

}

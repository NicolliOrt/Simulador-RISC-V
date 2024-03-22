import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a register bank. It contains 32 registers, each of which can hold an integer value.
 * The registers can be read and written to using instructions that specify the register numbers to be read or written.
 * The register bank also contains two special registers, called the program counter (PC) and the status register (SR).
 * The PC is used to keep track of the current location in memory where program execution is currently taking place.
 * The SR contains flags that indicate the status of the processor, such as whether the processor is currently executing
 * or halted, or whether an error has occurred.
 */
public class BancoRegistradores {
    private int readRegister1;
    private int readRegister2;
    private int writeRegister;
    private int readData1;
    private int readData2;
    /**
     * The table of registers in the register bank. The key is the register number, and the value is the register value.
     */
    private Map<Integer, Integer> tabelaRegistradores;

    /**
     * Constructs a new register bank with all registers initialized to 0.
     */
    public BancoRegistradores() {
        this.tabelaRegistradores = new HashMap<>(32); 

        for (int i = 0; i < 32; i++) {
            this.tabelaRegistradores.put(i, 0);
        }
    }

    /**
     * Sets the value of the registers in the register bank based on the given instruction.
     * 
     * @param instrucao the instruction that specifies the register values to be set
     */
    public void setRegistradores(String instrucao) {
        this.readRegister1 = decodificarReadRegister1(instrucao);
        this.readRegister2 = decodificarReadRegister2(instrucao);
        this.writeRegister = decodificarWriteRegister3(instrucao);

        this.readData1 = tabelaRegistradores.get(this.readRegister1);
        this.readData2 = tabelaRegistradores.get(this.readRegister2);
    }

    /**
     * Decodes the value of the first read register from the given instruction.
     * 
     * @param instrucao the instruction that specifies the register values
     * @return the value of the first read register
     */
    private static int decodificarReadRegister1(String instrucao) {
        String aux = instrucao.substring(12, 16+1);
        
        return Integer.parseInt(aux, 2);
    }

    /**
     * Decodes the value of the second read register from the given instruction.
     * 
     * @param instrucao the instruction that specifies the register values
     * @return the value of the second read register
     */
    private static int decodificarReadRegister2(String instrucao) {
        String aux = instrucao.substring(7, 11+1);
        
        return Integer.parseInt(aux, 2);
    }

    /**
     * Decodes the value of the write register from the given instruction.
     * 
     * @param instrucao the instruction that specifies the register values
     * @return the value of the write register
     */
    private static int decodificarWriteRegister3(String instrucao) {
        String aux = instrucao.substring(20, 24+1);
        
        return Integer.parseInt(aux, 2);
    }

    /**
     * Sets the value of the write register based on the given instruction and data.
     * 
     * @param RegWrite a boolean value indicating whether the write register is to be set or not
     * @param writeData the data to be written to the write register
     */
    public void setWriteData(boolean RegWrite, int writeData) {
        if(RegWrite) this.tabelaRegistradores.put(getWriteRegister(), writeData);
    }

    public int getReadRegister1() {
        return readRegister1;
    }

    public int getReadRegister2() {
        return readRegister2;
    }

    public int getReadData1(int readRegister1) {
        return tabelaRegistradores.get(readRegister1);
    }

    public int getReadData2(int readRegister2) {
        return tabelaRegistradores.get(readRegister2);
    }

    public int getReadData1() {
        return readData1;
    }

    public int getReadData2() {
        return readData2;
    }

    public int getWriteRegister() {
        return writeRegister;
    }

    /**
     * Prints the current state of the register bank to the console.
     */
    public void printBancoRegistradores() {
        System.out.println("Estado atual do Banco de Registradores:");
        System.out.println("Registrador lido 1: " + readRegister1 + ", Dado lido 1: " + readData1);
        System.out.println("Registrador lido 2: " + readRegister2 + ", Dado lido 2: " + readData2);
        System.out.println("Registrador escrito: " + writeRegister);
        System.out.println("------------------------------------------------");
    }

    /**
     * Prints the contents of the register table to the console.
     */
    public void printTabelaRegistradores() {
        System.out.println("Conteúdo da Tabela de Registradores:");
        for (Map.Entry<Integer, Integer> entry : tabelaRegistradores.entrySet()) {
            System.out.println("Registrador " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------------------------------------");
    }    
    
}

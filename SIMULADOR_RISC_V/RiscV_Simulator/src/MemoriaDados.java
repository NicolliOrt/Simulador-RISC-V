/**
 * This class represents the memory data unit in the computer system. It is responsible for storing data and providing it to the CPU when needed.
 * The memory data unit is divided into two parts: the main memory, which is volatile and can be accessed by the CPU, and the cache memory, which is faster but less volatile.
 * The memory data unit also includes the registers, which are temporary storage areas that can be accessed much faster than the main memory.
 */
public class MemoriaDados {
    private int[] endereco;
    private int readData;

    public MemoriaDados() {
        this.endereco = new int[1024];

        for (int i : this.endereco) {
            this.endereco[i] = 0;
        }

        this.readData = 0;
    }

    /**
     * This method is used by the CPU to access the memory data unit. It takes four parameters: MemWrite, MemRead, adress_ALUResult, and writeData_readData2.
     * MemWrite indicates whether the CPU is writing data to the memory (true) or reading data from the memory (false).
     * MemRead indicates whether the CPU is reading data from the memory (true) or writing data to the memory (false).
     * adress_ALUResult represents the memory address calculated by the ALU.
     * writeData_readData2 represents the data written to the memory by the CPU.
     * This method updates the memory data unit based on the parameters passed. If MemWrite is true, it updates the data at the memory address specified by adress_ALUResult with writeData_readData2. If MemRead is true, it reads the data from the memory address specified by adress_ALUResult and stores it in readData.
     */
    public void executeDataBase (boolean MemWrite, boolean MemRead, int adress_ALUResult, int writeData_readData2) {
        if(MemWrite) {
            this.endereco[adress_ALUResult] = writeData_readData2;
        }
        if(MemRead) {
            this.readData = this.endereco[adress_ALUResult];
        }
    }

    public int[] getEndereco() {
        return endereco;
    }

    public int getReadData() {
        return readData;
    }

    public void printState() {
        System.out.println("Estado atual da Memória de Dados:");
        System.out.println("Dados lidos: " + this.readData);
        System.out.println("------------------------------------------------");
    }

    // Método para imprimir os vetores de endereços utilizados
    public void printEnderecosUtilizados() {
        System.out.println("Endereços utilizados na Memória de Dados:");
        for (int i = 0; i < this.endereco.length; i++) {
            if (this.endereco[i] != 0) {
                System.out.println("Endereço " + i + ": " + this.endereco[i]);
            }
        }
        System.out.println("------------------------------------------------");
    }
}

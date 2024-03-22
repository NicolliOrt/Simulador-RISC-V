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

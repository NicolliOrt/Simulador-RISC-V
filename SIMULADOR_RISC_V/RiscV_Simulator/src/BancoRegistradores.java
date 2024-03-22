import java.util.HashMap;
import java.util.Map;

public class BancoRegistradores {
    private int readRegister1;
    private int readRegister2;
    private int writeRegister;
    private int readData1;
    private int readData2;
    private Map<Integer, Integer> tabelaRegistradores;

    public BancoRegistradores() {
        this.tabelaRegistradores = new HashMap<>(32); 

        for (int i = 0; i < 32; i++) {
            this.tabelaRegistradores.put(i, 0);
        }
    }

    public void setRegistradores(String instrucao) {
        this.readRegister1 = decodificarReadRegister1(instrucao);
        this.readRegister2 = decodificarReadRegister2(instrucao);
        this.writeRegister = decodificarWriteRegister3(instrucao);

        this.readData1 = tabelaRegistradores.get(this.readRegister1);
        this.readData2 = tabelaRegistradores.get(this.readRegister2);
    }

    private static int decodificarReadRegister1(String instrucao) {
        String aux = instrucao.substring(12, 16+1);
        
        return Integer.parseInt(aux, 2);
    }

    private static int decodificarReadRegister2(String instrucao) {
        String aux = instrucao.substring(7, 11+1);
        
        return Integer.parseInt(aux, 2);
    }

    private static int decodificarWriteRegister3(String instrucao) {
        String aux = instrucao.substring(20, 24+1);
        
        return Integer.parseInt(aux, 2);
    }

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

    public void printBancoRegistradores() {
        System.out.println("Estado atual do Banco de Registradores:");
        System.out.println("Registrador lido 1: " + readRegister1 + ", Dado lido 1: " + readData1);
        System.out.println("Registrador lido 2: " + readRegister2 + ", Dado lido 2: " + readData2);
        System.out.println("Registrador escrito: " + writeRegister);
        System.out.println("------------------------------------------------");
    }

    public void printTabelaRegistradores() {
        System.out.println("Conteúdo da Tabela de Registradores:");
        for (Map.Entry<Integer, Integer> entry : tabelaRegistradores.entrySet()) {
            System.out.println("Registrador " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------------------------------------");
    }    
    
}

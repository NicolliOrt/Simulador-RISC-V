import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoriaInstrucoes {

    private Map<Integer, String> tabelaInstrucoes;

    public MemoriaInstrucoes() {
        this.tabelaInstrucoes = new HashMap<>();
    }

    public void adicionarInstrucoes(List<String> instrucoes) {
        int endereco = 8000;

        for (String instrucao : instrucoes) {
            this.tabelaInstrucoes.put(endereco, instrucao);
            endereco += 4; // Incrementar o endereço em 4
        }
    }

    public String buscarInstrucao(int endereco) {
        return tabelaInstrucoes.get(endereco);
    }

    public void imprimirInstrucoes() {
        System.out.println("Instruções na Memória de Instruções:");
        for (Map.Entry<Integer, String> entry : tabelaInstrucoes.entrySet()) {
            System.out.println("Endereço: " + entry.getKey() + ", Instrução: " + entry.getValue());
        }
    }
    
}

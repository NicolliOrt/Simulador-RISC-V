import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String> setBancoInstrucoes (MemoriaInstrucoes mi) {
        Scanner scannerAux = new Scanner(System.in);
        String teste = "";

        System.out.println("Digite o caminho completo de teste: ");
        teste = scannerAux.nextLine();

        String caminhoArquivo = "";
        try {
            caminhoArquivo = teste;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scanner scanner = null;// Criar um scanner para ler o arquivo
        try {
            scanner = new Scanner(Paths.get(caminhoArquivo));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<String> instrucoes = new ArrayList<>();

        while (scanner.hasNextLine()) {// Ler cada linha do arquivo e adicionar à lista
            instrucoes.add(scanner.nextLine());
        }
        scanner.close();// Fechar o scanner
    
        mi.adicionarInstrucoes(instrucoes);//adiciona na tabela de instrucoes
        
        return instrucoes;
    } 

    public static void main(String[] args) throws Exception {
        MemoriaInstrucoes mi = new MemoriaInstrucoes();
        List<String> intrucoes = setBancoInstrucoes(mi);

        PC pc = new PC();
        Control control = new Control();
        BancoRegistradores br = new BancoRegistradores();
        ImmGen imm = new ImmGen();
        Mux mux1 = new Mux();
        Mux mux2 = new Mux();
        Mux mux3 = new Mux();
        Somador sum1 = new Somador();
        Somador sum2 = new Somador();
        ALUControl aluControl = new ALUControl();
        ALU alu = new ALU();
        MemoriaDados md = new MemoriaDados();
        String instrucao;

        do {
            instrucao = mi.buscarInstrucao(pc.getEndereco());
            System.out.println("Begin Instrucao: " + instrucao);
            System.err.println("----------------------------------------------------------------");

            if(instrucao != null) {

                //Segue para o componente CONTROL, onde irá setar as flags
                control.setFlags(instrucao);

                //Segue para o GERADOR DE IMEDIATOS
                imm.gerarImm(instrucao);

                //Seta os registradores e seus dados no BANCO DE REGISTRADORES
                br.setRegistradores(instrucao);

                //Decide quem irá para ALU: ReadData2 (rs2) ou IMEDIATO
                mux1.setResultado(br.getReadData2(), imm.getImmInt(), control.isALUSrc());

                //Decide qual operação ALU irá realizar
                aluControl.setResultado(instrucao);

                //A ALU realiza a opração
                alu.setResultado(aluControl.getResultado(), br.getReadData1(), mux1.getResultado());

                //Seta a memória de acordo com as flags
                md.executeDataBase(control.isMemWrite(), control.isMemRead(), alu.getResultado(), br.getReadData2());

                //Mux2 encaminha de volta para o banco de registradores
                mux2.setResultado(alu.getResultado(), md.getReadData(), control.isMemToReg());

                //Escreve de volta nos registradores
                br.setWriteData(control.isRegWrite(), mux2.getResultado());
                
                //Imprime as instruções
                br.printBancoRegistradores();
                br.printTabelaRegistradores();
                md.printEnderecosUtilizados();
                md.printState();
                control.printFlags();

                //Decide se irá incrementar o endereço linearmento ou de acordo com BENCH
                sum1.setResultado(pc.getEndereco(), 4); //Acréscimo regular
                sum2.setResultado(pc.getEndereco(), imm.getImmInt()); //Acréscimo com immediate
                mux3.setResultado(sum1.getResultado(), sum2.getResultado(), (control.isBranch() & alu.getEhZero())); //Mux toma a decisão
                pc.setEndereco(mux3.getResultado());

                System.out.println("End Instrucao: " + instrucao);
                System.err.println("----------------------------------------------------------------");
            }

        } while (instrucao != null);

    }
}

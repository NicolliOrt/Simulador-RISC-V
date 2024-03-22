import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<String> setBancoInstrucoes (MemoriaInstrucoes mi) {
        List<String> instrucoes = new ArrayList<>();
    
        instrucoes.add("00000000001100000000001010010011");
        instrucoes.add("00000000101000000000001100010011");
        instrucoes.add("00000001010000000000010100010011");
        instrucoes.add("00000000010101010010000000100011");
        instrucoes.add("00000000011001010010001000100011");
        instrucoes.add("00000000000001010010010110000011");
        instrucoes.add("00000000010001010010011000000011");
        instrucoes.add("00000000110001011000011100110011");
        instrucoes.add("01000000101101100000011110110011");
        instrucoes.add("01000000110001011000100000110011");
        instrucoes.add("00000000101101100111100010110011");
        instrucoes.add("00000000110001011110100100110011");
    
        mi.adicionarInstrucoes(instrucoes);
        
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
            System.out.println("comecou");
            instrucao = mi.buscarInstrucao(pc.getEndereco());

            if(instrucao != null) {

                control.setFlags(instrucao);
                imm.gerarImm(instrucao);
                System.out.println(imm.getImmInt());
                br.setRegistradores(instrucao);
                br.printBancoRegistradores();
                br.printTabelaRegistradores();

                mux1.setResultado(br.getReadData2(), imm.getImmInt(), control.isALUSrc());
                aluControl.setResultado(instrucao);
                alu.setResultado(aluControl.getResultado(), br.getReadData1(), mux1.getResultado());
                md.executeDataBase(control.isMemWrite(), control.isMemRead(), alu.getResultado(), br.getReadData2());
                md.printEnderecosUtilizados();
                md.printState();

                mux2.setResultado(alu.getResultado(), md.getReadData(), control.isMemToReg());
                System.out.println("mux2 = " + mux2.getResultado());

                br.setWriteData(control.isRegWrite(), mux2.getResultado());
                br.printBancoRegistradores();
                br.printTabelaRegistradores();

                sum1.setResultado(pc.getEndereco(), 4);
                sum2.setResultado(pc.getEndereco(), imm.getImmInt());

                mux3.setResultado(sum1.getResultado(), sum2.getResultado(), (control.isBranch() & alu.getEhZero()));

                System.out.println("MUX3 = " + mux3.getResultado());
                
                pc.setEndereco(mux3.getResultado());

                System.out.println("terminou");

            }

        } while (instrucao != null);

    }
}

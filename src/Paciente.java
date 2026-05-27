public class Paciente {
    private String id;
    private double pressaoSistole;
    private double pressaoDiastole;
    private int frequenciaCardiaca;
    private int quantidadeAlteracoes;

    public Paciente(String id) {
        this.id = id;
        this.pressaoSistole = 0.0;
        this.pressaoDiastole = 0.0;
        this.frequenciaCardiaca = 0;
        this.quantidadeAlteracoes = 0;
    }

    public void atualizarSinaisVitais(double pressaoSistole, double pressaoDiastole, int frequenciaCardiaca) {
        this.pressaoSistole = pressaoSistole;
        this.pressaoDiastole = pressaoDiastole;
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public void registrarAlteracao() {
        this.quantidadeAlteracoes++;
    }

    public void zerarAlteracoes() {
        this.quantidadeAlteracoes = 0;
    }

    public String getId() {
        return id;
    }

    public double getPressaoSistole() {
        return pressaoSistole;
    }

    public double getPressaoDiastole() {
        return pressaoDiastole;
    }

    public int getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public int getQuantidadeAlteracoes() {
        return quantidadeAlteracoes;
    }

}
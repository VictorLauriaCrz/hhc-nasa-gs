public class FilaPaciente {
    private static class No {
        Paciente dado;
        No prox;

        No(Paciente dado) {
            this.dado = dado;
            this.prox = null;
        }
    }

    private No ini;
    private No fim;
    private int quantidade;

    public FilaPaciente(){
        this.ini = null;
        this.fim = null;
        this.quantidade = 0;
    }

    public Boolean isEmpty(){
        return this.ini == null;
    }

    public void enqueue(Paciente p){
        No novo = new No(p);
        if (isEmpty()){
            this.ini = novo;
        }
        else {
            this.fim.prox = novo;
        }
        this.fim = novo;
        this.quantidade++;
    }

    public Paciente dequeue(){
        if (isEmpty()){
            return null;
        }
        Paciente p = this.ini.dado;
        this.ini = this.ini.prox;
        if (this.ini == null) {
            this.fim = null;
        }
        this.quantidade--;
        return p;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

}

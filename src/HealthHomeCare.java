import java.util.Scanner;

public class HealthHomeCare {
    static void main(String[] args) {

        FilaPaciente filaMonitora = new FilaPaciente();
        FilaPaciente filaAtencao = new FilaPaciente();


        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("HealthHomeCare Systems ");
            System.out.println("1. Inserir paciente na fila de monitoramento");
            System.out.println("2. Percorrer fila de monitoramento");
            System.out.println("0. Sair (encerrar)");
            System.out.println("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:

                    System.out.print("Digite o código/ID do paciente para cadastro: ");
                    String id = sc.nextLine();

                    Paciente novoPaciente = new Paciente(id);
                    filaMonitora.enqueue(novoPaciente);

                    System.out.println("Paciente " + id + " inserido com sucesso na Fila de Monitoramento.");
                    break;

                case 2:

                    int totalNoInicio = filaMonitora.getQuantidade();

                    if (totalNoInicio == 0) {
                        System.out.println("Não há pacientes na fila de monitoramento no momento.");
                        break;
                    }

                    System.out.println("\n--- Iniciando Ciclo de Monitoramento (" + totalNoInicio + " pacientes) ---");


                    for (int i = 0; i < totalNoInicio; i++) {
                        Paciente p = filaMonitora.dequeue();

                        System.out.println("\n-> Coletando dados do Paciente ID: " + p.getId());
                        System.out.print("Digite a Sístole (mmHg): ");

                        double sistole = sc.nextDouble();

                        System.out.print("Digite a Diástole (mmHg): ");
                        double diastole = sc.nextDouble();

                        System.out.print("Digite a Frequência Cardíaca (bpm): ");
                        int fc = sc.nextInt();

                        p.atualizarSinaisVitais(sistole, diastole, fc);
                        boolean temAlteracao = false;

                        if (sistole > 14.0 || diastole > 10.0 || fc < 60 || fc > 110) {
                            temAlteracao = true;
                        }


                        if (temAlteracao) {
                            p.registrarAlteracao();

                            filaAtencao.enqueue(p);
                            System.out.println("Resultado: ALTERADO! Paciente movido para a Fila de Atenção Especial.");
                        } else {
                            filaMonitora.enqueue(p); // Retorna de forma cíclica para o fim da monitoração
                            System.out.println("Resultado: NORMAL. Paciente reinserido na Fila de Monitoramento.");
                        }
                    }
                    System.out.println("\n--- Ciclo de monitoramento concluído! ---");
                    break;

                case 3:
                    int totalAtencao = filaAtencao.getQuantidade();
                    if (totalAtencao == 0) {
                        System.out.println("Não há pacientes na fila de Atenção no momento");
                        break;
                    }

                    System.out.println("\n FIla de atennção especial com(" + totalAtencao + " pacientes");

                    for (int i = 0; i < totalAtencao; i++) {
                        Paciente p = filaAtencao.dequeue();

                        System.out.println("\n -> Coletando dados do Paciente ID: " + p.getId());
                        System.out.println("Digite a Sístole (mmHg): ");
                        double sistole = sc.nextDouble();
                        System.out.println("Digite a Diástole (mmHg): ");
                        double diastole = sc.nextDouble();
                        System.out.println("Digite a frequência Cardíaca (bpm): ");
                        int fc = sc.nextInt();

                        p.atualizarSinaisVitais(sistole, diastole, fc);


                        boolean medicaoAtualAlterada = (sistole > 14.0 || diastole > 10.0 || fc < 60 || fc > 110);

                        if (!medicaoAtualAlterada) {

                            if (p.getQuantidadeAlteracoes() == 0) {

                                filaMonitora.enqueue(p);
                                System.out.println("Resultado: NORMAL (2ª vez consecutiva). Paciente retornou para a Fila de Monitoramento.");
                            } else {

                                p.zerarAlteracoes();
                                filaAtencao.enqueue(p);
                                System.out.println("Resultado: NORMAL (1ª vez). O contador foi zerado. Paciente permanece na Fila de Atenção para observação.");
                            }
                        } else {

                            p.registrarAlteracao();

                            if (p.getQuantidadeAlteracoes() >= 2) {

                                System.out.println("\n ! Alerta Critico ! Paciente atingiu 2 alterações consecutivas.");
                                System.out.println("Encaminhando Paciente " + p.getId() + " para TELECONSULTA médica...");

                                System.out.println("Simulação da Decisão do Médico:");
                                System.out.println("1 - Paciente deve retornar para o tratamento no HOSPITAL");
                                System.out.println("2 - Paciente pode retornar para a FILA DE MONITORAMENTO (com medicação específica)");
                                System.out.print("Digite a decisão do médico (1 ou 2): ");

                                int decisaoMedico = sc.nextInt();

                                if (decisaoMedico == 1) {
                                    System.out.println("Paciente " + p.getId() + " RETIRADO do sistema residencial e enviado ao hospital.");

                                } else {
                                    p.zerarAlteracoes();
                                    filaMonitora.enqueue(p);
                                    System.out.println("Paciente " + p.getId() + " inserido novamente na Fila de Monitoramento.");
                                }
                            } else {

                                filaAtencao.enqueue(p);
                                System.out.println("Resultado: ALTERADO (1ª vez). Paciente permanece na Fila de Atenção.");
                            }
                        }
                    }
                    System.out.println("\n--- Ciclo de atenção especial concluído! ---");
                    break;

                case 0:
                    System.out.println("Encerrando o sistema da HealthHomeCare...");
                    break;

                default:
                    System.out.println("Opção inválida! Digite novamente.");
            }
        }
        sc.close();
    }

}

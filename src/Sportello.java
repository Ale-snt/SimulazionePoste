public class Sportello implements Runnable {

    private ListaClienti listaClienti;
    private final int minTempoServizio = 1000;
    private final int maxTempoServizio = 4000;
    private String nome;

    public Sportello(ListaClienti listaClienti, String nome) {
        this.listaClienti = listaClienti;
        this.nome = nome;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer clienteServito = listaClienti.rimuoviCliente();

                int tempoServizio = (int) (
                    Math.random() * (maxTempoServizio - minTempoServizio + 1)
                    + minTempoServizio
                );

                Thread.sleep(tempoServizio);

                System.out.println("Servito Cliente Numero \t " + clienteServito +
                        " dallo sportello " + nome);
            }
        } catch (InterruptedException e) {
            System.out.println("Sportello interrotto");
        } finally {
            System.out.println("Sportello " + nome + " Chiuso");
        }
    }
}

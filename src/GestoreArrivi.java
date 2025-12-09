public class GestoreArrivi implements Runnable {

    private ListaClienti listaClienti;
    private final int attesaArrivi = 2000; // 2 sec tra un arrivo e l'altro

    public GestoreArrivi(ListaClienti listaClienti) {
        this.listaClienti = listaClienti;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);
                Integer clienteArrivato = listaClienti.addCliente();
                if (clienteArrivato == null) {
                    System.out.println("Totem: limite massimo raggiunto.");
                    break;
                }
                System.out.println("Arrivo Cliente Numero \t " + clienteArrivato);
            }
        } catch (InterruptedException e) {
            System.out.println("Totem interrotto");
        } finally {
            System.out.println("Posta Chiusa");
        }
    }
}

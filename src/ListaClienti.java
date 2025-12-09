import java.util.ArrayList;

public class ListaClienti {

    private ArrayList<Integer> listaNumeri;
    private int ultimoArrivo;
    private int ultimoServito;

    private final int numeroMassimo = 10;  // limite totale
    private final int maxInAttesa = 3;     // limite clienti in fila

    public ListaClienti() {
        listaNumeri = new ArrayList<>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }

    // CONSUMATORE: Sportello
    public synchronized Integer rimuoviCliente() throws InterruptedException {
        while (listaNumeri.isEmpty()) {
            System.out.println("Sportello: nessun cliente in attesa...");
            wait();
        }

        ultimoServito++;
        listaNumeri.remove(0);
        notifyAll(); // risveglia il totem se la coda era piena
        return ultimoServito;
    }

    // PRODUTTORE: GestoreArrivi
    public synchronized Integer addCliente() throws InterruptedException {
        while (listaNumeri.size() >= maxInAttesa) {
            System.out.println("Totem: troppi clienti in attesa, attendo...");
            wait();
        }

        if (ultimoArrivo < numeroMassimo) {
            ultimoArrivo++;
            listaNumeri.add(ultimoArrivo);
            notifyAll(); // risveglia gli sportelli
            return ultimoArrivo;
        }

        return null; // ufficio saturato
    }
}

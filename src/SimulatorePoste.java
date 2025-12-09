import java.time.Duration;
import java.time.LocalTime;

public class SimulatorePoste {

    public static void main(String[] args) {

        ListaClienti listaClienti = new ListaClienti();

        LocalTime apertura = LocalTime.now();

        Thread arriviThread = new Thread(new GestoreArrivi(listaClienti));
        Thread sportello1 = new Thread(new Sportello(listaClienti, "Marzia"));
        Thread sportello2 = new Thread(new Sportello(listaClienti, "Cinzia"));

        arriviThread.start();
        sportello1.start();
        sportello2.start();

        try {
            // Simulazione di 30 secondi
            Thread.sleep(30000);

            arriviThread.interrupt();
            sportello1.interrupt();
            sportello2.interrupt();

            arriviThread.join();
            sportello1.join();
            sportello2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime chiusura = LocalTime.now();
        Duration durata = Duration.between(apertura, chiusura);

        long minuti = durata.toMinutes();
        long secondi = durata.getSeconds() % 60;

        System.out.println("\n===== STATISTICHE =====");
        System.out.println("Apertura:\t" + apertura);
        System.out.println("Chiusura:\t" + chiusura);
        System.out.println("Durata:\t\t" + minuti + "min " + secondi + "sec");
    }
}

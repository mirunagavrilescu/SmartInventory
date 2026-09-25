import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        ArrayList<Produs> depozit = new ArrayList<>();
        System.out.println("--- Sistemul SmartInventory ---");
        System.out.println("Incarcam datele din fisier...");
        try {
            File fisier = new File("stoc.txt");
            Scanner cititorFisier = new Scanner(fisier);
            while (cititorFisier.hasNextLine()) {
                String linie = cititorFisier.nextLine();
                String[] bucati = linie.split(",");
                String nume = bucati[0];
                double pret = Double.parseDouble(bucati[1]);
                int stoc = Integer.parseInt(bucati[2]);
                depozit.add(new Produs(nume, pret, stoc));
            }
            cititorFisier.close();
            System.out.println("-> Date incarcate! Avem " + depozit.size() + " produse in depozit.\n");
        } catch (Exception e) {
            System.out.println("-> Niciun fisier gasit. Pornim cu depozitul gol.\n");
        }

        Scanner scanner = new Scanner(System.in);
        boolean aplicatiaRuleaza = true; // „Întrerupătorul” aplicației

        // Bucla WHILE ține aplicația pornită până când aplicatiaRuleaza devine false
        while (aplicatiaRuleaza) {
            System.out.println("\n=== MENIU SMART INVENTORY ===");
            System.out.println("1. Vezi tot stocul");
            System.out.println("2. Cauta un produs");
            System.out.println("3. Iesire");
            System.out.print("Alege o optiune (tasteaza 1, 2 sau 3): ");

            String optiune = scanner.nextLine(); // Citim ce a ales utilizatorul

            if (optiune.equals("1")) {
                System.out.println("\n--- STOC CURENT ---");
                for (Produs p : depozit) {
                    System.out.println("- " + p.nume + " | Cantitate: " + p.cantitateInStoc);
                }

            } else if (optiune.equals("2")) {
                System.out.print("\nCe produs cauti?: ");
                String produsCautat = scanner.nextLine();
                boolean gasit = false;

                for (Produs p : depozit) {
                    if (p.nume.equalsIgnoreCase(produsCautat)) {
                        System.out.println("-> Gasit! Stoc: " + p.cantitateInStoc + " de " + p.nume);
                        gasit = true;
                        break;
                    }
                }
                if (!gasit) {
                    System.out.println("-> Ne pare rau, produsul nu este pe stoc.");
                }

            } else if (optiune.equals("3")) {
                System.out.println("\nSalvam stocul in fisier...");

                // Încercăm să scriem în fișier
                try {
                    // Creăm (sau suprascriem) un fișier numit "stoc.txt"
                    FileWriter fisier = new FileWriter("stoc.txt");

                    // Trecem prin toate produsele din depozit
                    for (Produs p : depozit) {
                        // Le scriem pe rând, separate prin virgulă.
                        // "\n" înseamnă trecere la rând nou (Enter).
                        fisier.write(p.nume + "," + p.pret + "," + p.cantitateInStoc + "\n");
                    }

                    fisier.close(); // Salvăm și închidem fișierul
                    System.out.println("-> Datele au fost salvate cu succes in stoc.txt!");

                } catch (Exception e) {
                    // Dacă ceva merge prost (ex: nu avem permisiuni), prindem eroarea aici
                    System.out.println("Eroare la salvare: " + e.getMessage());
                }

                System.out.println("Se inchide sistemul... O zi frumoasa!");
                aplicatiaRuleaza = false; // Oprește aplicația

            } else {
                System.out.println("Eroare: Te rog alege o optiune valida (1, 2 sau 3).");
            }
        } // Aici se termină WHILE. Dacă nu ai ales 3, se întoarce automat sus la Meniu!

        scanner.close();
    }
}
class Produs {
    String nume;
    double pret;
    int cantitateInStoc;

    Produs(String numePrimit, double pretPrimit, int cantitatePrimita) {
        nume = numePrimit;
        pret = pretPrimit;
        cantitateInStoc = cantitatePrimita;
    }

    void vinde(int cantitate) {
        if (cantitate <= cantitateInStoc) {
            cantitateInStoc -= cantitate;
            System.out.println("Vandut: " + cantitate + "x " + nume);
        } else {
            System.out.println("Eroare: Stoc insuficient");
        }
    }
}

class ProdusPerisabil extends Produs {
    String dataExpirare;

    ProdusPerisabil(String numePrimit, double pretPrimit, int cantitatePrimita, String dataPrimita) {
        super(numePrimit, pretPrimit, cantitatePrimita);
        dataExpirare = dataPrimita;
    }
}
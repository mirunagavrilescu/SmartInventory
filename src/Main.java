import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Produs> depozit = new ArrayList<>();
        depozit.add(new Produs("Cafea Boabe", 85.50, 15));
        depozit.add(new Produs("Apa Plata", 5.5, 30));
        depozit.add(new ProdusPerisabil("Lapte", 7.5, 20, "25-August-2026"));

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
                System.out.println("Se inchide sistemul... O zi frumoasa!");
                aplicatiaRuleaza = false; // Asta va opri bucla while!

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
package ma.credit.backend.config;

import ma.credit.backend.entities.*;
import ma.credit.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final RemboursementRepository remboursementRepository;

    public DataInitializer(ClientRepository clientRepository,
                           CreditPersonnelRepository creditPersonnelRepository,
                           CreditImmobilierRepository creditImmobilierRepository,
                           CreditProfessionnelRepository creditProfessionnelRepository,
                           RemboursementRepository remboursementRepository) {
        this.clientRepository = clientRepository;
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.creditImmobilierRepository = creditImmobilierRepository;
        this.creditProfessionnelRepository = creditProfessionnelRepository;
        this.remboursementRepository = remboursementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create some clients
        Client alice = new Client(null, "Alice Dupont", "alice.dupont@example.com", null);
        Client bob   = new Client(null, "Bob Martin",   "bob.martin@example.com",   null);
        clientRepository.saveAll(Arrays.asList(alice, bob));

        // Create a personal credit for Alice
        CreditPersonnel cp = new CreditPersonnel();
        cp.setDateDemande(new Date());
        cp.setStatut("EN_COURS");
        cp.setDateAcceptation(null);
        cp.setMontant(5000.0);
        cp.setDuree(24);
        cp.setTauxInteret(3.5);
        cp.setMotif("Achat voiture");
        cp.setClient(alice);
        creditPersonnelRepository.save(cp);

        // Create an immobilier credit for Bob
        CreditImmobilier ci = new CreditImmobilier();
        ci.setDateDemande(new Date());
        ci.setStatut("ACCEPTE");
        ci.setDateAcceptation(new Date());
        ci.setMontant(150000.0);
        ci.setDuree(180);
        ci.setTauxInteret(2.8);
        ci.setTypeBien("MAISON");
        ci.setClient(bob);
        creditImmobilierRepository.save(ci);

        // Create a professionnel credit for Alice
        CreditProfessionnel cp2 = new CreditProfessionnel();
        cp2.setDateDemande(new Date());
        cp2.setStatut("ACCEPTE");
        cp2.setDateAcceptation(new Date());
        cp2.setMontant(30000.0);
        cp2.setDuree(60);
        cp2.setTauxInteret(4.2);
        cp2.setMotif("Extension de locaux");
        cp2.setRaisonSocialeEntreprise("Dubois SARL");
        cp2.setClient(alice);
        creditProfessionnelRepository.save(cp2);

        // Add reimbursements to the first credit
        Remboursement r1 = new Remboursement(null, new Date(), 220.0, "MENSUALITE", cp);
        Remboursement r2 = new Remboursement(null, new Date(), 500.0, "REMBOURSEMENT_ANTICIPE", cp);
        remboursementRepository.saveAll(Arrays.asList(r1, r2));

        System.out.println("Test data initialized.");
    }
}

package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.*;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp-jpa-banques");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Adresse adresse1 = new Adresse();
        Adresse adresse2 = new Adresse();
        Adresse adresse3 = new Adresse();
        Banque banque1 = new Banque();

        banque1.setNom("la banque");
        adresse1.setNumero(1);
        adresse1.setRue("rue1");
        adresse1.setVille("ville1");
        adresse1.setCodePostal(1);
        adresse2.setNumero(2);
        adresse2.setRue("rue2");
        adresse2.setVille("ville2");
        adresse2.setCodePostal(2);
        adresse3.setNumero(3);
        adresse3.setRue("rue3");
        adresse3.setVille("ville3");
        adresse3.setCodePostal(3);

        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();

        client1.setPrenom("prénom du client1");
        client1.setNom("nom du client1");
        client1.setDateNaissance(LocalDate.now());
        client1.setAdresse(adresse1);
        client2.setPrenom("prénom du client2");
        client2.setNom("nom du client2");
        client2.setDateNaissance(LocalDate.now());
        client2.setAdresse(adresse2);
        client3.setPrenom("prénom du client3");
        client3.setNom("nom du client3");
        client3.setDateNaissance(LocalDate.now());
        client3.setAdresse(adresse3);
        banque1.setClients(new HashSet<>(Arrays.asList(client1, client2, client3)));
        client1.setBanque(banque1);
        client2.setBanque(banque1);
        client3.setBanque(banque1);
        Compte compteAssocie = new Compte();
        compteAssocie.setSolde(123456.78);
        compteAssocie.setNumero("X9MP26");
        compteAssocie.setClients(new HashSet<>(Arrays.asList(client1, client2)));
        client1.setComptes(Collections.singleton(compteAssocie));
        client2.setComptes(Collections.singleton(compteAssocie));

        AssuranceVie assuranceVie = new AssuranceVie();
        LivretA livretA = new LivretA();
        assuranceVie.setDateFin(LocalDate.now());
        assuranceVie.setTaux(10.0);
        assuranceVie.setNumero("XXXXXYYYY5");
        assuranceVie.setSolde(5000.23);
        livretA.setTaux(15.0);
        livretA.setNumero("XXXXYYYYZ3");
        livretA.setSolde(43698.32);
        assuranceVie.setClients(Collections.singleton(client3));
        livretA.setClients(Collections.singleton(client3));
        client3.setComptes(new HashSet<>(Arrays.asList(livretA, assuranceVie)));
        Operation operation1 = new Operation();
        Virement operation2 = new Virement();
        operation1.setDate(LocalDateTime.now());
        operation1.setMontant(1000.0);
        operation1.setMotif("le motif 1");
        operation1.setCompte(compteAssocie);
        operation2.setDate(LocalDateTime.now());
        operation2.setMontant(125.02);
        operation2.setMotif("le motif 2");
        operation2.setBeneficiaire("prénom du client2");
        operation2.setCompte(compteAssocie);
        compteAssocie.setOperations(new HashSet<>(Arrays.asList(operation1, operation2)));

        em.persist(adresse1);
        em.persist(adresse2);
        em.persist(adresse3);
        em.persist(banque1);
        em.persist(client1);
        em.persist(client2);
        em.persist(client3);
        em.persist(compteAssocie);
        em.persist(livretA);
        em.persist(assuranceVie);
        em.persist(operation1);
        em.persist(operation2);
        em.getTransaction().commit();
        //connection à la base de données
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "9090")
                    .start();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

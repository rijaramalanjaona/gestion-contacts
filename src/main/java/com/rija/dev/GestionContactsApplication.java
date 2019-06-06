package com.rija.dev;

import com.rija.dev.dao.ContactRepository;
import com.rija.dev.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class GestionContactsApplication implements CommandLineRunner {
    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionContactsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        contactRepository.save(new Contact("nom1", "prenom1", dateFormat.parse("01/10/1990"),
                "toto1@test.com", 11111, "nom1.png"));
        contactRepository.save(new Contact("nom2", "prenom2", dateFormat.parse("02/10/1990"),
                "toto2@test.com", 11111, "nom2.png"));
        contactRepository.save(new Contact("nom3", "prenom3", dateFormat.parse("03/10/1990"),
                "toto3@test.com", 11111, "nom3.png"));

        contactRepository.findAll().forEach(c-> {
            System.out.println(c.getNom());
        });
    }
}

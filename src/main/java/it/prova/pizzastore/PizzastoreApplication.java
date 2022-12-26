package it.prova.pizzastore;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;

@SpringBootApplication
public class PizzastoreApplication implements CommandLineRunner {
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PizzastoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.ROLE_PROPRIETARIO));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.ROLE_FATTORINO));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Antonio", "Verdi", new Date());
			pizzaiolo.setEmail("u.pizzaiolo@prova.it");
			pizzaiolo.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId()); 
		}

		if (utenteServiceInstance.findByUsername("proprietario") == null) {
			Utente proprietario = new Utente("proprietario", "proprietario", "Antonioo", "Verdii", new Date());
			proprietario.setEmail("u.proprietario@prova.it");
			proprietario.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
			utenteServiceInstance.inserisciNuovo(proprietario);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}

		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Antoniooo", "Verdiii", new Date());
			fattorino.setEmail("u.fattorino@prova.it");
			fattorino.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
			utenteServiceInstance.inserisciNuovo(fattorino);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(fattorino.getId());
		}
		
	}

}

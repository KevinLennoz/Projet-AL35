package fr.eql.al35;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
=======
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
>>>>>>> branch 'main' of https://github.com/KevinLennoz/Projet-AL35.git

@EnableJpaRepositories(basePackages = "fr.eql.al35.repository")
@SpringBootApplication
public class FavoriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoriteApplication.class, args);
		applicationReadyEvent();
	}

	//Lorsque l'ensemble des config sont finalisée = lancement du navigateur sur l'url défini
	@EventListener({ApplicationReadyEvent.class})
	static void applicationReadyEvent() {
	    System.out.println("¯\\_(ツ)_/¯ <----- Romain  ԅ(≖‿≖ԅ) <----- Mathilda  ⊂(◉‿◉)つ  <----- Charles  (° ͜ʖ͡°)╭∩╮ <----- Floriane  (ง'̀-'́)ง <----- Kevin");
	    openBrowser("http://localhost:8085/home");
	}

	public static void openBrowser(String url) {
		
	    if(Desktop.isDesktopSupported()){
	        Desktop desktop = Desktop.getDesktop();
	        try {
	            desktop.browse(new URI(url));
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	        }
	    }else{
	        Runtime runtime = Runtime.getRuntime();
	        try {
	            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}

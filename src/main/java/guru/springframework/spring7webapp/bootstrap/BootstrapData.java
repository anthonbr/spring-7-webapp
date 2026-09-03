package guru.springframework.spring7webapp.bootstrap;

import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt, Spring Framework Guru.
 */
@Component
public class BootstrapData implements CommandLineRunner
{

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository)
  {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception
  {

    // Add first publisher
    Publisher addisonWesley = new Publisher();
    addisonWesley.setPublisherName("Addison Wesley");
    addisonWesley.setState("New York");
    addisonWesley.setZip("11697");
    Publisher addisonWesleySaved = publisherRepository.save(addisonWesley);

    // Add second publisher
    Publisher wrox = new Publisher();
    wrox.setPublisherName("Wrox");
    wrox.setState("New York");
    wrox.setZip("10001");
    Publisher wroxSaved = publisherRepository.save(wrox);

    // Add first author
    Author eric = new Author();
    eric.setFirstName("Eric");
    eric.setLastName("Evans");

    // Add first book
    Book ddd = new Book();
    ddd.setTitle("Domain Driven Design");
    ddd.setIsbn("123456");
    ddd.setPublisher(addisonWesleySaved);

    Author ericSaved = authorRepository.save(eric);
    Book dddSaved = bookRepository.save(ddd);
    addisonWesleySaved.getBooks().add(dddSaved);

    // Add second author
    Author rod = new Author();
    rod.setFirstName("Rod");
    rod.setLastName("Johnson");

    // Add second book
    Book noEJB = new Book();
    noEJB.setTitle("J2EE Development without EJB");
    noEJB.setIsbn("54757585");
    noEJB.setPublisher(wroxSaved);

    Author rodSaved = authorRepository.save(rod);
    Book noEJBSaved = bookRepository.save(noEJB);
    wroxSaved.getBooks().add(noEJBSaved);

    ericSaved.getBooks().add(dddSaved);
    rodSaved.getBooks().add(noEJBSaved);

    authorRepository.save(ericSaved);
    authorRepository.save(rodSaved);


    System.out.println("In Bootstrap");
    System.out.println("Author Count: " + authorRepository.count());
    System.out.println("Book Count: " + bookRepository.count());
    System.out.println("Publisher Count: " + publisherRepository.count());
  }
}











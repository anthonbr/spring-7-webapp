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
    Author ericSaved = authorRepository.save(eric); // save the author

    // Add first book
    Book ddd = new Book();
    ddd.setTitle("Domain Driven Design");
    ddd.setIsbn("123456");
    ddd.setPublisher(addisonWesleySaved);
    addisonWesleySaved.getBooks().add(ddd); // update publisher's hashSet of books
    ddd.getAuthors().add(ericSaved);        // Update the book's hashSet of authors

    Book dddSaved = bookRepository.save(ddd);  // save the book, also creates the row in the join table

    // Add second author
    Author rod = new Author();
    rod.setFirstName("Rod");
    rod.setLastName("Johnson");
    Author rodSaved = authorRepository.save(rod); // save the author

    // Add second book
    Book noEJB = new Book();
    noEJB.setTitle("J2EE Development without EJB");
    noEJB.setIsbn("54757585");
    noEJB.setPublisher(wroxSaved);
    wroxSaved.getBooks().add(noEJB);  // update publisher's hashSet of books
    noEJB.getAuthors().add(rodSaved); // Update the book's hashSet of authors

    Book noEJBSaved = bookRepository.save(noEJB); // save the book, also creates the row in the join table

    System.out.println("In Bootstrap");
    System.out.println("Author Count: " + authorRepository.count());
    System.out.println("Book Count: " + bookRepository.count());
    System.out.println("Publisher Count: " + publisherRepository.count());
  }
}











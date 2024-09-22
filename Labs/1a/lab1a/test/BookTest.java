import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Book class.
 */

public class BookTest {
  private Book book;

  @Before
  public void setUp() {

    book = new Book("Fault in our Stars",
            new Person("Ram", "Dutt", 1980),
            35.29f);
  }

  @Test
  public void testTitle() {
    assertEquals("Fault in our Stars", book.getTitle());
  }

  @Test
  public void testAuthorFirstName() {
    assertEquals("Ram", book.getAuthor().getFirstName());
  }

  @Test
  public void testAuthorLastName() {
    assertEquals("Dutt", book.getAuthor().getLastName());
  }

  @Test
  public void testYearPublished() {
    assertEquals(1980, book.getAuthor().getYearOfBirth());
  }

  @Test
  public void testPrice() {
    assertEquals(35.29f, book.getPrice(), 0.001);
  }
}
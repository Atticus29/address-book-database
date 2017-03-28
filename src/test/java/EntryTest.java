import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EntryTest {
  // private Entry testEntry;

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/address_book_rjmf_test", null, null);

  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM entries *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  //
  @Test
  public void find_returnsCorrectEntry_true(){
    Entry testEntry = new Entry("Ryan", "555-555-5555", "123 Maple Lane Portland, OR 97203", "Ryan@isCool.com");
    testEntry.save();
    Entry testEntry2 = new Entry("Mark", "555-555-5556", "124 Maple Lane Portland, OR 97203", "Mark@isAlright.com");
    testEntry2.save();
    assertEquals(Entry.find(testEntry2.getId()), testEntry2);
  }

  @Test
  public void save_SavesAnEntry_true(){
    Entry testEntry = new Entry("Ryan", "555-555-5555", "123 Maple Lane Portland, OR 97203", "Ryan@isCool.com");
    testEntry.save();
    // Entry retrievedEntry = Entry.find(1);
    Entry retrievedEntry = Entry.find(testEntry.getId());
    // assertTrue(retrievedEntry instanceof Entry);
    assertEquals("Ryan",retrievedEntry.getName());
  }

}

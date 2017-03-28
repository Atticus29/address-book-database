import org.sql2o.*;

public class Entry {
  private String name;
  private String phone;
  private String address;
  private String email;
  private int id;


  public Entry(String name, String phone, String address, String email) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.email = email;
  }

  public String getName(){
    return name;
  }

  public String getPhone(){
    return phone;
  }
  public String getAddress(){
    return address;
  }
  public String getEmail(){
    return email;
  }

  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object secondEntry){
    if(!(secondEntry instanceof Entry)){
      return false;
    } else{
      Entry testEntry = (Entry) secondEntry;
      return (this.getName().equals(testEntry.getName()));
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO entries (name, phone, address, email) VALUES (:name, :phone, :address, :email);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("address", this.address)
        .addParameter("email", this.email)
        .executeUpdate()
        .getKey();
    }
  }

  public static Entry find(int id){
    try(Connection con = DB.sql2o.open()){
      String sqlCommand = "SELECT * FROM entries WHERE id=:id;";
      Entry foundEntry = con.createQuery(sqlCommand)
        .addParameter("id", id)
        .executeAndFetchFirst(Entry.class);
      return foundEntry;
    }
  }
}

import org.sql2o.*;

public class Entry {
  private String name;
  private String phone;
  private String address;
  private String email;


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


}

package vn.edu.ntu.nguyenanhhai.models;

public class Contact {
  int id;
  String name, phone, addr, birthday;

  public Contact(int id, String name, String phone, String addr, String birthday) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.addr = addr;
    this.birthday = birthday;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
}

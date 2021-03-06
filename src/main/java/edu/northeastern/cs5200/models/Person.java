package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Enumerated(EnumType.STRING)
  private RoleType role;
  private String fistName;
  private String lastName;
  private String username;
  private String password;
  private String gender;
  private String email;
  private Date dob;
  @OneToMany(mappedBy = "person")
  private Collection<Phone> phones;
  @OneToMany(mappedBy = "person")
  private Collection<Address> addresses;


  public Person() {
  }

  public Person(RoleType role, String fistName, String lastName, String username, String password, String gender, String email, Date dob) {
    this.role = role;
    this.fistName = fistName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.gender = gender;
    this.email = email;
    this.dob = dob;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFistName() {
    return fistName;
  }

  public void setFistName(String fistName) {
    this.fistName = fistName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Collection<Phone> getPhones() {
    return phones;
  }

  public void setPhones(Collection<Phone> phones) {
    this.phones = phones;
  }

  public Collection<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Collection<Address> addresses) {
    this.addresses = addresses;
  }

  public void addAddress(Address address) {
    this.addresses.add(address);
  }

  public RoleType getRole() {
    return role;
  }

  public void setRole(RoleType role) {
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}

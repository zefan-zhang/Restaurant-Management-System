package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Customer extends Person {

  private ContractStatus userAgreement;
  private boolean VIP;
  private String paymentMethod;

  @OneToMany(mappedBy = "customer")
  private Collection<Order> orders;

  @OneToMany(mappedBy = "receiver")
  private Collection<Text> texts;

  @OneToMany(mappedBy = "customer")
  private Collection<FoodReview> foodReviews;

  @OneToMany(mappedBy = "customer")
  private Collection<WishList> wishLists;

  public Customer() {
    super();
  }

  public Customer(RoleType role, String fistName, String lastName, String username, String password, String gender, String email, Date dob) {
    super(role, fistName, lastName, username, password, gender, email, dob);
  }

  public Collection<Text> getTexts() {
    return texts;
  }

  public void setTexts(Collection<Text> texts) {
    this.texts = texts;
  }

  public Collection<Order> getOrders() {
    return orders;
  }

  public void setOrders(Collection<Order> orders) {
    this.orders = orders;
  }

  public ContractStatus getUserAgreement() {
    return userAgreement;
  }

  public void setUserAgreement(ContractStatus userAgreement) {
    this.userAgreement = userAgreement;
  }

  public boolean isVIP() {
    return VIP;
  }

  public void setVIP(boolean VIP) {
    this.VIP = VIP;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Collection<FoodReview> getFoodReviews() {
    return foodReviews;
  }

  public void setFoodReviews(Collection<FoodReview> foodReviews) {
    this.foodReviews = foodReviews;
  }

  public Collection<WishList> getWishLists() {
    return wishLists;
  }

  public void setWishLists(Collection<WishList> wishLists) {
    this.wishLists = wishLists;
  }

  public void addWishList(WishList wishList) {
    this.wishLists.add(wishList);
  }
}

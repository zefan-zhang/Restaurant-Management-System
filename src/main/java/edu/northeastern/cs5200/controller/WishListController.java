package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.WishList;

@Controller
public class WishListController {

  @Autowired
  private RestaurantDao restaurantDao;

  @GetMapping("/customer/wishlist/{customerId}")
  public String goWishList(@PathVariable(name = "customerId") int id, Model model) {
    List<WishList> wishLists = restaurantDao.findWishListByCustomerId(id);
    Customer customer = restaurantDao.findCustomerById(id);
    model.addAttribute("wishLists", wishLists);
    model.addAttribute("customer", customer);
    return "customer_wishlist";
  }

  @GetMapping("/owner/wishlist/{username}")
  public String goWishList(@PathVariable(name = "username") String username, Model model) {
    Owner owner = restaurantDao.findOwnerByUname(username);
    List<WishList> wishLists = restaurantDao.findAllWishList();
    model.addAttribute("owner", owner);
    model.addAttribute("wishLists", wishLists);
    return "owner_wishlist";
  }

  @GetMapping("/wishlist/{orderId}/{username}")
  public String getWishListByOrderId(@PathVariable(name = "orderId") int id,
                                     @PathVariable(name = "username") String username, Model model) {
    Owner owner = restaurantDao.findOwnerByUname(username);
    List<WishList> wishLists = restaurantDao.findWishListByOrderId(id);
    model.addAttribute("owner", owner);
    model.addAttribute("wishLists", wishLists);
    return "owner_wishlist";
  }

  @GetMapping("/owner/wishlist/{foodId}/{ownerId}")
  public String createWishListFoodCustomer(@PathVariable(name = "foodId") int foodId,
                                           @PathVariable(name = "ownerId") int ownerId,
                                           Model model) {
    Owner owner = restaurantDao.findOwnerById(ownerId);
    WishList wishList = restaurantDao.createWishListForFood(foodId);
    model.addAttribute("wishList", wishList);
    model.addAttribute("owner", owner);
    return "owner_add_food_wishlist";
  }

  @GetMapping("/customer/wishlist/{foodId}/{customerId}")
  public String addFoodToWishListByCustomer(@PathVariable(name = "foodId") int foodId,
                                            @PathVariable(name = "customerId") int customerId,
                                            Model model) {
    Customer customer = restaurantDao.findCustomerById(customerId);
    WishList wishList = restaurantDao.createWishListForFood(foodId);
    wishList.setCustomer(customer);
    model.addAttribute("customer", customer);
    model.addAttribute("wishList", wishList);
    return "customer_add_food_wishlist";
  }

  @GetMapping("/edit_wishlist/{wishlistId}/{username}")
  public String editWishListByOwner(@PathVariable(name = "wishlistId") int id,
                                    @PathVariable(name = "username") String username,
                                    Model model) {
    WishList wishList = restaurantDao.findWishListById(id);
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("wishList", wishList);
    model.addAttribute("owner", owner);
    return "owner_update_wishlist";
  }

  @GetMapping("/edit_wishlist/{listId}")
  public String editWishListByCustomer(@PathVariable(name = "listId") int listId,
                                       Model model) {
    WishList wishList = restaurantDao.findWishListById(listId);
    Customer customer = restaurantDao.findCustomerById(wishList.getCustomer().getId());
    model.addAttribute("wishList", wishList);
    model.addAttribute("customer", customer);
    return "customer_update_wishlist";
  }

  @PostMapping(value = "/owner/save_wishlist")
  public String saveWishListByOwner(@ModelAttribute("wishList") WishList wishList) {
    restaurantDao.saveWishList(wishList);
    return "redirect:/owner/wishlist/admin";
  }


  @PostMapping(value = "/customer/save_wishlist/{userId}")
  public String saveWishListByCustomer(@PathVariable(name = "userId") int userId,
                                       @ModelAttribute("wishList") WishList wishList) {

    restaurantDao.saveWishList(wishList);
    return "redirect:/customer/wishlist/" + userId;
  }

  @GetMapping(value = "/delete_wishlist/{listId}")
  public String deleteWishListByOwner(@PathVariable(name = "listId") int id) {
    restaurantDao.deleteWishListById(id);
    return "redirect:/owner/wishlist/admin";
  }

  @GetMapping(value = "/delete_wishlist/{listId}/{userId}")
  public String deleteWishListByCustomer(@PathVariable(name = "listId") int listId,
                                         @PathVariable(name = "userId") int userId) {
    restaurantDao.deleteWishListById(listId);
    return "redirect:/customer/wishlist/" + userId;
  }
}

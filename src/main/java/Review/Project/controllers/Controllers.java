package Review.Project.controllers;

import Review.Project.Models.UserModel;
import Review.Project.services.ReviewServices;
import Review.Project.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.hibernate.classic.Lifecycle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@ControllerAdvice
@CrossOrigin("http://localhost:3000")
public class Controllers implements InitializingBean {




  @Autowired
  private UserServices service;

  @Autowired
  private ReviewServices reviewService;

  private HttpServletRequest request;

  // CREATE USER
  @PostMapping("/create/{pass}")
  public Map getUsers(@RequestBody UserModel data,@PathVariable String pass){

    return this.service.createUser(data, pass);

  }



  // LOG IN USER
  @PostMapping("/logIn")
  public Map logIn(@RequestBody UserModel data){

    return this.service.logIn(data);

  }

  // CREATE REVIEW
  @PostMapping("/review/create/{title}/{des}/{id}")
  public Map createReview(@RequestParam("image")MultipartFile img, @PathVariable String title,@PathVariable String des,@PathVariable String id) throws IOException {


    return this.reviewService.createReview(title, des, Long.valueOf(id), img);



  }

  // LOAD IMAGE
  @GetMapping("{name:.+}")
  public ResponseEntity<Resource> loadImage(@PathVariable String name) throws IOException {

     Resource file = reviewService.loadImg(name);

     String content = Files.probeContentType(file.getFile().toPath());

     return ResponseEntity
    .ok()
    .header(HttpHeaders.CONTENT_TYPE, content)
    .body(file);
  }

  @GetMapping(value = {"/simple/", "/simple/{game}"})
  public String pene(@PathVariable (value = "game", required = false)String game) throws IOException {

    String result = "pepe";

    if (game == null){return result + "pepe";}
    else return result;
  }


  @GetMapping("/leche")
  public String pene2() throws IOException {

    System.out.println("lechita");

   return "pene";
  }

  // GET ALL REVIEWS
  @GetMapping("/get/reviews")
  public Optional<ArrayList> getReviews(){

    return this.reviewService.getReviews();
  }

  // GET SINGLE REVIEW
  @GetMapping("/get/review/{id}")
  public Optional<Map> getSingleReview(@PathVariable String id){

    return this.reviewService.getSingleReviews(id);

  }

  // GET USER REVIEWS
  @GetMapping("/get/user/reviews/{id}/{reviewId}")
  public Optional<ArrayList<Map>> getUserReview(@PathVariable Long id ,@PathVariable Long reviewId){


      return this.reviewService.getUserReviews(id, reviewId);


  }


  // DELETE REVIEW
  @DeleteMapping("/delete/{id}")
  public Map deleteReview(@PathVariable Long id){

    return this.reviewService.deleteReviews(id);

  }

  // EDIT REVIEW
  @PutMapping("/edit/review/{title}/{des}/{id}/{userId}")
  public Map deleteReview(@RequestParam(value = "image",required = false)Optional<MultipartFile> img, @PathVariable Optional<String> title,@PathVariable Optional<String> des,@PathVariable Long id,@PathVariable String userId){

    System.out.println("me pide leche");

    return this.reviewService.editReview(title.get(), des.get(), id, img.get(),userId);
  }


  public void afterPropertiesSet(){
    System.out.println("lechitaa");
  }

}

package Review.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.classic.Lifecycle;

@Entity
@Table(name = "reviews")
public class ReviewModel implements Lifecycle {

    @Getter @Setter @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Lob @Getter @Setter
    private byte[] img;
    @Column @Getter @Setter
    private String title;
    @Column @Getter @Setter
    private Long user;
    @Column @Getter @Setter
    private String des;

    public void start (){
        System.out.println("se inicio lechee");
    }

    public void destroy (){
        System.out.println("se termino la lechee");
    }

}

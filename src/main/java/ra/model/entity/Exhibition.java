package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Exhibition")
@Data
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitionID")
    private int exhibitionID;

    @Column(name = "exhibitionTitle")
    private String exhibitionTitle;

    @Column(name = "exhibitionDescription")
    private String exhibitionDescription;

    @Column(name = "exhibitionCreatedDate")
    private LocalDate exhibitionCreatedDate;

    @Column(name = "exhibitionExpiredDate")
    private LocalDate exhibitionExpiredDate;

    @Column(name = "exhibitionStatus")
    private boolean exhibitionStatus;
    @OneToMany
    @JoinColumn(name = "Product")
    private List<Product> productList;

    @ManyToMany
    @JoinTable(name= "Exhibition_Tag", joinColumns = @JoinColumn(name = "exhibitionID"), inverseJoinColumns = @JoinColumn(name="tagID"))
    private List<Tag> listTag;
}

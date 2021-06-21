package co.dheeraj.dbcron.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address  implements Serializable {

    private static final long serialVersionUID = -9147413721012310522L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String addLine1;
    private String addLine2;
    private String city;
    private String pincode;

    @OneToOne
    @JoinColumn(name="EMPLOYEE_ID", nullable=true)
    private Employee employee;

    public Address(String addLine1, String addLine2, String city, String pincode) {
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.city = city;
        this.pincode = pincode;
    }
}

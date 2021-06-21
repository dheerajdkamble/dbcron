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
public class Employee  implements Serializable {

    private static final long serialVersionUID = 7015736294523878608L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="employee")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEPARTMENT_ID", nullable=true)
    private Department department;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}

package co.dheeraj.dbcron.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = 4456983062529301742L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String deptName;

    @OneToMany(cascade=ALL, mappedBy="department")
    private List<Employee> employees;

    public Department(String deptName) {
        this.deptName = deptName;
    }
}

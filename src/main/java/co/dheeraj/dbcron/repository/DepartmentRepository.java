package co.dheeraj.dbcron.repository;

import co.dheeraj.dbcron.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByDeptName(String deptName);

    @Query("select d from Department d inner join Employee e on d.id=e.department.id inner join Address a on e.id = a.employee.id")
    List<Department> getAllDepartmentsForCity(String city);
}

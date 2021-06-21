package co.dheeraj.dbcron.controller;

import co.dheeraj.dbcron.model.Address;
import co.dheeraj.dbcron.model.Department;
import co.dheeraj.dbcron.model.Employee;
import co.dheeraj.dbcron.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments(@RequestParam(required = false) String deptName) {
        try {
            List<Department> departments = new ArrayList<Department>();

            if (deptName == null)
                departmentService.getAll().forEach(departments::add);
            else
                departmentService.findByDeptName(deptName).forEach(departments::add);

            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getTutorialById(@PathVariable("id") long id) {
        Optional<Department> employeeData = departmentService.get(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createTutorial(@RequestBody Department department) {
        try {
            Department _department = new Department(department.getDeptName());
            List<Employee> employees = new ArrayList<>();
            for(Employee employee : department.getEmployees()) {
                Address address = new Address(employee.getAddress().getAddLine1(), employee.getAddress().getAddLine2(), employee.getAddress().getCity(), employee.getAddress().getPincode());
                employees.add(new Employee(employee.getFirstName(), employee.getLastName(), address));
            }
            _department.setEmployees(employees);

            Department _department2 = departmentService
                    .save(_department);
            return new ResponseEntity<>(_department2, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
        Optional<Department> departmentData = departmentService.get(id);

        if (departmentData.isPresent()) {
            Department _department = departmentData.get();
            _department.setDeptName(department.getDeptName());
            return new ResponseEntity<>(departmentService.update(_department), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departments")
    public ResponseEntity<HttpStatus> deleteAllDepartments() {
        try {
            departmentService.delete(null);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

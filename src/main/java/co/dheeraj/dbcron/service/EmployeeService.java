package co.dheeraj.dbcron.service;

import co.dheeraj.dbcron.model.Employee;
import co.dheeraj.dbcron.repository.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class EmployeeService implements CrudService<Employee> {

    @Value("${scheduler.delay}")
    private Long delay;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Optional<Employee> get(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return save(employee);
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            employeeRepository.deleteAll();
        else
            employeeRepository.deleteById(id);
    }

    public void createEmployee() {
        System.out.println("Creating...");
    }
}

package co.dheeraj.dbcron.service;

import co.dheeraj.dbcron.model.Department;
import co.dheeraj.dbcron.model.Employee;
import co.dheeraj.dbcron.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements CrudService<Department> {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Optional<Department> get(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public List<Department> findByDeptName(String deptName) {
        return departmentRepository.findByDeptName(deptName);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        return save(department);
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            departmentRepository.deleteAll();
        else
            departmentRepository.deleteById(id);
    }

    public List<Department> getAllDepartmentsForCity(String city) {
        return departmentRepository.getAllDepartmentsForCity(city);
    }
}

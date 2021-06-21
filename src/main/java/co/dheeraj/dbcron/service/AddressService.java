package co.dheeraj.dbcron.service;

import co.dheeraj.dbcron.model.Address;
import co.dheeraj.dbcron.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements CrudService<Address> {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Optional<Address> get(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return save(address);
    }

    @Override
    public void delete(Long id) {
        if(id == null)
            addressRepository.deleteAll();
        else
            addressRepository.deleteById(id);
    }

    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }
}

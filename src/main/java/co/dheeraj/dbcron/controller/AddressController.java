package co.dheeraj.dbcron.controller;

import co.dheeraj.dbcron.model.Address;
import co.dheeraj.dbcron.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresss")
    public ResponseEntity<List<Address>> getAllAddresss(@RequestParam(required = false) String city) {
        try {
            List<Address> addresses = new ArrayList<Address>();

            if (city == null)
                addressService.getAll().forEach(addresses::add);
            else
                addressService.findByCity(city).forEach(addresses::add);

            if (addresses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getTutorialById(@PathVariable("id") long id) {
        Optional<Address> employeeData = addressService.get(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addresss")
    public ResponseEntity<Address> createTutorial(@RequestBody Address address) {
        try {
            Address _employee = addressService
                    .save(new Address(address.getAddLine1(), address.getAddLine2(), address.getCity(), address.getPincode()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
        Optional<Address> addressData = addressService.get(id);

        if (addressData.isPresent()) {
            Address _address = addressData.get();
            _address.setAddLine1(address.getAddLine1());
            _address.setAddLine2(address.getAddLine2());
            _address.setCity(address.getCity());
            _address.setPincode(address.getPincode());
            return new ResponseEntity<>(addressService.update(_address), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
        try {
            addressService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/addresses")
    public ResponseEntity<HttpStatus> deleteAllAddresses() {
        try {
            addressService.delete(null);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

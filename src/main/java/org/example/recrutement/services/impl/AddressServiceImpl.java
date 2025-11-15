package org.example.recrutement.services.impl;

import org.example.recrutement.entities.Addresse;
import org.example.recrutement.repositories.AddressRepository;
import org.example.recrutement.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Addresse creatAddress(Addresse address) {
        return addressRepository.save(address);
    }

    @Override
    public Addresse updateAddress(Addresse address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Addresse address = addressRepository.findById(addressId).get();
        addressRepository.delete(address);
    }

    @Override
    public List<Addresse> listAddress() {
        return addressRepository.findAll();
    }

}

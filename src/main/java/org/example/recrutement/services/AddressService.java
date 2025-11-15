package org.example.recrutement.services;

import org.example.recrutement.entities.Addresse;

import java.util.List;

public interface AddressService {
    public Addresse creatAddress(Addresse address);
    public Addresse updateAddress(Addresse address);
    public void deleteAddress(Long addressId);
    public List<Addresse> listAddress();
}

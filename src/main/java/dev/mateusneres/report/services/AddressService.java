package dev.mateusneres.report.services;

import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final MapQuestService mapQuestService;


    public AddressService(AddressRepository addressRepository, MapQuestService mapQuestService) {
        this.addressRepository = addressRepository;
        this.mapQuestService = mapQuestService;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    private boolean existsLatAndLong(double latitude, double longitude) {
        return addressRepository.existsAddressByLatitudeAndLongitude(latitude, longitude);
    }

    public Address getLocationByLatAndLong(double latitude, double longitude) {
        if (!existsLatAndLong(latitude, longitude)) {
            return mapQuestService.getReverseGeoAddress(latitude, longitude);
        }

        return addressRepository.getAddressByLatitudeAndLongitude(latitude, longitude);
    }

}

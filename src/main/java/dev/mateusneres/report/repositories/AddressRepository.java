package dev.mateusneres.report.repositories;

import dev.mateusneres.report.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getAddressByLatitudeAndLongitude(double latitude, double longitude);

    boolean existsAddressByLatitudeAndLongitude(double latitude, double longitude);

}

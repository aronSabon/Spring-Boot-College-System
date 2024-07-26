package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appSoft.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {


}

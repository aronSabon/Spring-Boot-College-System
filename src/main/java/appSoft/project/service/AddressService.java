package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Address;

public interface AddressService {
		void addAddress(Address address);
		List<Address> getAllAddress();
		void deleteAddressById(int id);
		Address getAddressById(int id);
		void updateAddress(Address address);
}

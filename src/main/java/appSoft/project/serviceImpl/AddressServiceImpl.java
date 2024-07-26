package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Address;
import appSoft.project.repository.AddressRepository;
import appSoft.project.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository ar;
		@Override
		public void addAddress(Address address) {
			// TODO Auto-generated method stub
			ar.save(address);
		}

		@Override
		public List<Address> getAllAddress() {
			// TODO Auto-generated method stub
			return ar.findAll();
		}

		@Override
		public void deleteAddressById(int id) {
			// TODO Auto-generated method stub
			ar.deleteById(id);
		}

		@Override
		public Address getAddressById(int id) {
			// TODO Auto-generated method stub
			return ar.findById(id).get();
		}

		@Override
		public void updateAddress(Address address) {
			// TODO Auto-generated method stub
			ar.save(address);
		}

}

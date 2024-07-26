package appSoft.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appSoft.project.model.Transport;
import appSoft.project.repository.TransportRepository;
import appSoft.project.service.TransportService;


@Service
public class TransportServiceImpl implements TransportService {
	@Autowired
	TransportRepository tr;
		@Override
		public void addTransport(Transport transport) {
			// TODO Auto-generated method stub
			tr.save(transport);
		}

		@Override
		public List<Transport> getAllTransport() {
			// TODO Auto-generated method stub
			return tr.findAll();
		}

		@Override
		public void deleteTransportById(int id) {
			// TODO Auto-generated method stub
			tr.deleteById(id);
		}

		@Override
		public Transport getTransportById(int id) {
			// TODO Auto-generated method stub
			return tr.findById(id).get();
		}

		@Override
		public void updateTransport(Transport transport) {
			// TODO Auto-generated method stub
			tr.save(transport);
		}

}

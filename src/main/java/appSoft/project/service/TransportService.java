package appSoft.project.service;

import java.util.List;

import appSoft.project.model.Transport;

public interface TransportService {
		void addTransport(Transport transport);
		List<Transport> getAllTransport();
		void deleteTransportById(int id);
		Transport getTransportById(int id);
		void updateTransport(Transport transport);
}

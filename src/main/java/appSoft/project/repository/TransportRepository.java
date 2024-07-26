package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import appSoft.project.model.Student;
import appSoft.project.model.Teacher;
import appSoft.project.model.Transport;

public interface TransportRepository extends JpaRepository<Transport, Integer> {


}

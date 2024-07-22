package appSoft.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import appSoft.project.model.Student;
import appSoft.project.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {


}

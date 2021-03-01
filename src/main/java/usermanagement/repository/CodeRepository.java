package usermanagement.repository;

import usermanagement.model.entity.GeneratedCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CodeRepository extends JpaRepository<GeneratedCode, String>{

    GeneratedCode findByCodeAndEmail(String code, String email);


}

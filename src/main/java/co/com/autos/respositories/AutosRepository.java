package co.com.autos.respositories;

import co.com.autos.entities.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Integer> {


}

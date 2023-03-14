package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Exhibition;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition,Integer> {

}

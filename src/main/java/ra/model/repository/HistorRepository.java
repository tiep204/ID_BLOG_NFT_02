package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.History;

@Repository
public interface HistorRepository extends JpaRepository<History,Integer> {
}

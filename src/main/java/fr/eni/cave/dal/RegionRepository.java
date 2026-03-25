package fr.eni.cave.dal;

import fr.eni.cave.bo.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}

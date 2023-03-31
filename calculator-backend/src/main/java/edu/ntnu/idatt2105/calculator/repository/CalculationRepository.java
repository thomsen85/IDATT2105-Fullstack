package edu.ntnu.idatt2105.calculator.repository;

import edu.ntnu.idatt2105.calculator.model.Calculation;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long>,
    JpaSpecificationExecutor<Calculation> {
  List<Calculation> findAllByUserUsername(@NonNull String username);
}
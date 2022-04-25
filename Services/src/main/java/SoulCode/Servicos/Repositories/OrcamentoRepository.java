package SoulCode.Servicos.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import SoulCode.Servicos.Models.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento,Integer>{

	@Query(value = "SELECT * FROM orcamento WHERE status = :status", nativeQuery = true)
	List<Orcamento> findByStatus(String status);
}

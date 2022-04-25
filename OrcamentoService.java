package SoulCode.Servicos.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Servicos.Models.Orcamento;
import SoulCode.Servicos.Models.Servico;
import SoulCode.Servicos.Models.StatusOrcamento;
import SoulCode.Servicos.Repositories.OrcamentoRepository;
import SoulCode.Servicos.Repositories.ServicoRepository;

@Service
public class OrcamentoService {
	
	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	// método para trazer todos os orçamento cadastrados no db (findAll)
	public List<Orcamento> mostrarTodosOrcamentos(){
		return orcamentoRepository.findAll();
	}
	
	// Método para trazer o orçamento pelo seu id
	public Orcamento mostrarUmOrcamento (Integer idOrcamento) {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(idOrcamento);
		return orcamento.orElseThrow();
	}
	
	//Método para trazer os orçamentos de acordo com o status
	public List<Orcamento> mostrarOrcamentosPeloStatus(String status){
		return orcamentoRepository.findByStatus(status);
	}
	
	public Orcamento inserirOrcamento(Orcamento orcamento, Integer idServico) {
		orcamento.setIdOrcamento(idServico);
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		orcamentoRepository.save(orcamento);
		Servico servico = servicoRepository.getById(idServico);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
	}
	

}

package SoulCode.Servicos.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Servicos.Models.Orcamento;
import SoulCode.Servicos.Models.Servico;
import SoulCode.Servicos.Models.StatusOrcamento;
import SoulCode.Servicos.Models.StatusServico;
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
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
	}
	
	// serviço para pagamento de um orçamento (liquidar um orçamento) -
	// modificar o status do orçamento para QUITADO
	public Orcamento quitarOrcamento(Integer idOrcamento) {
		Orcamento orcamento = mostrarUmOrcamento(idOrcamento);
		orcamento.setStatus(StatusOrcamento.QUITADO);
		return orcamentoRepository.save(orcamento);
	}
	
	// serviço para deletar um orçamento
	public void excluirOrcamento(Integer idOrcamento) {
		Servico servico = servicoRepository.getById(idOrcamento);
		servico.setOrcamento(null);
		servico.setStatus(StatusServico.ARQUIVADO);
		servicoRepository.save(servico);
		orcamentoRepository.deleteById(idOrcamento);
	}
	
	// serviço para alteração dos dados de um orçamento
	public Orcamento editarOrcamento(Orcamento orcamento, Integer idOrcamento) {
		mostrarUmOrcamento(orcamento.getIdOrcamento());
		return orcamentoRepository.save(orcamento);
	}
}










package br.edu.univassouras.vasscommerce.controller;

import br.edu.univassouras.vasscommerce.dto.CidadeDTO;
import br.edu.univassouras.vasscommerce.dto.EstadoDTO;
import br.edu.univassouras.vasscommerce.repository.CidadeRepository;
import br.edu.univassouras.vasscommerce.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoRepository estadoRepo;
    private final CidadeRepository cidadeRepo;

    public EstadoController(EstadoRepository estadoRepo, CidadeRepository cidadeRepo) {
        this.estadoRepo = estadoRepo;
        this.cidadeRepo = cidadeRepo;
    }

    // GET /estado
    @GetMapping
    public List<EstadoDTO> listar() {
        return estadoRepo.findAll()
                .stream().map(EstadoDTO::from).collect(Collectors.toList());
    }

    // GET /estado/<idestado>/cidade
    @GetMapping("/{idEstado}/cidade")
    public ResponseEntity<List<CidadeDTO>> cidades(@PathVariable Integer idEstado) {
        if (!estadoRepo.existsById(idEstado)) {
            return ResponseEntity.notFound().build();
        }
        List<CidadeDTO> cidades = cidadeRepo.findByEstadoId(idEstado)
                .stream().map(CidadeDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(cidades);
    }
}

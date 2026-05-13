package com.funcionario.service.impl;

import com.funcionario.dtos.PessoaDTO;
import com.funcionario.entities.Pessoa;
import com.funcionario.mapper.PessoaMapper;
import com.funcionario.repositories.PessoaRepository;
import com.funcionario.service.PessoaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    @Transactional
    @Override
    public PessoaDTO criar(PessoaDTO pessoaDTO) {
        Pessoa p = pessoaMapper.toEntity(pessoaDTO);
        p.setNome(pessoaDTO.nome().toUpperCase().trim());
        pessoaRepository.persist(p);
        return pessoaMapper.toDTO(p);
    }

    @Transactional
    @Override
    public PessoaDTO atualizar(Long id, PessoaDTO pessoaDTO) {
        Pessoa p = pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa com ID " + id + " não encontrada"));
        p.setNome(pessoaDTO.nome().toUpperCase().trim());
        p.setCpf(pessoaDTO.cpf().trim());
        return pessoaMapper.toDTO(p);
    }

    @Override
    public PessoaDTO buscarPorId(Long id) {
        Pessoa p = pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> (new NotFoundException("Pessoa com ID " + id + " não encontrada")));
        return pessoaMapper.toDTO(p);
    }

    @Override
    public List<PessoaDTO> listarPessoas(int page, int size) {
        List<Pessoa> listPessoa = pessoaRepository
                .findAll()
                .page(page, size)
                .list();

        return listPessoa.
                stream()
                .map(pessoaMapper::toDTO)
                .toList();
    }

    @Override
    public List<PessoaDTO> buscarPessoaNome(String nome, int page, int size) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        List<Pessoa> listPessoa = pessoaRepository.buscarPorNome(nome.toUpperCase().trim(), page, size);
        if (listPessoa.isEmpty()){
            throw new NotFoundException("Pessoa com nome " + nome + " não encontrada");
        }
        return listPessoa.stream()
                .map(pessoaMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Pessoa p = pessoaRepository.findByIdOptional(id)
                .orElseThrow(() -> (new RuntimeException("Pessoa com ID " + id + " não encontrada")));
        pessoaRepository.delete(p);
    }
}
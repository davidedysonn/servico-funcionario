package com.funcionario.service.impl;

import com.funcionario.dtos.FuncionarioDTO;
import com.funcionario.entities.Funcionario;
import com.funcionario.entities.Pessoa;
import com.funcionario.mapper.FuncionarioMapper;
import com.funcionario.repositories.FuncionarioRepository;
import com.funcionario.repositories.PessoaRepository;
import com.funcionario.service.FuncionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;
    private final PessoaRepository pessoaRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper, PessoaRepository pessoaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional
    public FuncionarioDTO criar(FuncionarioDTO funcionarioDTO) {
        Pessoa pessoa = pessoaRepository.findByIdOptional(funcionarioDTO.pessoaId()).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario.setPessoa(pessoa);
        funcionarioRepository.persist(funcionario);
        return funcionarioMapper.toDTO(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioRepository.findByIdOptional(id).orElseThrow(()->new NotFoundException("Funcionario com "+ id +" não encontrado"));
        funcionario.setMatricula(funcionarioDTO.matricula());
        funcionario.setTurno(funcionarioDTO.turno());
        funcionario.setStatus(funcionarioDTO.status());
        return funcionarioMapper.toDTO(funcionario);
    }

    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findByIdOptional(id).orElseThrow(()-> new NotFoundException("Funcionario com "+ id +" não encontrado"));
        return funcionarioMapper.toDTO(funcionario);
    }

    @Override
    public List<FuncionarioDTO> listarFuncionarios() {
//         ps. aparentemente o panache tem os dois: findALl e listAll
//        List<Funcionario> funcionarios = funcionarioRepository.findAll().stream().toList();
//        return funcionarios.stream().map(funcionarioMapper::toDTO).toList();
        return funcionarioRepository.listAll()
                .stream()
                .map(funcionarioMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void removerFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findByIdOptional(id).orElseThrow(()-> new NotFoundException("Funcionario com "+ id +" não encontrado"));
        funcionarioRepository.delete(funcionario);
    }
}

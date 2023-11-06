package br.edu.iftm.tspi.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AlunoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Aluno> listarAlunos() {
        String sql = "select id, nome, email, telefone from aluno";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Aluno.class));
    }

    public Aluno buscarPorId(Long id) {
        String sql = "select id, nome, email, telefone from aluno where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Aluno.class), id);
    }

    public List<Aluno> buscarPorNome(String nome) {
        String sql = "select id, nome, email, telefone from aluno where lower(nome) like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Aluno.class), "%" + nome.toLowerCase() + "%");
    }

    @Transactional
    public void salvar(Aluno aluno) {
        String sql = "insert into aluno (nome, email, telefone) values (?, ?, ?)";
        jdbcTemplate.update(sql, aluno.getNome(), aluno.getEmail(), aluno.getTelefone());
    }

    @Transactional
    public void atualizar(Aluno aluno) {
        String sql = "update aluno set nome = ?, email = ?, telefone = ? where id = ?";
        jdbcTemplate.update(sql, aluno.getNome(), aluno.getEmail(), aluno.getTelefone(), aluno.getId());
    }

    @Transactional
    public void excluir(Long id) {
        String sql = "delete from aluno where id = ?";
        jdbcTemplate.update(sql, id);
    }
}

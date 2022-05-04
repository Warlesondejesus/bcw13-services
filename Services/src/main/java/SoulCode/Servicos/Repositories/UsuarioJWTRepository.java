package SoulCode.Servicos.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Servicos.Models.UsuarioJWT;

@Repository
public interface UsuarioJWTRepository extends JpaRepository<UsuarioJWT,Integer> {

	public Optional<UsuarioJWT> findByLogin(String login);
}

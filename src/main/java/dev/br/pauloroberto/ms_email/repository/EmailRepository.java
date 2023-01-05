package dev.br.pauloroberto.ms_email.repository;

import dev.br.pauloroberto.ms_email.model.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {
}

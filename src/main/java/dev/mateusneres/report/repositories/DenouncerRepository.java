package dev.mateusneres.report.repositories;

import dev.mateusneres.report.entities.Denouncer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenouncerRepository extends JpaRepository<Denouncer, Long> {

    Denouncer getDenouncerByCpf(String cpf);

    boolean existsDenouncerByCpf(String cpf);

}

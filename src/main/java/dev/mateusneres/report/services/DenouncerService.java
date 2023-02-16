package dev.mateusneres.report.services;

import dev.mateusneres.report.model.Denouncer;
import dev.mateusneres.report.repositories.DenouncerRepository;
import org.springframework.stereotype.Service;

@Service
public class DenouncerService {

    private final DenouncerRepository denouncerRepository;

    public DenouncerService(DenouncerRepository denouncerRepository) {
        this.denouncerRepository = denouncerRepository;
    }

    public void save(Denouncer denouncer) {
        denouncerRepository.save(denouncer);
    }

    public boolean existsDenouncer(String cpf) {
        return denouncerRepository.existsDenouncerByCpf(cpf);
    }

    public Denouncer getOrCreateDenouncer(String name, String cpf) {
        Denouncer denouncer = getDenouncerByCpf(cpf);
        if (denouncer != null) {
            return denouncer;
        }

        Denouncer createdDenouncer = new Denouncer(name, cpf);
        save(createdDenouncer);

        return createdDenouncer;
    }

    public Denouncer getDenouncerByCpf(String cpf) {
        if (existsDenouncer(cpf)) {
            return denouncerRepository.getDenouncerByCpf(cpf);
        }

        return null;
    }

}

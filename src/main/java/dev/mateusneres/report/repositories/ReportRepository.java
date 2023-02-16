package dev.mateusneres.report.repositories;

import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.entities.Denouncer;
import dev.mateusneres.report.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    boolean existsReportByTituloAndDescricaoAndDenouncerAndAddress(String title, String description, Denouncer denouncer, Address address);

}

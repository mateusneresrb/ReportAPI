package dev.mateusneres.report.repositories;

import dev.mateusneres.report.model.Address;
import dev.mateusneres.report.model.Denouncer;
import dev.mateusneres.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    boolean existsReportByTituloAndDescricaoAndDenouncerAndAddress(String title, String description, Denouncer denouncer, Address address);

}

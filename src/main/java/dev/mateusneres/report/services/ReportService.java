package dev.mateusneres.report.services;

import dev.mateusneres.report.domain.DataResponse;
import dev.mateusneres.report.domain.Location;
import dev.mateusneres.report.dtos.CreateReportDto;
import dev.mateusneres.report.dtos.sub.DenouncerDto;
import dev.mateusneres.report.dtos.sub.ReportDto;
import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.entities.Denouncer;
import dev.mateusneres.report.entities.Report;
import dev.mateusneres.report.exceptions.AddressNotFoundException;
import dev.mateusneres.report.exceptions.ReportDuplicationException;
import dev.mateusneres.report.repositories.ReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ReportService {

    private final AddressService addressService;
    private final DenouncerService denouncerService;
    private final ReportRepository reportRepository;

    public ReportService(AddressService addressService,
                         DenouncerService denouncerService, ReportRepository reportRepository) {
        this.addressService = addressService;
        this.denouncerService = denouncerService;
        this.reportRepository = reportRepository;
    }

    public Object makeReport(CreateReportDto createReportDto) {
        Address address = addressService.getLocationByLatAndLong(createReportDto.getLatitude(), createReportDto.getLongitude());

        if (address == null) {
            throw new AddressNotFoundException(2, "Endereço não encontrado para essa localidade.");
        }

        Denouncer denouncer = denouncerService.getOrCreateDenouncer(createReportDto.getDenunciante().getNome(), createReportDto.getDenunciante().getCpf());
        Report report = new Report(createReportDto.getDenuncia().getTitulo(),
                createReportDto.getDenuncia().getDescricao(),
                denouncer, address);

        addressService.save(address);
        if (existsReportCreatedByDenouncerAndAddress(report.getTitulo(), report.getDescricao(), denouncer, address)) {
            throw new ReportDuplicationException(4, "Já existe uma denuncia criada por você com as mesmas informações");
        }

        this.save(report);

        return sendReportResponse(denouncer, report, address);
    }

    private Object sendReportResponse(Denouncer denouncer, Report report, Address address) {
        DataResponse dataResponse = new DataResponse();

        DenouncerDto denouncerDto = new DenouncerDto();
        BeanUtils.copyProperties(denouncer, denouncerDto);

        ReportDto reportDto = new ReportDto();
        BeanUtils.copyProperties(report, reportDto);

        Location location = new Location();
        BeanUtils.copyProperties(address, location);

        dataResponse.setId(report.getId());
        dataResponse.setLatitude(address.getLatitude());
        dataResponse.setLongitude(address.getLongitude());
        dataResponse.setDenuncia(reportDto);
        dataResponse.setDenunciante(denouncerDto);
        dataResponse.setEndereco(location);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("data", dataResponse);

        return response;
    }

    public boolean existsReportCreatedByDenouncerAndAddress(String title, String description, Denouncer denouncer, Address address) {
        return reportRepository.existsReportByTituloAndDescricaoAndDenouncerAndAddress(title, description, denouncer, address);
    }

    public void save(Report report) {
        reportRepository.save(report);
    }


}

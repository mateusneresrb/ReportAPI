package dev.mateusneres.report.controllers;

import dev.mateusneres.report.dtos.CreateReportDto;
import dev.mateusneres.report.dtos.DenouncerCpfDto;
import dev.mateusneres.report.exceptions.BadRequestException;
import dev.mateusneres.report.services.DenouncerService;
import dev.mateusneres.report.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/v1/")

public class ReportController {

    private final ReportService reportService;
    private final DenouncerService denouncerService;

    public ReportController(ReportService reportService, DenouncerService denouncerService) {
        this.reportService = reportService;
        this.denouncerService = denouncerService;
    }

    @PostMapping(value = "/denuncias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createReport(@Valid @RequestBody() CreateReportDto createReportDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(1, "Request inválido");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reportService.makeReport(createReportDto));
    }

    @GetMapping(value = "/denuncias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReportsByCpf(@Valid @RequestBody() DenouncerCpfDto denouncerCpfDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(1, "Request inválido");
        }

        return ResponseEntity.status(HttpStatus.OK).body(denouncerService.getReportsByDenouncer(denouncerCpfDto.getCpf()));
    }

}

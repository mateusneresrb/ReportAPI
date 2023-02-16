package dev.mateusneres.report.controllers;

import dev.mateusneres.report.dtos.CreateReportDto;
import dev.mateusneres.report.exceptions.BadRequestException;
import dev.mateusneres.report.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/v1/")

public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/denuncias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createReport(@Valid @RequestBody() CreateReportDto createReportDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(1, "Request inválido");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reportService.makeReport(createReportDto));
    }

}

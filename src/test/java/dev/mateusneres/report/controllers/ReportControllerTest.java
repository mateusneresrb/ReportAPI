package dev.mateusneres.report.controllers;

import dev.mateusneres.report.dtos.CreateReportDto;
import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.entities.Denouncer;
import dev.mateusneres.report.entities.Report;
import dev.mateusneres.report.exceptions.BadRequestException;
import dev.mateusneres.report.services.DenouncerService;
import dev.mateusneres.report.services.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Mock
    ReportService reportService;
    @Mock
    DenouncerService denouncerService;
    @InjectMocks
    ReportController reportController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateReport() throws Exception {
        when(reportService.makeReport(any())).thenReturn("makeReportResponse");

        Assert.assertNull(reportController.createReport(new CreateReportDto(), null));
    }

    @Test(expected = BadRequestException.class)
    public void testGetReportsByCpf() throws Exception {
        when(denouncerService.getReportsByDenouncer(anyString())).thenReturn(Arrays.<Report>asList(new Report("titulo", "descricao", new Denouncer(0L, "name", "cpf"), new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"))));

        Assert.assertNull(reportController.getReportsByCpf("cpf"));
    }
}

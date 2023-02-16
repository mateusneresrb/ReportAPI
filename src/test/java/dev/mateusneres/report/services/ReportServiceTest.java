package dev.mateusneres.report.services;

import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.entities.Denouncer;
import dev.mateusneres.report.entities.Report;
import dev.mateusneres.report.repositories.ReportRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @Mock
    AddressService addressService;
    @Mock
    DenouncerService denouncerService;
    @Mock
    ReportRepository reportRepository;
    @InjectMocks
    ReportService reportService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMakeReport() throws Exception {
        when(addressService.getLocationByLatAndLong(anyDouble(), anyDouble())).thenReturn(new Address(0d, 0d, null, null, null, null, null, null));
        when(denouncerService.getOrCreateDenouncer(anyString(), anyString())).thenReturn(new Denouncer(0L, "name", "cpf"));
        when(reportRepository.existsReportByTituloAndDescricaoAndDenouncerAndAddress(anyString(), anyString(), any(), any())).thenReturn(true);
    }

    @Test
    public void testExistsReportCreatedByDenouncerAndAddress() throws Exception {
        when(reportRepository.existsReportByTituloAndDescricaoAndDenouncerAndAddress(anyString(), anyString(), any(), any())).thenReturn(true);

        boolean result = reportService.existsReportCreatedByDenouncerAndAddress("title", "description", new Denouncer(0L, "name", "cpf"), new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"));
        Assert.assertTrue(result);
    }

    @Test
    public void testSave() throws Exception {
        reportService.save(new Report("titulo", "descricao", new Denouncer(0L, "name", "cpf"), new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep")));
    }
}

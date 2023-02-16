package dev.mateusneres.report.services;

import dev.mateusneres.report.entities.Denouncer;
import dev.mateusneres.report.repositories.DenouncerRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class DenouncerServiceTest {

    @Mock
    DenouncerRepository denouncerRepository;
    @InjectMocks
    DenouncerService denouncerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        denouncerService.save(new Denouncer(0L, "name", "cpf"));
    }

    @Test
    public void testExistsDenouncer() throws Exception {
        when(denouncerRepository.existsDenouncerByCpf(anyString())).thenReturn(true);

        boolean result = denouncerService.existsDenouncer("cpf");
        Assert.assertTrue(result);
    }

    @Test
    public void testGetReportsByDenouncer() throws Exception {
        when(denouncerRepository.getDenouncerByCpf(anyString())).thenReturn(new Denouncer(0L, "name", "cpf"));
        when(denouncerRepository.existsDenouncerByCpf(anyString())).thenReturn(true);
    }

    @Test
    public void testGetOrCreateDenouncer() throws Exception {
        when(denouncerRepository.getDenouncerByCpf(anyString())).thenReturn(new Denouncer(0L, "name", "cpf"));
        when(denouncerRepository.existsDenouncerByCpf(anyString())).thenReturn(true);

        Denouncer result = denouncerService.getOrCreateDenouncer("name", "cpf");
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new Denouncer(0L, "name", "cpf"), result));
    }

    @Test
    public void testGetDenouncerByCpf() throws Exception {
        when(denouncerRepository.getDenouncerByCpf(anyString())).thenReturn(new Denouncer(0L, "name", "cpf"));
        when(denouncerRepository.existsDenouncerByCpf(anyString())).thenReturn(true);

        Denouncer result = denouncerService.getDenouncerByCpf("cpf");
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new Denouncer(0L, "name", "cpf"), result));
    }
}
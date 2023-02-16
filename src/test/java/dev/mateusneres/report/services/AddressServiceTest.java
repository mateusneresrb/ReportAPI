package dev.mateusneres.report.services;

import dev.mateusneres.report.entities.Address;
import dev.mateusneres.report.repositories.AddressRepository;
import dev.mateusneres.report.services.AddressService;
import dev.mateusneres.report.services.MapQuestService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@WebMvcTest(AddressService.class)
public class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;
    @Mock
    MapQuestService mapQuestService;
    @InjectMocks
    AddressService addressService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        addressService.save(new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"));
    }

    @Test
    public void testGetLocationByLatAndLong() throws Exception {
        when(addressRepository.getAddressByLatitudeAndLongitude(anyDouble(), anyDouble())).thenReturn(new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"));
        when(addressRepository.existsAddressByLatitudeAndLongitude(anyDouble(), anyDouble())).thenReturn(true);
        when(mapQuestService.getReverseGeoAddress(anyDouble(), anyDouble())).thenReturn(new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"));

        Address result = addressService.getLocationByLatAndLong(0d, 0d);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"), result));
    }
}

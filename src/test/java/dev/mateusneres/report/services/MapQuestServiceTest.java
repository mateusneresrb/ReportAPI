package dev.mateusneres.report.services;

import dev.mateusneres.report.entities.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MapQuestServiceTest {

    @InjectMocks
    MapQuestService mapQuestService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = NullPointerException.class)
    public void testGetReverseGeoAddress() throws Exception {
        Address result = mapQuestService.getReverseGeoAddress(0d, 0d);
        Assert.assertEquals(new Address(0d, 0d, "logradouro", "bairro", "cidade", "estado", "pais", "cep"), result);
    }
}

package br.com.senior.server.checkin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CheckinControllerTest {

    @Mock
    private CheckinService checkinService;

    @InjectMocks
    private CheckinController checkinController;

    @Test
    public void getService() {
        assertEquals(checkinService, checkinController.getService());
    }
}

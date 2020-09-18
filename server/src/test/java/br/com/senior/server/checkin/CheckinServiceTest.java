package br.com.senior.server.checkin;

import br.com.senior.server.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.felix.common.date.Dates.toDate;
import static com.felix.common.number.BigDecimals.ONE_HUNDRED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckinServiceTest {

@Mock
    private CheckinRepository checkinRepository;

    @Mock
    private CheckinNativeQuery checkinNativeQuery;

    @InjectMocks
    private CheckinServiceImpl checkinService;

    @Test
    void getRepository() {
        assertEquals(checkinRepository, checkinService.getRepository());
    }

    @Test
    void validPreSave() {
        startAndEndDateValidationTest();
        findActiveCheckinByPersonTest();
    }

    private void startAndEndDateValidationTest() {
        var checkin = new Checkin();
        checkin.setEntryTime(LocalDateTime.now().plusDays(1));
        checkin.setDepartureTime(LocalDateTime.now());
        assertThrows(ValidationException.class, () -> checkinService.validPreSave(checkin));
        checkin.setDepartureTime(checkin.getDepartureTime().plusDays(2));
        assertDoesNotThrow(() -> checkinService.validPreSave(checkin));
        checkin.setDepartureTime(null);
        assertDoesNotThrow(() -> checkinService.validPreSave(checkin));
    }

    private void findActiveCheckinByPersonTest() {
        when(checkinRepository.findActiveCheckinByPerson(any(), any()))
                .thenReturn(Optional.of(new Checkin()));

        assertThrows(ValidationException.class, () -> checkinService.validPreSave(new Checkin()));

        when(checkinRepository.findActiveCheckinByPerson(any(), any()))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> checkinService.validPreSave(new Checkin()));
    }

    @Test
    void preSave() {
        assertNull(checkinService.preSave(new Checkin()).getFinalValue());

        var checkin1 = new Checkin();
        checkin1.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin1.setDepartureTime(LocalDateTime.of(2020, 9, 20, 17, 0, 0));
        checkin1.setHasVehicle(true);
        assertEquals(new BigDecimal("475"), checkinService.preSave(checkin1).getFinalValue());

        var checkin2 = new Checkin();
        checkin2.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin2.setDepartureTime(LocalDateTime.of(2020, 9, 20, 17, 0, 0));
        assertEquals(new BigDecimal("420"), checkinService.preSave(checkin2).getFinalValue());

        var checkin3 = new Checkin();
        checkin3.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin3.setDepartureTime(LocalDateTime.of(2020, 9, 17, 17, 0, 0));
        assertEquals(new BigDecimal("120"), checkinService.preSave(checkin3).getFinalValue());

        var checkin4 = new Checkin();
        checkin4.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin4.setDepartureTime(LocalDateTime.of(2020, 9, 17, 10, 0, 0));
        checkin4.setHasVehicle(true);
        assertEquals(new BigDecimal("135"), checkinService.preSave(checkin4).getFinalValue());
    }

    @Test
    void filterSetsFinalValue() {
        var dto1 = new CheckinDTO();
        dto1.setVehicle(true);
        dto1.setEntry(toDate(LocalDateTime.now().minusDays(7)));

        var dto2 = new CheckinDTO();
        dto2.setEntry(toDate(LocalDateTime.now().minusDays(5)));

        var dto3 = new CheckinDTO();
        dto3.setEntry(toDate(LocalDateTime.now().minusDays(5)));
        dto3.setAmount(ONE_HUNDRED);

        var checkinDTOList = Arrays.asList(
                dto1,
                dto2,
                dto3
        );

        when(checkinNativeQuery.filter(any(), any())).thenReturn(new PageImpl<>(checkinDTOList));

        List<CheckinDTO> checkinDTOListReturned = checkinService.filter(null, null).getContent();

        assertTrue(checkinDTOList.get(0).getAmount().doubleValue() >= 845.0);
        assertTrue(checkinDTOList.get(1).getAmount().doubleValue() >= 480);
        assertEquals(ONE_HUNDRED, checkinDTOListReturned.get(2).getAmount());
    }

}

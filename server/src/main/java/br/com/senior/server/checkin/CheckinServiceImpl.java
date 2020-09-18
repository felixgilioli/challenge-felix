package br.com.senior.server.checkin;

import br.com.senior.server.crud.BaseServiceImpl;
import br.com.senior.server.validation.StartAndEndDateValidation;
import br.com.senior.server.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckinServiceImpl extends BaseServiceImpl<Checkin, Long> implements CheckinService {

    private final CheckinRepository checkinRepository;
    private final CheckinNativeQuery checkinNativeQuery;

    public CheckinServiceImpl(CheckinRepository checkinRepository,
                              CheckinNativeQuery checkinNativeQuery) {
        this.checkinRepository = checkinRepository;
        this.checkinNativeQuery = checkinNativeQuery;
    }

    @Override
    protected JpaRepository<Checkin, Long> getRepository() {
        return checkinRepository;
    }

    @Override
    protected void validPreSave(Checkin entity) {
        StartAndEndDateValidation.validate(entity.getEntryTime(), entity.getDepartureTime());
        checkinRepository.findActiveCheckinByPerson(entity.getPersonId(), entity.getEntryTime()).ifPresent(p -> {
            throw new ValidationException("selected person has active checkin.");
        });
    }

    @Override
    protected Checkin preSave(Checkin checkin) {
        CheckinFinalValueCalculator calculator = new CheckinFinalValueCalculatorDefault();
        checkin.setFinalValue(calculator.calculate(checkin));
        return checkin;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CheckinDTO> filter(CheckinFilter checkinFilter, Pageable pageable) {
        Page<CheckinDTO> checkinList = checkinNativeQuery.filter(checkinFilter, pageable);
        calculateFinalValue(checkinList.getContent());
        return checkinList;
    }

    private void calculateFinalValue(List<CheckinDTO> checkinDTOList) {
        checkinDTOList.forEach(dto -> {
            if (dto.getAmount() == null && dto.getEntry().isBefore(LocalDateTime.now())) {
                var checkin = new Checkin();
                checkin.setHasVehicle(dto.getVehicle());
                checkin.setEntryTime(dto.getEntry());
                checkin.setDepartureTime(LocalDateTime.now());
                dto.setAmount(new CheckinFinalValueCalculatorDefault().calculate(checkin));
            }
        });
    }
}

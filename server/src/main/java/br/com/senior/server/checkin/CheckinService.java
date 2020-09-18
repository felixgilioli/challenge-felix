package br.com.senior.server.checkin;

import br.com.senior.server.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckinService extends BaseService<Checkin, Long> {

    Page<CheckinDTO> filter(CheckinFilter checkinFilter, Pageable pageable);
}

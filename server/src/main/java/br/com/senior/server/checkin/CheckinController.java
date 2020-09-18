package br.com.senior.server.checkin;

import br.com.senior.server.crud.BaseController;
import br.com.senior.server.crud.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
public class CheckinController extends BaseController<Checkin, Long> {

    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    @Override
    protected BaseService<Checkin, Long> getService() {
        return checkinService;
    }

    @PostMapping("/filter")
    public Page<CheckinDTO> filter(@RequestBody CheckinFilter checkinFilter, Pageable pageable) {
        return checkinService.filter(checkinFilter, pageable);
    }
}

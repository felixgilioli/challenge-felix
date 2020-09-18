package br.com.senior.server.checkin;

import io.github.gasparbarancelli.NativeQuery;
import io.github.gasparbarancelli.NativeQueryFolder;
import io.github.gasparbarancelli.NativeQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@NativeQueryFolder("checkin")
public interface CheckinNativeQuery extends NativeQuery {

    Page<CheckinDTO> filter(@NativeQueryParam(value = "filter", addChildren = true) CheckinFilter filter, Pageable pageable);
}

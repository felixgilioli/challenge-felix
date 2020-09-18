import {Injectable} from '@angular/core';
import {BaseService} from '../shared/crud/base.service';
import {Checkin} from './checkin';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {Page} from '../shared/model/page';
import {CheckinFilter} from './checkin.filter';
import {CheckinDto} from './checkin-dto';

@Injectable()
export class CheckinService extends BaseService<Checkin, number> {

  constructor(http: HttpClient) {
    super(`${environment.urlApi}/checkin`, http);
  }

  filter(checkinFilter: CheckinFilter, page: number, size: number): Observable<Page<CheckinDto>> {
    return this.http.post<Page<CheckinDto>>(`${this.url}/filter?page=${page}&size=${size}`, checkinFilter);
  }

}

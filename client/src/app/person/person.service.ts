import {Injectable} from '@angular/core';
import {BaseService} from '../shared/crud/base.service';
import {Person} from './person';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Page} from '../shared/model/page';

@Injectable()
export class PersonService extends BaseService<Person, number> {

  constructor(http: HttpClient) {
    super(`${environment.urlApi}/person`, http);
  }

  filter(page: number, size: number): Observable<Page<Person>> {
    return this.http.get<Page<Person>>(`${this.url}/filter?page=${page}&size=${size}`);
  }

  complete(query: string): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.url}/complete?q=${query}`);
  }
}

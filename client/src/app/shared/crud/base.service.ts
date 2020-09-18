import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export abstract class BaseService<T, ID> {

  protected constructor(protected url: string, protected http: HttpClient) {
  }

  findById(id: ID): Observable<T> {
    return this.http.get<T>(`${this.url}/${id}`);
  }

  save(entity: T): Observable<T> {
    return this.http.post<T>(this.url, entity);
  }

  delete(id: ID): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}

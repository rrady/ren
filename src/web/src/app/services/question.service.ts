import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { Question, Page } from '@app/models';

@Injectable({ providedIn: 'root' })
export class QuestionService {
  constructor(private http: HttpClient) { }

  query(pageIndex: number, pageSize: number): Observable<Page<Question>>  {
    const pageParams = new HttpParams({
      fromObject: {
        page: pageIndex.toString(),
        size: pageSize.toString()
      }
    });

   return this.http.get<Page<Question>>(`${environment.renApi}/api/question`, { params: pageParams });
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { Router } from '@angular/router';
import { Question } from '@app/models/question.model';
import { Page } from '@app/models/page.model';

@Injectable({ providedIn: 'root' })
export class QuestionService {
    constructor(private http: HttpClient, private router: Router) { }

    query(pageIndex: number, pageSize: number): Observable<HttpResponse<Page<Question>>> {
      const pageParams = new HttpParams({
        fromObject: {
          page: pageIndex.toString(),
          size: pageSize.toString()
        }
      });

      return this.http.get<Page<Question>>(`${environment.renApi}/api/question`, { params: pageParams, observe: 'response' });
    }
}

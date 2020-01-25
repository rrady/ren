import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { Question, Page, Tag } from '@app/models';

@Injectable({ providedIn: 'root' })
export class QuestionService {
  constructor(private http: HttpClient) { }

  query(pageIndex: number, pageSize: number, searchKey?: string): Observable<Page<Question>>  {
    const pageParams = new HttpParams({
      fromObject: {
        page: pageIndex.toString(),
        size: pageSize.toString(),
        searchKey: searchKey ? searchKey : ""
      }
    });

   return this.http.get<Page<Question>>(`${environment.renApi}/api/question`, { params: pageParams });
  }

  postQuestion(userId: number, title: string, text: string, tags: Tag[]): Observable<any> {
    return this.http.post<any>(`${environment.renApi}/api/question`, { userId, title, text, tags });
  }

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${environment.renApi}/api/tag`);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { Question, Page, Tag } from '@app/models';

@Injectable({ providedIn: 'root' })
export class QuestionService {
  constructor(private http: HttpClient) { }

  query(pageIndex: number, pageSize: number, searchKey?: string): Observable<Page<Question>>  {
    let pageParams = new HttpParams({
      fromObject: {
        page: pageIndex.toString(),
        size: pageSize.toString()
      }
    });

    if (searchKey) {
      pageParams = pageParams.append('searchKey', searchKey);
    }

   return this.http.get<Page<Question>>(`${environment.renApi}/api/question`, { params: pageParams });
  }

  getQuestion(id: number): Observable<Question> {
    return this.http.get<Question>(`${environment.renApi}/api/question/${id}`);
  }

  postQuestion(userId: number, title: string, text: string, tags: Tag[]): Observable<any> {
    return this.http.post<any>(`${environment.renApi}/api/question`, { userId, title, text, tags });
  }

  postAnswer(userId: number, questionId: number, text: string): Observable<any> {
    return this.http.post<any>(`${environment.renApi}/api/question/${questionId}/answer`,
      { userId, questionId, text });
  }

  postComment(creatorId: number, questionId: number, answerId: number, text: string): Observable<any> {
    return this.http.post<any>(`${environment.renApi}/api/question/${questionId}/answer/${answerId}/comment`,
      { creatorId, questionId, answerId, text });
  }

  updateQuestion(userId: number, questionId: number, title:string, text: string, viewCount: number): Observable<any> {
    return this.http.put<any>(`${environment.renApi}/api/question/${questionId}`, { userId, title, text, viewCount });
  }

  updateAnswer(userId: number, questionId: number, answerId: number, text: string, rating: number): Observable<any> {
    return this.http.put<any>(`${environment.renApi}/api/question/${questionId}/answer/${answerId}`,
      { userId, questionId, text, rating });
  }

  updateComment(creatorId: number, questionId: number, answerId: number, commentId: number, text: string): Observable<any> {
    return this.http.put<any>(`${environment.renApi}/api/question/${questionId}/answer/${answerId}/comment/${commentId}`,
      { creatorId, questionId, answerId, text });
  }

  deleteQuestion(questionId: number): Observable<any> {
    return this.http.delete<any>(`${environment.renApi}/api/question/${questionId}`);
  }

  deleteAnswer(questionId: number, answerId: number): Observable<any> {
    return this.http.delete<any>(`${environment.renApi}/api/question/${questionId}/answer/${answerId}`);
  }

  deleteComment(questionId: number, answerId: number, commentId: number): Observable<any> {
    return this.http.delete<any>(`${environment.renApi}/api/question/${questionId}/answer/${answerId}/comment/${commentId}`);
  }

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${environment.renApi}/api/tag`);
  }
}

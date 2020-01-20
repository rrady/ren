import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Question } from '@app/models/question.model';

@Component({
  selector: 'app-paginated-grid',
  templateUrl: './paginated-grid.component.html',
  styleUrls: ['./paginated-grid.component.css']
})
export class PaginatedGridComponent implements OnInit {
  @Input() pageSize: number;
  @Input() items: Question[] = [];
  @Output() prev: EventEmitter<any> = new EventEmitter<any>();
  @Output() page: EventEmitter<any> = new EventEmitter<any>();
  @Output() next: EventEmitter<any> = new EventEmitter<any>();

  currentPage: number = 3;
  totalPages: number = 80;

  constructor() {
  }

  ngOnInit(): void {
  }
}

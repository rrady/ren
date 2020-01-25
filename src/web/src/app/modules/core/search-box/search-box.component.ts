import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent implements OnInit {
  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  search(event: KeyboardEvent): void {
    var searchTerm: string = (event.target as HTMLInputElement).value;
    this.router.navigate(['/feed'], { queryParams: { term: searchTerm } });
  }
}

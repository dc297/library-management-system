import { Component, OnInit, ChangeDetectionStrategy, ViewEncapsulation } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { BookSearchResult } from '@app/models/book-search-result';
import { NotificationService } from '@app/core';
import { Router } from '@angular/router';

@Component({
  selector: 'lms-search-books',
  templateUrl: './search-books.component.html',
  styleUrls: ['./search-books.component.css']
})
export class SearchBooksComponent implements OnInit {

  searchQuery = '';
  rows: BookSearchResult[] = [];
  loadingIndicator = true;
  displayedColumns = ['title', 'isbn', 'authorName', 'actions'];
  constructor(
    private http: HttpClient, 
    private notificationService: NotificationService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onSearch(){
    this.loadingIndicator = true;
    if(this.searchQuery.length>0){
      this.http.get<BookSearchResult[]>(`${environment.apiBaseUrl}${environment.bookSearchEndpoint}/${this.searchQuery}`).subscribe(
        data=>
        {
          this.loadingIndicator = false;
          this.rows = data;
        },
        error =>
        {
          console.log(error);
        }
      )
    }
  }

  onQueryChange(searchQuery: string){
    this.searchQuery = searchQuery;
  }

  checkOut(bookId: number, bookLoanId: number){
    if(bookLoanId==null){
      this.router.navigate(['/check-out/' + bookId]);
    }
    else{
      this.notificationService.error("Book already checked out");
    }
  }

}
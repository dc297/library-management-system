import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from '@app/models/book';
import { environment } from '@env/environment';
import { HttpClient } from '@angular/common/http';
import { BorrowerSearchResult } from '@app/models/borrower-search-result';
import { BookLoan } from '@app/models/book-loan';
import { NotificationService } from '@app/core';

@Component({
  selector: 'lms-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit {

  book: Book = new Book();
  bookId: number = 0;
  searchQuery = '';
  rows: BorrowerSearchResult[] = [];
  loadingIndicator = true;
  displayedColumns = ['borrowerid', 'name', 'address', 'ssn', 'action'];

  constructor(
    private routhe: ActivatedRoute,
    private http: HttpClient,
    private notificationService: NotificationService
    ) { }

  ngOnInit() {
    this.routhe.params.subscribe(params => {
      console.log(params.id);
      this.bookId = parseInt(params.id);
      if(isNaN(this.bookId)){
        this.notificationService.error("Invalid book selected");
        return;
      } 
      this.fetchBook();
    });
  }

  fetchBook(){
    this.http.get<Book>(`${environment.apiBaseUrl}${environment.booksEndpoint}/${this.bookId}`).subscribe(data=>{
      this.book = data;
    });
  }

  onSearch(){
    this.loadingIndicator = true;
    if(this.searchQuery.length>0){
      this.http.get<BorrowerSearchResult[]>(`${environment.apiBaseUrl}${environment.borrowersSearchEndpoint}/${this.searchQuery}`)
      .subscribe(data => 
        {
          this.rows = data;
          this.loadingIndicator = false;
        })
    }
  }

  onQueryChange(searchQuery: string){
    this.searchQuery = searchQuery;
  }

  checkOut(borrowerId: number){
    this.http.post<any>(`${environment.apiBaseUrl}${environment.bookLoanEndpoint}`, new BookLoan(borrowerId, this.bookId))
    .subscribe(data=>
      {
        this.notificationService.success("Successfully checked out book");
      },
      error =>
      {
        console.log(error);
        this.notificationService.error(error.error.message);
      }
    )
  }

}

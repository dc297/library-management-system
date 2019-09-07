import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { BookLoanSearchRequest } from '@app/models/book-loan-search-request';
import { BookLoanSearchResult } from '@app/models/book-loan-search-result';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { formatDate } from '@angular/common';
import { BookLoan } from '@app/models/book-loan';
import { NotificationService } from '@app/core';

@Component({
  selector: 'lms-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css']
})
export class CheckInComponent implements OnInit {

  bookLoanSearchRequest: BookLoanSearchRequest = new BookLoanSearchRequest();
  loadingIndicator = true;
  rows: BookLoanSearchResult[] = [];
  displayedColumns = ['bookName','borrowerId', 'borrowerName','dateOut','dueDate','action'];
  constructor(private http: HttpClient, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  search(){
    this.loadingIndicator = true;
    this.http.get<BookLoanSearchResult[]>(`${environment.apiBaseUrl}${environment.bookLoanSearchEndpoint}`, {params: this.bookLoanSearchRequest.getHttpParams()})
    .subscribe(data =>
      {
        this.rows = data;
        this.loadingIndicator = false;
      })
  }

  checkIn(rowId: number){
    var bookLoanSearchResult = this.rows[rowId];
    var bookLoan = new BookLoan(
      bookLoanSearchResult.borrowerId,
      bookLoanSearchResult.bookId,
      bookLoanSearchResult.id,
      bookLoanSearchResult.dateOut,
      bookLoanSearchResult.dueDate);

    this.http.put<any>(`${environment.apiBaseUrl}${environment.bookLoanEndpoint}/${bookLoanSearchResult.id}`, bookLoan)
    .subscribe(
      data =>
      {
        this.notificationService.success("Book successfully checked in");
        this.search();
      },
      error => 
      {
        this.notificationService.error("Error while checking in book");
      }
    )
  }

  toDate(input){
    return formatDate(input, 'mediumDate', 'en-US');
  }

}

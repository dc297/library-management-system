import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FineSearchResult } from '@app/models/fine-search-result';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { NotificationService } from '@app/core';

@Component({
  selector: 'lms-fines',
  templateUrl: './fines.component.html',
  styleUrls: ['./fines.component.css']
})
export class FinesComponent implements OnInit {

  rows: FineSearchResult[] = [];
  loadingIndicator = true;
  displayedColumns = ['borrowerId', 'borrowerName', 'paidAmount', 'unpaidAmount', 'estimatedAmount', 'action'];

  constructor(private http: HttpClient, private notificationService: NotificationService) { }

  ngOnInit() {
    this.loadingIndicator = true;
    this.fetchFines()
  }

  fetchFines(){
    this.http.get<FineSearchResult[]>(`${environment.apiBaseUrl}${environment.fineSearchEndpoint}`)
    .subscribe(
      data => 
      {
        this.rows = data;
        this.loadingIndicator = false;
      }
    );
  }

  calculateFines(){
    this.http.post<any>(`${environment.apiBaseUrl}${environment.fineEndpoint}`, undefined)
    .subscribe(
      data => {
        this.notificationService.success("Successfully caluclated fines.");
        this.fetchFines();
      },
      error => {
        this.notificationService.error("Failed to calculate fines");
      }
    )
  }

  payFine(borrowerId: number){
    this.http.put<any>(`${environment.apiBaseUrl}${environment.fineEndpoint}/${borrowerId}`, undefined)
    .subscribe(
      data=>{
        this.notificationService.success("Successfully paid fine");
        this.fetchFines();
      },
      error => {
        this.notificationService.error("Failed to pay fine");
      }
    )
  }

}

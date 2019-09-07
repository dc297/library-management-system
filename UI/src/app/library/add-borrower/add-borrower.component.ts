import { Component, OnInit } from '@angular/core';
import { Borrower } from '@app/models/borrower';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { NotificationService } from '@app/core';

@Component({
  selector: 'lms-add-borrower',
  templateUrl: './add-borrower.component.html',
  styleUrls: ['./add-borrower.component.css']
})
export class AddBorrowerComponent implements OnInit {

  borrower: Borrower = new Borrower();
  constructor(private http: HttpClient, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  addBorrower(){
    this.http.post<Borrower>(`${environment.apiBaseUrl}${environment.borrowersEndpoint}`, this.borrower)
    .subscribe(
      data=>
      {
        this.notificationService.success("Successfully added borrower with card number " + data.id);
      },
      error => 
      {
        this.notificationService.error(error.error.message);
      }
    )
  }

}

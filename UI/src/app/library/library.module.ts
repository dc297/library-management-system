import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchBooksComponent } from './search-books/search-books.component';
import { SharedModule } from '@app/shared';
import { MatTableModule, MatPaginatorModule, MatTooltipModule } from '@angular/material';
import { CoreModule } from '@app/core';
import { CheckOutComponent } from './check-out/check-out.component';
import { CheckInComponent } from './check-in/check-in.component';
import { AddBorrowerComponent } from './add-borrower/add-borrower.component';
import { FormsModule } from '@angular/forms';
import { FinesComponent } from './fines/fines.component';

@NgModule({
  declarations: [SearchBooksComponent, CheckOutComponent, CheckInComponent, AddBorrowerComponent, FinesComponent],
  imports: [
    CommonModule,
    SharedModule,
    CoreModule,
    MatTableModule,
    MatPaginatorModule,
    MatTooltipModule,
    FormsModule
  ]
})
export class LibraryModule { }

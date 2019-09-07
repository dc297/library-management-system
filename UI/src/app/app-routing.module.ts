import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchBooksComponent } from './library/search-books/search-books.component';
import { CheckOutComponent } from './library/check-out/check-out.component';
import { CheckInComponent } from './library/check-in/check-in.component';
import { AddBorrowerComponent } from './library/add-borrower/add-borrower.component';
import { FinesComponent } from './library/fines/fines.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'search-books',
    pathMatch: 'full'
  },
  {
    path: 'search-books',
    component: SearchBooksComponent
  },
  {
    path: 'check-out/:id',
    component: CheckOutComponent
  },
  {
    path: 'check-in',
    component: CheckInComponent
  },
  {
    path: 'add-borrower',
    component: AddBorrowerComponent
  },
  {
    path: 'fines',
    component: FinesComponent
  },
  {
    path: '**',
    redirectTo: 'search-books'
  }
];

@NgModule({
  // useHash supports github.io demo page, remove in your app
  imports: [
    RouterModule.forRoot(routes, {
      useHash: true,
      scrollPositionRestoration: 'enabled'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}

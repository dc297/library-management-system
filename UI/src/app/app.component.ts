import browser from 'browser-detect';
import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';

import {
  routeAnimations,
  AppState,
  LocalStorageService,
  selectIsAuthenticated
} from '@app/core';
import { environment as env } from '@env/environment';
import { OverlayContainer } from '@angular/cdk/overlay';

@Component({
  selector: 'lms-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [routeAnimations]
})
export class AppComponent implements OnInit {
  isProd = env.production;
  envName = env.envName;
  version = env.versions.app;
  year = new Date().getFullYear();
  logo = require('../assets/logo.png');
  languages = ['en'];
  navigation = [
    { link: 'search-books', label: 'Search Books' },
    { link: 'check-in', label: 'Checkin Books' },
    { link: 'add-borrower', label: 'Add Borrower' },
    { link: 'fines', label: 'Fines' }
  ];
  navigationSideMenu = [
    ...this.navigation
  ];

  isAuthenticated$: Observable<boolean>;
  stickyHeader$: Observable<boolean>;
  language$: Observable<string>;
  //theme$: Observable<string>;

  constructor(
    private store: Store<AppState>,
    private storageService: LocalStorageService,
    private overlayContainer: OverlayContainer
  ) {}

  private static isIEorEdgeOrSafari() {
    return ['ie', 'edge', 'safari'].includes(browser().name);
  }

  ngOnInit(): void {
    this.storageService.testLocalStorage();

    this.isAuthenticated$ = this.store.pipe(select(selectIsAuthenticated));
    const classList = this.overlayContainer.getContainerElement().classList;
    classList.add("default-theme");
  }

}

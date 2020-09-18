import {NgModule} from '@angular/core';
import {NavComponent} from './nav.component';
import {BrowserModule} from '@angular/platform-browser';
import {ButtonModule} from 'primeng/button';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    NavComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    ButtonModule,
  ],
  exports: [
    NavComponent,
  ],
})
export class NavModule {
}

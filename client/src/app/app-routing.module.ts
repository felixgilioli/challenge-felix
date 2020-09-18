import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CheckinComponent} from './checkin/checkin.component';
import {PersonComponent} from './person/person.component';

const routes: Routes = [
  { path: '', redirectTo: '/checkin', pathMatch: 'full' },
  { path: 'checkin', component: CheckinComponent },
  { path: 'person', component: PersonComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

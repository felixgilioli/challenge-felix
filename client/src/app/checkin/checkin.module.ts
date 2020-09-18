import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CheckinComponent} from './checkin.component';
import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {CalendarModule} from 'primeng/calendar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {CheckinService} from './checkin.service';
import {CheckboxModule} from 'primeng/checkbox';
import {TableModule} from 'primeng/table';
import {InputTextModule} from 'primeng/inputtext';
import {InputMaskModule} from 'primeng/inputmask';
import {RadioButtonModule} from 'primeng/radiobutton';
import {CurrencyFormatPipeModule} from '../shared/currency/currency-format.pipe.module';

@NgModule({
  declarations: [CheckinComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    ButtonModule,
    CardModule,
    CalendarModule,
    AutoCompleteModule,
    CheckboxModule,
    TableModule,
    InputTextModule,
    InputMaskModule,
    RadioButtonModule,
    CurrencyFormatPipeModule,
  ],
  providers: [
    CheckinService,
  ]
})
export class CheckinModule { }

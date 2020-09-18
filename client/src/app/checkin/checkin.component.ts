import {Component, OnInit} from '@angular/core';
import {Checkin} from './checkin';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Person} from '../person/person';
import {CheckinService} from './checkin.service';
import {PersonService} from '../person/person.service';
import {LazyLoadEvent, MessageService} from 'primeng/api';
import {CheckinFilter} from './checkin.filter';
import {CheckinDto} from './checkin-dto';

@Component({
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.scss']
})
export class CheckinComponent implements OnInit {

  formCheckin: FormGroup;
  checkin = new Checkin();
  checkinFilter = new CheckinFilter();
  checkinList: CheckinDto[] = [];
  totalRecords = 0;
  personCompleteList: Person[] = [];

  constructor(private checkinService: CheckinService,
              private personService: PersonService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.formCheckin = new FormGroup({
      entryTime: new FormControl(this.checkin.entryTime, [
        Validators.required,
      ]),
      departureTime: new FormControl(this.checkin.departureTime, []),
      person: new FormControl(this.checkin.person, [
        Validators.required,
      ]),
      hasVehicle: new FormControl(this.checkin.hasVehicle, []),
    });
  }

  completePerson(event): void {
    this.personService.complete(event.query)
      .subscribe(res => this.personCompleteList = res);
  }

  saveCheckin(): void {
    this.checkinService.save(this.checkin)
      .subscribe(checkin => {
        this.messageService.add({severity: 'success', detail: 'Checkin salvo com sucesso.'});
        this.formCheckin.reset();
        this.refreshTable();
      }, error => {
        this.messageService.add({severity: 'error', detail: 'Não foi possível salvar, tente novamente.'});
        console.log(error);
      });
  }

  public refreshTable(): void {
    this.loadData({first: 0, rows: 5});
  }

  loadData(event: LazyLoadEvent): void {
    this.checkinService.filter(this.checkinFilter, event.first / event.rows, event.rows)
      .subscribe(page => {
        this.checkinList = page.content;
        this.totalRecords = page.totalElements;
      });
  }

  editCheckin(checkinDto: CheckinDto): void {
    this.checkinService.findById(checkinDto.id)
      .subscribe(checkin => this.checkin = checkin);
  }
}

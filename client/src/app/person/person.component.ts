import {Component, OnInit} from '@angular/core';
import {Person} from './person';
import {ConfirmationService, LazyLoadEvent, MessageService} from 'primeng/api';
import {PersonService} from './person.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit {

  formPerson: FormGroup;
  person = new Person();
  totalRecords = 0;
  personList: Person[] = [];

  constructor(private messageService: MessageService,
              private confirmationService: ConfirmationService,
              private personService: PersonService) { }

  ngOnInit(): void {
    this.formPerson = new FormGroup({
      fullName: new FormControl(this.person.fullName, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(100),
      ]),
      document: new FormControl(this.person.document, [
        Validators.required,
        Validators.maxLength(20),
      ]),
      phoneNumber: new FormControl(this.person.phoneNumber, [
        Validators.required,
        Validators.maxLength(20),
      ]),
    });
  }

  savePerson(): void {
    this.personService.save(this.person)
      .subscribe(person => {
        this.messageService.add({severity: 'success', detail: 'Hóspede salvo com sucesso.'});
        this.formPerson.reset();
        this.refreshTable();
      }, error => {
        this.messageService.add({severity: 'error', detail: 'Não foi possível salvar, tente novamente.'});
        console.log(error);
      });
  }

  private refreshTable(): void {
    this.loadData({first: 0, rows: 5});
  }

  loadData(event: LazyLoadEvent): void {
    this.personService.filter(event.first / event.rows, event.rows)
      .subscribe(page => {
        this.personList = page.content;
        this.totalRecords = page.totalElements;
      });
  }


  editPerson(person: Person): void {
    this.person = JSON.parse(JSON.stringify(person));
  }

  deletePerson(person: Person): void {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este registro?',
      accept: () => {
        this.personService.delete(person.id)
          .subscribe(() => {
            this.messageService.add({severity: 'success', detail: 'Hóspede excluído com sucesso.'});
            this.refreshTable();
          });
      }
    });
  }
}

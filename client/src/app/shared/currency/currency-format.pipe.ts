import {Inject, LOCALE_ID, Pipe, PipeTransform} from '@angular/core';
import {CurrencyPipe, registerLocaleData} from '@angular/common';
import pt from '@angular/common/locales/pt';

@Pipe({
  name: 'currencyFormat',
})
export class CurrencyFormatPipe implements PipeTransform {

  constructor(@Inject(LOCALE_ID) public locale: string) {}

  transform(value: number,
            locale: string = 'BRL',
            currencySymbol: 'symbol',
            numberFormat: string = '1.2-2'): string {
    if (value != null) {
      registerLocaleData(pt, 'pt-BR');
      const currencyPipe = new CurrencyPipe('pt-BR');
      let newValue: string;

      newValue = currencyPipe.transform(value, locale, currencySymbol, numberFormat);

      return newValue;
    }
  }

}

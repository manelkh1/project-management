import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bank } from '../models/bank';
import  {environment } from '../../environments/environment'

const AUTH_API = environment.baseURL;

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpClient: HttpClient) { }


  getBankById(bankId: number): Observable<Bank[]> {
    return this.httpClient.get<Bank[]>( AUTH_API + '/api/projects/banks/' + bankId);
  }

  createBank(bank: Bank): Observable<any> {
    return this.httpClient.post(AUTH_API + '/api/projects/banks/' , bank);
  }

  updateBank(bankId: number,bank:Bank): Observable<any> {
    return this.httpClient.put(AUTH_API + '/api/projects/banks/' + bankId, bank);
  }

  deleteBank(bankId: number): Observable<any> {
    return this.httpClient.delete(AUTH_API + '/api/projects/banks/' + bankId);
  }
 
}

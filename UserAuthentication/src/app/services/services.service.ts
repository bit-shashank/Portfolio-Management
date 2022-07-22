import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {


  private base_url = 'http://localhost:6500'
  
  constructor(private httpClient: HttpClient) { }

  // getUserById(id:number): Observable<User> {
  //   return this.httpClient.get<User>(`${this.base_url}/${id}`)
  // }

  Authenticate(User: User): Observable<Object>{
    return this.httpClient.post(`${this.base_url}/authenticate`, User)
  }

  CalculateNetWorth(token: String, id: number): Observable<Object>{
    var headers_object = new HttpHeaders().set("Authorization", "Bearer " + token);
    return this.httpClient.get(`${this.base_url}/calculateNetworth/${id}` , { 'headers': headers_object })
  }

  AllStocks(token: String, id: number): Observable<Object>{
    var headers_object = new HttpHeaders().set("Authorization", "Bearer " + token);
    return this.httpClient.get(`${this.base_url}/stocks/${id}` , { 'headers': headers_object })
  }

  AllMutualFunds(token: String, id: number): Observable<Object>{
    var headers_object = new HttpHeaders().set("Authorization", "Bearer " + token);
    return this.httpClient.get(`${this.base_url}/mutualfunds/${id}` , { 'headers': headers_object })
  }

  // Login(login: Login): Observable<Object>{
  //   const payload = new HttpParams();
  //   payload.set('userName', login.userName);
  //   payload.set('password', login.password);
  //   return this.httpClient.post(`${this.base_url}/auth/login`, payload)
  // }

  // updateUser(id: number, User: User): Observable<Object>{
  //   return this.httpClient.put(`${this.base_url}/${id}`,User)
  // }

  // deleteUser(id: number): Observable<Object>{
  //   return this.httpClient.delete(`${this.base_url}/${id}`)
  // }

}

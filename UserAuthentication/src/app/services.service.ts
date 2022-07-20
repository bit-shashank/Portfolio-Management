import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.model';
import { Login } from './Login.model';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {


  private base_url = 'http://localhost:9092'
  
  constructor(private httpClient: HttpClient) { }

  // getUserById(id:number): Observable<User> {
  //   return this.httpClient.get<User>(`${this.base_url}/${id}`)
  // }

  addUser(User: User): Observable<Object>{
    return this.httpClient.post(`${this.base_url}/auth/login/save`, User)
  }

  Login(login: Login): Observable<Object>{
    const payload = new HttpParams();
    payload.set('userName', login.userName);
    payload.set('password', login.password);
    return this.httpClient.post(`${this.base_url}/auth/login`, payload)
  }

  // updateUser(id: number, User: User): Observable<Object>{
  //   return this.httpClient.put(`${this.base_url}/${id}`,User)
  // }

  // deleteUser(id: number): Observable<Object>{
  //   return this.httpClient.delete(`${this.base_url}/${id}`)
  // }

}

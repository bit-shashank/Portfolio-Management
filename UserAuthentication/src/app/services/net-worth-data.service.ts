import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NetWorthDataService {

  constructor() { }

  token: string
  data: any
  id: number

  setNetData(data: any){
    this.data = data
  }

  getNetData(){
    return this.data
  }

  getToken(){
    return this.token
  }

  setToken(token: string){
    this.token = token
  }

  getId(){
    return this.id
  }

  setId(id: number){
    this.id = id
  }

}

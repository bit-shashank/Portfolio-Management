import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';

export interface Stocks {
  Stockname: string;
  Price: number;
}

export interface MutualFund {
  Fund: string;
  Price: number;
}

const ELEMENT_DATAS: MutualFund[] = [
  {Fund: 'Hydrogen', Price: 123},
  {Fund: 'Helium', Price: 456},
  {Fund: 'Lithium', Price: 789},
  {Fund: 'Beryllium', Price: 132},
  {Fund: 'Boron', Price: 231},
  {Fund: 'Carbon', Price: 453},
  {Fund: 'Nitrogen', Price: 435},
  {Fund: 'Oxygen', Price: 567},
  {Fund: 'Fluorine', Price: 765},
  {Fund: 'Neon', Price: 876},
];

const ELEMENT_DATA: Stocks[] = [
  {Stockname: 'Hydrogen', Price: 123},
  {Stockname: 'Helium', Price: 456},
  {Stockname: 'Lithium', Price: 789},
  {Stockname: 'Beryllium', Price: 132},
  {Stockname: 'Boron', Price: 231},
  {Stockname: 'Carbon', Price: 453},
  {Stockname: 'Nitrogen', Price: 435},
  {Stockname: 'Oxygen', Price: 567},
  {Stockname: 'Fluorine', Price: 765},
  {Stockname: 'Neon', Price: 876},
];

const USER_DATAS: MutualFund[] = [
  {Fund: 'Carbon', Price: 453},
  {Fund: 'Nitrogen', Price: 435},
  {Fund: 'Oxygen', Price: 567},
  {Fund: 'Fluorine', Price: 765},
  {Fund: 'Neon', Price: 876},
];

const USER_DATA: Stocks[] = [
  {Stockname: 'Carbon', Price: 453},
  {Stockname: 'Nitrogen', Price: 435},
  {Stockname: 'Oxygen', Price: 567},
  {Stockname: 'Fluorine', Price: 765},
  {Stockname: 'Neon', Price: 876},
];

@Component({
  selector: 'app-dashboard-body',
  templateUrl: './dashboard-body.component.html',
  styleUrls: ['./dashboard-body.component.css']
})
export class DashboardBodyComponent implements OnInit {

  Form: any

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {   
    this.initForm()
  }

  initForm() {
    this.Form = this.formBuilder.group({
      count: new FormControl({value: 1, disabled: true})
    });
  }

  displayedColumns1: string[] = ['Stockname', 'Price'];
  dataSource1 = ELEMENT_DATA;

  displayedColumns2: string[] = ['Fund', 'Price'];
  dataSource2 = ELEMENT_DATAS;

  displayedColumns3: string[] = ['Stockname', 'Price', 'Counter'];
  dataSource3 = USER_DATA;

  displayedColumns4: string[] = ['Fund', 'Price', 'Counter'];
  dataSource4 = USER_DATAS;

  minus(){
    if(this.Form.get('count').value == 1){
      this.Form.get('count').setValue(1)
    }else{
    this.Form.get('count').setValue(this.Form.get('count').value - 1)
    }
  }


  plus(){
    this.Form.get('count').setValue(this.Form.get('count').value + 1)
  }

}

import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { DialogData } from '../dashboard-body/dashboard-body.component';
import { MutualFund } from '../models/MutualFund.model';
import { Stocks } from '../models/Stocks.models';

@Component({
  selector: 'app-sell-dialog',
  templateUrl: './sell-dialog.component.html',
  styleUrls: ['./sell-dialog.component.css']
})
export class SellDialogComponent implements OnInit{
  NetWorthData: any
  displayedColumns: string[] = ['Share', 'Price', 'Counter']
  dataSource: any
  dataSource4: any
  SellForm: any
  asset: any
  type: boolean

  constructor(
    public dialogRef: MatDialogRef<SellDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private formBuilder: FormBuilder,
  ) {}
  ngOnInit(): void {
    console.log(this.data)
    this.NetWorthData = JSON.parse(localStorage.getItem('NetWorthData'))
    if(this.data['name'] === 'Stocks'){
      this.type = true
      this.dataSource = new MatTableDataSource<Stocks>(this.data['asset'])
    }else{
      this.type = false
      this.dataSource = new MatTableDataSource<MutualFund>(this.data['asset'])
    }
    this.asset = this.data['asset'] 
    this.initSellForm()
    this.asset.forEach((moveMaker ) => this.SellForm .addControl(moveMaker.id, new FormControl()));
  }

  initSellForm() {
    this.SellForm = this.formBuilder.group({
      delete: []
    });
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}

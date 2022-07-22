import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MutualFund } from '../models/MutualFund.model';
import { Stocks } from '../models/Stocks.models';
import { NetWorthDataService } from '../services/net-worth-data.service';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-dashboard-body',
  templateUrl: './dashboard-body.component.html',
  styleUrls: ['./dashboard-body.component.css']
})
export class DashboardBodyComponent implements OnInit {

  /**
   * Declaration and initialization section
   */
  Form: any
  NetWorthData: any
  displayedColumns4: string[] = ['Fund', 'Price', 'Counter'];
  dataSource4: any
  displayedColumns1: string[] = ['Stockname', 'Price'];
  dataSource1: any
  show: boolean = false
  displayedColumns2: string[] = ['Fund', 'Price'];
  dataSource2: any;
  displayedColumns3: string[] = ['Stockname', 'Price', 'Counter'];
  dataSource3: any
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private netWorthDataservice: NetWorthDataService, private httpservice: ServicesService) { }

  ngOnInit(): void {
    /**
     * This is for getting the networth and user details
     */
    this.NetWorthData = this.netWorthDataservice.getNetData()
    
    /**
     * Calling All Stocks service
     */
    this.httpservice.AllStocks(this.netWorthDataservice.getToken(), this.netWorthDataservice.getId()).subscribe(data => {
      this.dataSource1 = data
    })

    /**
     * Calling All MutualFund service
     */
    this.httpservice.AllMutualFunds(this.netWorthDataservice.getToken(), this.netWorthDataservice.getId()).subscribe(data => {
      this.dataSource2 = data
    })

    /**
     * Creating dataSource for table 3 and 4
     */
    this.dataSource3 = new MatTableDataSource<Stocks>(this.NetWorthData.stockDetails);
    this.dataSource4 = new MatTableDataSource<Stocks>(this.NetWorthData.mutualFundDetails);
    // this.dataSource3 = this.NetWorthData.stockDetails;
    // this.dataSource4 = this.NetWorthData.mutualFundDetails;
  }

  /**
   * 
   * @param id is the user Stock Id for identifying the Stock
   */
  minusStocks(id: number){
    for(let i = 0; i < this.dataSource3.data.length; i++) {
      if(this.dataSource3.data[i].id == id && this.dataSource3.data[i].qty > 0){
        this.dataSource3.data[i].qty -= 1
      }
    }
  }

  /**
   * 
   * @param id is the user Stock Id for identifying the Stock
   */
  plusStocks(id: number){
    for(let i = 0; i < this.dataSource3.data.length; i++) {
      if(this.dataSource3.data[i].id == id){
        this.dataSource3.data[i].qty += 1
      }
    }
  }


  /**
   * 
   * @param id is the user Stock Id for identifying the Stock
   */
   minusMutualFunds(id: number){
    for(let i = 0; i < this.dataSource4.data.length; i++) {
      if(this.dataSource4.data[i].id == id && this.dataSource4.data[i].qty > 0){
        this.dataSource4.data[i].qty -= 1
      }
    }
  }

  /**
   * 
   * @param id is the user Stock Id for identifying the Stock
   */
  plusMutualFunds(id: number){
    for(let i = 0; i < this.dataSource4.data.length; i++) {
      if(this.dataSource4.data[i].id == id){
        this.dataSource4.data[i].qty += 1
      }
    }
  }

  /**
   * This method is for making the plus and minus buttons visible and invisible on command
   */
  countvisible(){
    if(!this.show){
      this.show = true
    }else{
      this.show = false
    }
  }

}

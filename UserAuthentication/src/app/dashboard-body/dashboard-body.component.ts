import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { MutualFund } from '../models/MutualFund.model';
import { Stocks } from '../models/Stocks.models';
import { SellDialogComponent } from '../sell-dialog/sell-dialog.component';
import { ServicesService } from '../services/services.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-dashboard-body',
  templateUrl: './dashboard-body.component.html',
  styleUrls: ['./dashboard-body.component.css'],
})
export class DashboardBodyComponent implements OnInit {
  /**
   * Declaration and initialization section
   */
  Form: any;
  NetWorthData: any;
  displayedColumns4: string[] = ['Fund', 'Price', 'Counter'];
  dataSource4: any;
  displayedColumns1: string[] = ['Stockname', 'Price'];
  dataSource1: any;
  show: boolean = false;
  show1: boolean = false;
  displayedColumns2: string[] = ['Fund', 'Price'];
  dataSource2: any;
  displayedColumns3: string[] = ['Stockname', 'Price', 'Counter'];
  dataSource3: any;
  deleteStock: number = 0;
  deletefund: number = 0;

  sellAssets: {
    stockDetails: [
      {
        id: number;
        stockId: number;
        count: number;
      }
    ];
    mutualFundDetails: [
      {
        id: number;
        mutualFundId: number;
        count: number;
      }
    ];
  } = {
    stockDetails: [
      {
        id: 1,
        stockId: 1,
        count: 1,
      },
    ],
    mutualFundDetails: [
      {
        id: 1,
        mutualFundId: 1,
        count: 1,
      },
    ],
  };

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private fb: FormBuilder,
    private httpservice: ServicesService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    /**
     * This is for getting the networth and user details
     */
    this.NetWorthData = JSON.parse(localStorage.getItem('NetWorthData'));
    
    /**
     * Calling All Stocks service
     */
    this.httpservice
      .AllStocks(
        localStorage.getItem('token'),
        localStorage.getItem('id') as unknown as number
      )
      .subscribe((data) => {
        this.dataSource1 = data;
      });

    /**
     * Calling All MutualFund service
     */
    this.httpservice
      .AllMutualFunds(
        localStorage.getItem('token'),
        localStorage.getItem('id') as unknown as number
      )
      .subscribe((data) => {
        this.dataSource2 = data;
      });

    /**
     * Creating dataSource for table 3 and 4
     */
    this.dataSource3 = new MatTableDataSource<Stocks>(
      this.NetWorthData.stockDetails
    );
    this.dataSource4 = new MatTableDataSource<MutualFund>(
      this.NetWorthData.mutualFundDetails
    );
    this.initForm();
  }
  initForm() {
    this.Form = this.fb.group({
      count: [{ value: 0, disabled: true }],
    });
  }

  /**
   *
   * @param id is the user Stock Id for identifying the Stock
   */
  minusStocks(id: number) {
    for (let i = 0; i < this.dataSource3.data.length; i++) {
      if (
        this.dataSource3.data[i].id == id &&
        this.dataSource3.data[i].qty > 0
      ) {
        this.dataSource3.data[i].qty -= 1;
        this.deleteStock += 1;
        if (
          this.sellAssets.stockDetails[this.sellAssets.stockDetails.length - 1]
            .stockId == this.dataSource3.data[i].id
        ) {
          this.sellAssets.stockDetails[
            this.sellAssets.stockDetails.length - 1
          ].count = this.dataSource3.data[i].qty;
        } else {
          this.sellAssets.stockDetails.push({
            id: 1,
            stockId: this.dataSource3.data[i].id,
            count: this.dataSource3.data[i].qty,
          });
        }
        console.log(this.sellAssets);
      }
    }
  }

  /**
   *
   * @param id is the user Stock Id for identifying the Stock
   */
  plusStocks(id: number) {
    for (let i = 0; i < this.dataSource3.data.length; i++) {
      if (this.dataSource3.data[i].id == id && this.deleteStock > 0) {
        this.dataSource3.data[i].qty += 1;
        this.deleteStock -= 1;
        this.sellAssets.stockDetails.pop();
        console.log(this.sellAssets);
      }
    }
  }

  /**
   *
   * @param id is the user Stock Id for identifying the Stock
   */
  minusMutualFunds(id: number) {
    for (let i = 0; i < this.dataSource4.data.length; i++) {
      if (
        this.dataSource4.data[i].id == id &&
        this.dataSource4.data[i].qty > 0
      ) {
        this.dataSource4.data[i].qty -= 1;
        this.deletefund += 1;
        if (
          this.sellAssets.mutualFundDetails[
            this.sellAssets.mutualFundDetails.length - 1
          ].mutualFundId == this.dataSource4.data[i].id
        ) {
          this.sellAssets.mutualFundDetails[
            this.sellAssets.mutualFundDetails.length - 1
          ].count = this.dataSource4.data[i].qty;
        } else {
          this.sellAssets.mutualFundDetails.push({
            id: 1,
            mutualFundId: this.dataSource4.data[i].id,
            count: this.dataSource4.data[i].qty,
          });
        }
        console.log(this.sellAssets);
      }
    }
  }

  /**
   *
   * @param id is the user Stock Id for identifying the Stock
   */
  plusMutualFunds(id: number) {
    for (let i = 0; i < this.dataSource4.data.length; i++) {
      if (this.dataSource4.data[i].id == id && this.deletefund > 0) {
        this.dataSource4.data[i].qty += 1;
        this.deletefund -= 1;
        this.sellAssets.mutualFundDetails.pop();
        console.log(this.sellAssets);
      }
    }
  }

  /**
   * This method is for making the plus and minus buttons visible and invisible on command
   */

  countvisibleAssets() {
    if (!this.show1) {
      this.show1 = true;
    } else {
      // this.deleteStock = 0
      this.sellAssets.stockDetails.shift();
      this.sellAssets.mutualFundDetails.shift();
      console.log(this.sellAssets);
      this.show1 = false;
      this.httpservice
        .sellAssets(
          localStorage.getItem('id') as unknown as number,
          this.sellAssets,
          localStorage.getItem('token')
        )
        .subscribe((data) => {
          console.log(data);
        });
    }
  }

  openDialog(name: string): void {
    let dialogRef: any;
    if (name == 'Stocks') {
      dialogRef = this.dialog.open(SellDialogComponent, {
        width: '50%',
        data: { name: name, asset: this.dataSource3.data },
      });
    } else {
      dialogRef = this.dialog.open(SellDialogComponent, {
        width: '50%',
        data: { name: name, asset: this.dataSource4.data },
      });
    }
    dialogRef.afterClosed().subscribe((result) => {
      console.log(result);
      let keys = Object.keys(result);
      keys.pop();
      if (name === 'Mutual Funds') {
        keys.forEach((key) => {
          this.sellAssets.mutualFundDetails.push({
            id: 1,
            mutualFundId: key as unknown as number,
            count: result[key],
          });
        });
        this.sellAssets.stockDetails.shift();
        this.sellAssets.mutualFundDetails.shift();
        console.log(this.sellAssets);
        this.httpservice
          .sellAssets(
            localStorage.getItem('id') as unknown as number,
            this.sellAssets,
            localStorage.getItem('token')
          )
          .subscribe((data) => {
            localStorage.setItem('NetWorthData', JSON.stringify(data))
            let currentUrl = this.router.url;
            this.router
              .navigateByUrl('/', { skipLocationChange: true })
              .then(() => {
                this.router.navigate([currentUrl]);
              });
          });
      } else if (name === 'Stocks') {
        keys.forEach((key) => {
          this.sellAssets.stockDetails.push({
            id: 1,
            stockId: key as unknown as number,
            count: result[key],
          });
        });
        this.sellAssets.stockDetails.shift()
        this.sellAssets.mutualFundDetails.shift()
        this.httpservice
          .sellAssets(
            localStorage.getItem('id') as unknown as number,
            this.sellAssets,
            localStorage.getItem('token')
          )
          .subscribe((data) => {
            localStorage.setItem('NetWorthData', JSON.stringify(data))
            let currentUrl = this.router.url;
            this.router
              .navigateByUrl('/', { skipLocationChange: true })
              .then(() => {
                this.router.navigate([currentUrl]);
              });
          });
      }
    });
  }
}

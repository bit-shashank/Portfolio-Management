import { TestBed } from '@angular/core/testing';

import { NetWorthDataService } from './net-worth-data.service';

describe('NetWorthDataService', () => {
  let service: NetWorthDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NetWorthDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

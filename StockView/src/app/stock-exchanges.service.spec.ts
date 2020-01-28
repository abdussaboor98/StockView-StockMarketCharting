import { TestBed } from '@angular/core/testing';

import { StockExchangesService } from './stock-exchanges.service';

describe('StockExchangesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StockExchangesService = TestBed.get(StockExchangesService);
    expect(service).toBeTruthy();
  });
});

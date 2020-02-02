import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewExchangesComponent } from './view-exchanges.component';

describe('ViewExchangesComponent', () => {
  let component: ViewExchangesComponent;
  let fixture: ComponentFixture<ViewExchangesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewExchangesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewExchangesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

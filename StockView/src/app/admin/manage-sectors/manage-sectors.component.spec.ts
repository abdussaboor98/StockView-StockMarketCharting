import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSectorsComponent } from './manage-sectors.component';

describe('ManageSectorsComponent', () => {
  let component: ManageSectorsComponent;
  let fixture: ComponentFixture<ManageSectorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageSectorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSectorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

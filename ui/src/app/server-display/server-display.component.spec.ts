import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerDisplayComponent } from './server-display.component';

describe('ServerDisplayComponent', () => {
  let component: ServerDisplayComponent;
  let fixture: ComponentFixture<ServerDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServerDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

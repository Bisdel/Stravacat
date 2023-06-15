import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppToastsComponent } from './toasts.component';

describe('AppToastsComponent', () => {
  let component: AppToastsComponent;
  let fixture: ComponentFixture<AppToastsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppToastsComponent]
    });
    fixture = TestBed.createComponent(AppToastsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

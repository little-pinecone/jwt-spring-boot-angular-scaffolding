import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CookieListComponent } from './cookie-list.component';

describe('CookieListComponent', () => {
  let component: CookieListComponent;
  let fixture: ComponentFixture<CookieListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CookieListComponent ],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
     ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CookieListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

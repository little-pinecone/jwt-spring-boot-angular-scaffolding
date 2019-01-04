import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { CookieDataService } from './cookie-data.service';
import { environment } from 'src/environments/environment';

describe('CookieDataService', () => {
  let injector: TestBed;
  let service: CookieDataService;
  let httpMock: HttpTestingController;
  let apiCookiesUrl = environment.apiUrl + '/cookies';
  let cookiesList = [
    {"flavour":"chocolate"},{"flavour":"vanilla"},{"flavour":"cinnamon"},
    {"flavour":"coconut"}
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    injector = getTestBed();
    service = injector.get(CookieDataService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    const service: CookieDataService = TestBed.get(CookieDataService);
    expect(service).toBeTruthy();
  });

  it('should retrieve all cookies', () => {
    service.findAll().subscribe((projects: any) => {
      expect(projects).toEqual(cookiesList);
    });

    const req = httpMock.expectOne(`${apiCookiesUrl}`);
    expect(req.request.method).toBe("GET");
    req.flush(cookiesList);
  });
});

import { TestBed } from '@angular/core/testing';

import { AccessIntercepterService } from './access-intercepter.service';

describe('AccessIntercepterService', () => {
  let service: AccessIntercepterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccessIntercepterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

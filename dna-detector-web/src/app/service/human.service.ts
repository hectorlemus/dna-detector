import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HumanService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiRest;
  }

  public getMutants(): Observable<any> {
    return this.http.get(this.url.concat("api/mutant-humans"), {responseType : 'json'});
  }
  
  public getNoMutants(): Observable<any> {
    return this.http.get(this.url.concat("api/non-mutant-humans"), {responseType : 'json'});
  }

  public getStats(): Observable<any> {
    return this.http.get(this.url.concat("api/stats"), {responseType : 'json'});
  }

  public createHuman(human: any): Observable<any> {
    return this.http.post(this.url.concat("api/human"), human, { responseType : 'json'} );
  }

}

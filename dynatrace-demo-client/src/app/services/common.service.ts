import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private serverStatusSource = new Subject<string>();
  serverStatus$ = this.serverStatusSource.asObservable();

  constructor(private httpClient: HttpClient) {
  }

  healthCheck(): void {
     this.httpClient.get("/health-check", { responseType: 'text' })
      .subscribe( status => {
        this.setServerStatus(status);
      });
  }

  setServerStatus(serverStatus: string): void {
     this.serverStatusSource.next(serverStatus);
  }

  startInfiniteLoop(): void {
    this.httpClient.get("/start-infinite-loop")
      .subscribe( status => {
        this.setServerStatus("Infinite loop was started!");
      })
  }

  stopInfiniteLoop(): void{
    this.httpClient.get("/stop-infinite-loop")
      .subscribe( status => {
        this.setServerStatus("Infinite loop was stopped!");
      })
  }
}

import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";

@Component({
  selector: 'app-content-container',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent implements OnInit, OnDestroy, AfterViewInit {

  serverEvent?: string;

  constructor(private commonService: CommonService) {
    this.commonService.serverStatus$.subscribe( status => {
      console.log("Got server status: " + status)
      this.serverEvent = status
    });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {

  }

  ngAfterViewInit(): void {

  }
}

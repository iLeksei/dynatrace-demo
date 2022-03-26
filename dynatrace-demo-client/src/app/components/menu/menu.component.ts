import {Component, OnDestroy, OnInit} from '@angular/core';
import {CommonService} from "../../services/common.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public serverStatus?: string;

  constructor(private commonService: CommonService) {
  }

  ngOnInit(): void {
  }

  healthCheck() {
    this.commonService.healthCheck()
  }

  stopInfiniteLoop() {
    this.commonService.stopInfiniteLoop()
    console.log("Infinite loop was stopped!")
  }

  startInfiniteLoop() {
    this.commonService.startInfiniteLoop()
    console.log("Infinite loop was started!")
  }
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DtIconModule } from '@dynatrace/barista-components/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {DtMenuModule} from "@dynatrace/barista-components/menu";
import {MenuComponent} from "./components/menu/menu.component";
import { ContentComponent } from './components/content/content.component';
@NgModule({
    declarations: [
        AppComponent,
        MenuComponent,
        ContentComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        DtIconModule.forRoot({ svgIconLocation: '/assets/icons/{{name}}.svg' }),
        HttpClientModule,
        //dynatrace
        DtMenuModule,
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule    } from '@angular/platform-browser';
import { NgModule         } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule        } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerModule } from "ngx-spinner";

import { AppComponent           } from './app.component';
import { ServerDisplayComponent } from './server-display/server-display.component';

@NgModule({
    declarations: [
        AppComponent,
        ServerDisplayComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        NgbModule,
        NgxSpinnerModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }

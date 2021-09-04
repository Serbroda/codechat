import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { environment } from '../environments/environment';
import { BASE_PATH } from 'codechat-sdk-angular';
import { YoutubePlayerComponent } from './component/youtube-player/youtube-player.component';

@NgModule({
  declarations: [
    AppComponent,
    YoutubePlayerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [{ provide: BASE_PATH, useValue: environment.apiBaseUrl }],
  bootstrap: [AppComponent]
})
export class AppModule { }

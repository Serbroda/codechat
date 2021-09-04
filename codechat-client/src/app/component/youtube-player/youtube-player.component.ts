import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-youtube-player',
  templateUrl: './youtube-player.component.html',
  styleUrls: ['./youtube-player.component.scss']
})
export class YoutubePlayerComponent implements OnInit {

  YT: any;
  video: any;
  player: any;
  reframed: boolean = false;

  constructor() {
    this.video = 'L075FL5iG_g'
  }

  ngOnInit(): void {
    // @ts-ignore
    if (window['YT']) {
      this.startVideo();
      return;
    }

    let tag = document.createElement("script");
    tag.src = "https://www.youtube.com/player_api";
    let firstScriptTag = document.getElementsByTagName('script')[0];
    // @ts-ignore
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // @ts-ignore
    window['onYouTubeIframeAPIReady'] = () => this.startVideo();
  }


  startVideo() {
    this.reframed = false;
    // @ts-ignore
    this.player = new window['YT'].Player('player', {
      videoId: this.video,
      playerVars: {
        autoplay: 1,
        modestbranding: 1,
        controls: 0,
        mute: 1,
        origin: 'http://localhost:8080'
      },
      events: {
        'onStateChange': this.onPlayerStateChange.bind(this),
        'onError': this.onPlayerError.bind(this),
        'onReady': this.onPlayerReady.bind(this),
      }
    });
  }

  onPlayerReady(event: any) {
    console.log('onPlayerReady');
    event.target.playVideo();
    event.target.setVolume(100);
    //event.target.setVolume(1);
  }

  setVol() {
    console.log(this.player.getVolume());
  }

  onPlayerStateChange(event: any) {
    console.log(event)

    switch (event.data) {
      // @ts-ignore
      case window['YT'].PlayerState.PLAYING:
        if (this.cleanTime() == 0) {
          console.log('started ' + this.cleanTime());
        } else {
          console.log('playing ' + this.cleanTime())
        };
        break;
      // @ts-ignore
      case window['YT'].PlayerState.PAUSED:
        if (this.player.getDuration() - this.player.getCurrentTime() != 0) {
          console.log('paused' + ' @ ' + this.cleanTime());
        };
        break;
      // @ts-ignore
      case window['YT'].PlayerState.ENDED:
        console.log('ended ');
        break;
    };
  };

  cleanTime() {
    return Math.round(this.player.getCurrentTime())
  };

  onPlayerError(event: any) {
    switch (event.data) {
      case 2:
        console.log('' + this.video)
        break;
      case 100:
        break;
      case 101 || 150:
        break;
    };
  };
}

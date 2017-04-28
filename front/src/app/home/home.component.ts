import {Component} from '@angular/core';
import {$WebSocket} from "angular2-websocket/angular2-websocket";

@Component({
  selector: 'home',
  styleUrls: ['./home.component.css'],
  templateUrl: './home.component.html'
})
export class HomeComponent {

  private ws:$WebSocket;
  private counter:number;

  subscribe() {
    console.log("trying to subscribe to ws");
    this.ws = new $WebSocket("ws://localhost:3000/ws/counter");
    this.ws.send("Hello");
    this.ws.getDataStream().subscribe(
      res => {
        var count = JSON.parse(res.data).value;
        console.log('Got: ' + count);
        this.counter = count;
      },
      function(e) { console.log('Error: ' + e.message); },
      function() { console.log('Completed'); }
    );
  }

  close() {
    this.ws.close();
  }

  sendMessage(message){
    console.log('sending', message);
    this.ws.send(message).subscribe(
      (msg)=> {
        console.log("next", msg.data);
      },
      (msg)=> {
        console.log("error", msg);
      },
      ()=> {
        console.log("complete");
      }
    );
  }
}

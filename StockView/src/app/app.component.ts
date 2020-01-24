import { Component } from '@angular/core';
import { User } from './models/users';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'StockView';
  user = new User(101,'Abdus Saboor','gaffari98@gmail.com',9876543210);
}

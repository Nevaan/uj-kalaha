import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { KalahaBoardComponent } from './kalaha-board/kalaha-board.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, KalahaBoardComponent],
  templateUrl: './app.html',
  styleUrl: './app.sass'
})
export class App {
  protected readonly title = signal('kalaha-frontend');
}

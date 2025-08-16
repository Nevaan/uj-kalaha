import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KalahaPitComponent } from '../kalaha-pit/kalaha-pit.component';
import { KalahaStoreComponent } from '../kalaha-store/kalaha-store.component';

@Component({
  selector: 'kalaha-board',
  standalone: true,
  imports: [CommonModule, KalahaPitComponent, KalahaStoreComponent],
  templateUrl: './kalaha-board.component.html',
  styleUrls: ['./kalaha-board.component.scss']
})
export class KalahaBoardComponent {
  player1Pits = [4, 4, 4, 4, 4, 4];
  player2Pits = [4, 4, 4, 4, 4, 4];
  player1Store = 0;
  player2Store = 0;
}

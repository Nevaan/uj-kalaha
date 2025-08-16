import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KalahaPitComponent } from '../kalaha-pit/kalaha-pit.component';
import { KalahaStoreComponent } from '../kalaha-store/kalaha-store.component';
import { KalahaWebsocketService } from '../services/kalaha-websocket.service';

@Component({
  selector: 'kalaha-board',
  standalone: true,
  imports: [CommonModule, KalahaPitComponent, KalahaStoreComponent],
  templateUrl: './kalaha-board.component.html',
  styleUrls: ['./kalaha-board.component.scss']
})
export class KalahaBoardComponent implements OnInit {
  player1Pits = [4, 4, 4, 4, 4, 4];
  player2Pits = [4, 4, 4, 4, 4, 4];
  player1Store = 0;
  player2Store = 0;

  constructor(private webSocketService: KalahaWebsocketService) {}

  ngOnInit() {
    this.webSocketService.gameState$.subscribe(state => {
      this.player1Pits = state.player1Pits;
      this.player2Pits = state.player2Pits;
      this.player1Store = state.player1Store;
      this.player2Store = state.player2Store;
    });
  }

  onPitClick(pitIndex: number) {
    this.webSocketService.makeMove(pitIndex);
  }
}

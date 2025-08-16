import { Injectable, OnDestroy } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { BehaviorSubject, EMPTY, Observable, Subject, catchError, delay, retryWhen, takeUntil, tap } from 'rxjs';

export interface GameState {
  player1Pits: number[];
  player2Pits: number[];
  player1Store: number;
  player2Store: number;
}

export interface Move {
  pitIndex: number;
}

@Injectable({
  providedIn: 'root'
})
export class KalahaWebsocketService implements OnDestroy {
  private socket$!: WebSocketSubject<any>;
  private destroy$ = new Subject<void>();
  private gameState = new BehaviorSubject<GameState>({
    player1Pits: [4, 4, 4, 4, 4, 4],
    player2Pits: [4, 4, 4, 4, 4, 4],
    player1Store: 0,
    player2Store: 0
  });

  gameState$ = this.gameState.asObservable();

  constructor() {
    this.setupWebSocketConnection();
  }

  private setupWebSocketConnection(): void {
    this.socket$ = webSocket({
      url: 'ws://localhost:8080/ws-kalaha',
      deserializer: msg => {
        try {
          return JSON.parse(msg.data);
        } catch (err) {
          console.error('Failed to parse WebSocket message:', err);
          return null;
        }
      }
    });

    this.socket$.pipe(
      tap(msg => console.log('Message received:', msg)),
      retryWhen(errors =>
        errors.pipe(
          tap(err => console.warn('WebSocket error, retrying:', err)),
          delay(1000)
        )
      ),
      takeUntil(this.destroy$),
      catchError(err => {
        console.error('Fatal WebSocket error:', err);
        return EMPTY;
      })
    ).subscribe(state => {
      if (state) {
        this.gameState.next(state as GameState);
      }
    });
  }

  makeMove(pitIndex: number): void {
    if (this.socket$) {
      const move: Move = { pitIndex };
      this.socket$.next(move);
    }
  }

  reconnect(): void {
    this.disconnect();
    this.setupWebSocketConnection();
  }

  disconnect(): void {
    this.socket$?.complete();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.disconnect();
  }
}

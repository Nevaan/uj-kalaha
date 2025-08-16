import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'kalaha-store',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './kalaha-store.component.html',
  styleUrls: ['./kalaha-store.component.scss']
})
export class KalahaStoreComponent {
  @Input() value: number = 0;
  @Input() position: 'left' | 'right' = 'left';
}


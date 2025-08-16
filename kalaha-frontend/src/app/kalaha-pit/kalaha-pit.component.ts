import { Component, Input } from '@angular/core';

@Component({
  selector: 'kalaha-pit',
  standalone: true,
  templateUrl: './kalaha-pit.component.html',
  styleUrls: ['./kalaha-pit.component.scss']
})
export class KalahaPitComponent {
  @Input() value: number = 0;
}


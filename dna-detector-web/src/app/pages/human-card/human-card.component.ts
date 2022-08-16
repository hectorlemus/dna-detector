import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-human-card',
  templateUrl: './human-card.component.html',
  styleUrls: ['./human-card.component.css']
})
export class HumanCardComponent implements OnInit {

  @Input() name: string;

  @Input() createdAt: string;

  @Input() mutant: boolean;

  constructor() { }

  ngOnInit() {
  }

}

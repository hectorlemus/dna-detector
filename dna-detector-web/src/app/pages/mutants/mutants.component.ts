import { Component, OnInit } from '@angular/core';
import { HumanService } from 'src/app/service/human.service';

@Component({
  selector: 'app-mutants',
  templateUrl: './mutants.component.html',
  styles: ['']
})
export class MutantsComponent implements OnInit {

  humans: [];

  constructor(private humanService:HumanService) {
  }

  ngOnInit() {
    this.humans = [];
    this.getMutants();
  }

  getMutants(): void {
    this.humanService.getMutants().subscribe((data: any) => {
        this.humans = data;        
      }, (error) => {
        console.error(error);
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import { HumanService } from 'src/app/service/human.service';

@Component({
  selector: 'app-no-mutants',
  templateUrl: './no-mutants.component.html',
  styles: ['']
})
export class NoMutantsComponent implements OnInit {

  humans: [];

  constructor(private humanService:HumanService) {
  }  

  ngOnInit() {
    this.getNoMutants();
  }

  getNoMutants(): void {
    this.humans = [];
    this.humanService.getNoMutants().subscribe((data: any) => {
      this.humans = data;
    }, error => {
      console.error(error);
    });
  }
  
}

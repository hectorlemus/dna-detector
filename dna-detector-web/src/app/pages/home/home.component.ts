import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HumanService } from 'src/app/service/human.service';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  humanForm: FormGroup;
  isSubmitted = false;
  hasError = false;

  stats: any;

  @ViewChild('closeModal', {static: false}) closeModal: ElementRef;

  constructor(
    private formBuilder: FormBuilder,
    private humanService: HumanService,
    private authService: AuthService,
    private router: Router,
  ) {

  }  

  ngOnInit() {
    this.humanForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  add(): void {
    if(this.humanForm.invalid) {
      return;
    }

    this.isSubmitted = true;
    this.hasError = false;
        
    this.humanService.createHuman(this.humanForm.value).subscribe(() => {
      this.isSubmitted = false;
      this.closeModal.nativeElement.click();      
    }, () => {
      this.isSubmitted = false;
      this.hasError = true;
    });
  }

  getStats(): void {
    this.humanService.getStats().subscribe((data: any) => {
      this.stats = data;
    }, (error: any) => {
      console.log(error);
    });
  }

  logOut(): void {
    this.authService.logOut();
    this.router.navigateByUrl('/login');
  }

}

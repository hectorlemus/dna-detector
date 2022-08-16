import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  isSubmitted = false;
  hasError = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.loginForm  =  this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get formControls() {
    return this.loginForm.controls;
  }

  login(): void {
    if(this.loginForm.invalid) {
      return;
    }

    this.isSubmitted = true;
    this.hasError = false;
    
    this.authService.login(this.loginForm.value)
    .subscribe((response: any) => {
      let data = JSON.parse(JSON.stringify(response));
      let token = data['token']      
      this.authService.saveToken(token);
      this.isSubmitted = false;
      this.router.navigateByUrl('/home/no-mutants');
    }, () => {
      this.isSubmitted = false;
      this.hasError = true;
    });
  }

}

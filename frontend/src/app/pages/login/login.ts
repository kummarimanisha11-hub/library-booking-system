import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  user = {
    email: '',
    password: ''
  };

  constructor(
    private auth: Auth,
    private router: Router
  ) {}

  login() {

    console.log('Login button clicked');
    console.log(this.user);

    this.auth.login(this.user).subscribe({
      next: (response: any) => {

        console.log('Login successful', response);

        if (response.token) {
          localStorage.setItem('token', response.token);
        }

        alert('Login successful!');

        this.router.navigate(['/']);
      },

      error: (error: any) => {

        console.error('Login failed', error);

        alert('Login failed!');
      }
    });
  }
}

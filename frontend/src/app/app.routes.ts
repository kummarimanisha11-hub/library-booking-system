import { Routes } from '@angular/router';
import { AdminBooks } from './pages/admin-books/admin-books';

import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { Books } from './pages/books/books';
import { Booking } from './pages/booking/booking';

export const routes: Routes = [

  {
    path: 'login',
    component: Login
  },

  {
    path: 'register',
    component: Register
  },

  {
    path: 'books',
    component: Books
  },

  {
    path: 'booking',
    component: Booking
  },
  {
    path: 'admin-books',
    component: AdminBooks
  },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  }

];

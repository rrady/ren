import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '@app/services/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  showLoginModal: boolean = false;
  showRegisterModal: boolean = false;
  showChangePasswordModal: boolean = false;

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
  }

  toggleLoginModal(showLoginModal: boolean): void {
    this.showLoginModal = showLoginModal;
  }

  toggleRegisterModal(showRegisterModal: boolean): void {
    this.showRegisterModal = showRegisterModal;
  }

  toggleChangePasswordModal(showChangePasswordModal: boolean): void {
    this.showChangePasswordModal = showChangePasswordModal;
  }

  logout(): void {
    this.authService.logout().subscribe();
    this.router.navigate(['/feed']);
  }
}

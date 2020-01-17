import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/services/auth/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  showLoginModal: boolean = false;
  showRegisterModal: boolean = false;

  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

  toggleLoginModal(showLoginModal: boolean): void {
    this.showLoginModal = showLoginModal;
  }

  toggleRegisterModal(showRegisterModal: boolean): void {
    this.showRegisterModal = showRegisterModal;
  }
}

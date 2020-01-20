import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ModalComponent } from '@app/modules/shared/modal/modal.component';
import { SearchBoxComponent } from '@app/modules/shared/search-box/search-box.component';
import { PaginatedGridComponent } from '@app/modules/shared/paginated-grid/paginated-grid.component';

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [
    SearchBoxComponent,
    ModalComponent,
    PaginatedGridComponent
  ],
  exports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    SearchBoxComponent,
    ModalComponent,
    PaginatedGridComponent
  ]
})
export class SharedModule { }

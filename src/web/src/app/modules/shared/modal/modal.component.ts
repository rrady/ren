import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() title: string;
  @Input() visible: boolean;
  @Input() error: string[];
  @Output() onToggle: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() onSave: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {}

  ngOnInit(): void {}

  toggle(): void {
    this.visible = !this.visible;
    this.onToggle.emit(this.visible);
  }

  save(): void {
    this.onSave.emit();
  }
}

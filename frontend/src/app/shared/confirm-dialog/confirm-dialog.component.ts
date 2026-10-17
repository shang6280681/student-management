import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
@Component({selector:'app-confirm-dialog',standalone:true,imports:[MatDialogModule,MatButtonModule],template:`<h2 mat-dialog-title>Confirm Delete</h2><mat-dialog-content><p>{{data.message}}</p></mat-dialog-content><mat-dialog-actions align="end"><button mat-button (click)="ref.close(false)">Cancel</button><button mat-flat-button color="warn" (click)="ref.close(true)">Delete</button></mat-dialog-actions>`})
export class ConfirmDialogComponent {constructor(public ref:MatDialogRef<ConfirmDialogComponent>,@Inject(MAT_DIALOG_DATA) public data:{message:string}){}}

import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-novo-funcionario',
  templateUrl: './novo-funcionario.component.html',
  styleUrls: ['./novo-funcionario.component.css']
})
export class NovoFuncionarioComponent implements OnInit {

  @ViewChild('fileInput')
  fileInput!: ElementRef

  funcionario: FormGroup = this.fb.group({
    nome: ['', [ Validators.required ]],
    email: ['', [ Validators.required, Validators.email ]],
    foto: ['']
  })

  constructor(
    private fb: FormBuilder,
    private funHttpService: FuncionarioHttpService,
    private snackbar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  selectImage(): void {
    this.fileInput.nativeElement.click()
  }

  submit(): void {
    const funcionario = this.funcionario.value

    this.funHttpService
    .createFuncionario(funcionario)
    .subscribe(
      () => {
        this.snackbar.open('Funcionário salvo!', 'Ok', {
          duration: 3000,
          horizontalPosition: 'left',
          verticalPosition: 'top'
        })
        this.router.navigateByUrl('/funcionario')
      },
      (e: HttpErrorResponse) => {
        this.snackbar.open(`Ocorreu um erro no salvamento! (Erro ${e.status})`, 'Ok', {
          duration: 3000,
          horizontalPosition: 'left',
          verticalPosition: 'top'
        })
      }
    )
  }
}

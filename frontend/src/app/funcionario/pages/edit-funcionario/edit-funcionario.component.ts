import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ConfirmExitDialogComponent } from '../../components/confirm-exit-dialog/confirm-exit-dialog.component';
import { CanDeactivate } from '../../models/canDeactivate';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioHttpService } from '../../services/funcionario-http.service';

@Component({
  selector: 'app-edit-funcionario',
  templateUrl: './edit-funcionario.component.html',
  styleUrls: ['./edit-funcionario.component.css']
})
export class EditFuncionarioComponent implements OnInit, CanDeactivate {

  fun!: Funcionario

  funcionario: FormGroup = this.fb.group({
    nome: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    foto: [null]
  })

  foto!: File

  constructor(
    private route: ActivatedRoute,
    private funHttpService: FuncionarioHttpService,
    private fb: FormBuilder,
    private snackbar: MatSnackBar,
    private router: Router,
    private dialog: MatDialog
  ) { }

  canDeactivate(): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.funcionario.dirty) {
      const dialogRef = this.dialog.open(ConfirmExitDialogComponent)

      return dialogRef.afterClosed()
    }

    return true
  }

  ngOnInit(): void {
    const id: number = parseInt(this.route.snapshot.paramMap.get('idFuncionario') || '0')

    this.funHttpService.getFuncionarioById(id)
    .subscribe(
      (f) => {
        this.fun = f

        this.funcionario.patchValue({
          nome: this.fun.nome,
          email: this.fun.email
        })
      }
    )
  }

  submit(): void {
    this.fun.nome = this.funcionario.value.nome
    this.fun.email = this.funcionario.value.email

    this.funHttpService
    .updateFuncionario(this.fun)
    .subscribe(
      () => {
        if (this.foto != undefined) {
          const formData = new FormData()

          formData.append('foto', this.foto)

          const filename = `funcionario-${this.fun.idFuncionario}.${this.foto.type.split('/')[1]}`

          this.funHttpService.addFoto(this.fun.idFuncionario || 0, formData, filename)
          .subscribe(
            () => {
              this.funcionario.reset()
              this.showSuccessMessageAndRedirect()
            },
            (e: HttpErrorResponse) => {
              this.showErrorMessage(e)
            }
          )
        } else {
          this.funcionario.reset()
          this.showSuccessMessageAndRedirect()
        }
      },
      (e: HttpErrorResponse) => {
        this.showErrorMessage(e)
      }
    )
  }

  fileChange(event: any): void {
    this.foto = event.target.files[0]
  }

  showSuccessMessageAndRedirect(): void {
    this.snackbar.open('Funcionário salvo!', 'Ok', {
      duration: 3000,
      horizontalPosition: 'left',
      verticalPosition: 'top'
    })

    this.router.navigateByUrl('/funcionario')
  }

  showErrorMessage(e: HttpErrorResponse): void {
    this.snackbar.open(`Ocorreu um erro no salvamento! (Erro ${e.status})`, 'Ok', {
      duration: 3000,
      horizontalPosition: 'left',
      verticalPosition: 'top'
    })
  }
}

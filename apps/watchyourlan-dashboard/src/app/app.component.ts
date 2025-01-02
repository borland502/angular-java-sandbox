import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButton } from '@angular/material/button';

@Component({
    imports: [RouterModule, MatToolbar, MatButton],
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss',
})
export class AppComponent {
    title = 'watchyourlan-dashboard';
}

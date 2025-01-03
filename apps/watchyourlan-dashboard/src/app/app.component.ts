import { Component } from '@angular/core';
import { AdminLayoutComponent } from '@ng-matero/theme/admin-layout/admin-layout.component';
import { CustomizerComponent } from '@ng-matero/theme/customizer/customizer.component';
import { TopmenuComponent } from '@ng-matero/theme/topmenu/topmenu.component';
import { SidebarComponent } from '@ng-matero/theme/sidebar/sidebar.component';
import { SidebarNoticeComponent } from '@ng-matero/theme/sidebar-notice/sidebar-notice.component';
import { MatSidenav, MatSidenavContent, MatSidenavContainer } from '@angular/material/sidenav';
import { NgProgressbar } from 'ngx-progressbar';
import { HeaderComponent } from '@ng-matero/theme/header/header.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, AdminLayoutComponent, NgProgressbar, HeaderComponent, CustomizerComponent, TopmenuComponent, SidebarComponent, SidebarNoticeComponent, MatSidenavContent, MatSidenav, MatSidenavContainer],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true
})
export class AppComponent extends AdminLayoutComponent {
  title = 'matero-angular';

}

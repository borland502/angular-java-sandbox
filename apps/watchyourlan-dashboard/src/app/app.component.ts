import { AfterViewInit, Component, inject } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButton } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { EntityModelHost } from '@technohouser/api/models';
import {
  HostEntityControllerService
} from '@technohouser/api/services/host-entity-controller/host-entity-controller.service';

@Component({
  imports: [
    RouterModule,
    MatToolbar,
    MatButton,
    MatTableModule,
    RouterLink
  ],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true
})
export class AppComponent implements AfterViewInit {

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;
  title = 'watchyourlan-dashboard';
  displayedColumns: string[] = [
    'name',
    'ip',
    'mac',
    'date',
    'hw',
    'known',
    'now'
  ];
  protected hosts: EntityModelHost[] = [];
  private hostDataService = inject(HostEntityControllerService);

  ngAfterViewInit(): void {
    this.hostDataService
      .getCollectionResourceHostGet()
      .subscribe((response) => {
        console.log(response);
        if (typeof response !== 'string' && response._embedded?.hosts) {
          this.hosts = response._embedded.hosts;
        } else {
          console.error('Error loading hosts');
        }
      });
  }

}
